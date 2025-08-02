package com.receipttracker.ui.receipt

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.receipttracker.R
import com.receipttracker.data.database.ReceiptDatabase
import com.receipttracker.data.model.Receipt
import com.receipttracker.data.model.ReceiptCategory
import com.receipttracker.data.repository.ReceiptRepository
import com.receipttracker.databinding.ActivityAddReceiptBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddReceiptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddReceiptBinding
    private lateinit var repository: ReceiptRepository
    private var selectedDate = Date()
    private var warrantyDate: Date? = null
    private var imagePath: String? = null
    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = ReceiptDatabase.getDatabase(this)
        repository = ReceiptRepository(database.receiptDao())

        setupUI()
        handleIntent()
    }

    private fun setupUI() {
        // Setup category spinner
        val categories = ReceiptCategory.values().map { it.displayName }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.categorySpinner.adapter = adapter

        // Setup date picker
        binding.dateButton.setOnClickListener { showDatePicker() }
        binding.dateButton.text = dateFormat.format(selectedDate)

        // Setup warranty date picker
        binding.warrantyDateButton.setOnClickListener { showWarrantyDatePicker() }
        
        // Setup warranty toggle
        binding.warrantySwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.warrantyDateLayout.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        // Setup buttons
        binding.saveButton.setOnClickListener { saveReceipt() }
        binding.cancelButton.setOnClickListener { finish() }
        binding.backButton.setOnClickListener { finish() }
    }

    private fun handleIntent() {
        intent?.let { intent ->
            // Pre-fill from OCR results
            intent.getStringExtra("vendor")?.let { 
                binding.vendorEditText.setText(it)
            }
            
            intent.getDoubleExtra("total", 0.0).let { total ->
                if (total > 0) {
                    binding.totalEditText.setText(String.format("%.2f", total))
                }
            }
            
            intent.getLongExtra("date", 0L).let { dateMillis ->
                if (dateMillis > 0) {
                    selectedDate = Date(dateMillis)
                    binding.dateButton.text = dateFormat.format(selectedDate)
                }
            }
            
            intent.getStringExtra("category")?.let { categoryName ->
                val categoryIndex = ReceiptCategory.values().indexOfFirst { it.name == categoryName }
                if (categoryIndex >= 0) {
                    binding.categorySpinner.setSelection(categoryIndex)
                }
            }
            
            intent.getStringExtra("imagePath")?.let { path ->
                imagePath = path
                loadReceiptImage(path)
            }
            
            intent.getStringExtra("rawText")?.let { rawText ->
                binding.notesEditText.setText("OCR Text: $rawText")
            }
        }
    }

    private fun loadReceiptImage(path: String) {
        try {
            binding.receiptImageView.visibility = View.VISIBLE
            Glide.with(this)
                .load(Uri.parse(path))
                .centerCrop()
                .into(binding.receiptImageView)
        } catch (e: Exception) {
            binding.receiptImageView.visibility = View.GONE
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        calendar.time = selectedDate
        
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                selectedDate = calendar.time
                binding.dateButton.text = dateFormat.format(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showWarrantyDatePicker() {
        val calendar = Calendar.getInstance()
        warrantyDate?.let { calendar.time = it }
        
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                warrantyDate = calendar.time
                binding.warrantyDateButton.text = dateFormat.format(warrantyDate!!)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun saveReceipt() {
        val vendor = binding.vendorEditText.text.toString().trim()
        val totalText = binding.totalEditText.text.toString().trim()
        val notes = binding.notesEditText.text.toString().trim()

        if (vendor.isEmpty()) {
            binding.vendorEditText.error = "Vendor name is required"
            return
        }

        val total = try {
            totalText.toDouble()
        } catch (e: NumberFormatException) {
            binding.totalEditText.error = "Please enter a valid amount"
            return
        }

        val selectedCategory = ReceiptCategory.values()[binding.categorySpinner.selectedItemPosition]
        val isWarrantyEnabled = binding.warrantySwitch.isChecked

        val receipt = Receipt(
            vendor = vendor,
            total = total,
            date = selectedDate,
            category = selectedCategory,
            imagePath = imagePath,
            notes = notes.ifEmpty { null },
            warrantyExpiryDate = if (isWarrantyEnabled) warrantyDate else null,
            isWarrantyAlertEnabled = isWarrantyEnabled
        )

        lifecycleScope.launch {
            try {
                repository.insertReceipt(receipt)
                Toast.makeText(this@AddReceiptActivity, "Receipt saved successfully", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK)
                finish()
            } catch (e: Exception) {
                Toast.makeText(this@AddReceiptActivity, "Failed to save receipt", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
