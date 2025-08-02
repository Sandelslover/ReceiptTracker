package com.receipttracker.ui.receipt

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.receipttracker.R
import com.receipttracker.data.database.ReceiptDatabase
import com.receipttracker.data.model.Receipt
import com.receipttracker.data.repository.ReceiptRepository
import com.receipttracker.databinding.ActivityReceiptDetailBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ReceiptDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceiptDetailBinding
    private lateinit var repository: ReceiptRepository
    private var receipt: Receipt? = null
    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiptDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = ReceiptDatabase.getDatabase(this)
        repository = ReceiptRepository(database.receiptDao())

        setupToolbar()
        loadReceipt()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Receipt Details"
    }

    private fun loadReceipt() {
        val receiptId = intent.getLongExtra("receipt_id", -1)
        if (receiptId == -1L) {
            finish()
            return
        }

        lifecycleScope.launch {
            receipt = repository.getReceiptById(receiptId)
            receipt?.let { displayReceipt(it) } ?: finish()
        }
    }

    private fun displayReceipt(receipt: Receipt) {
        binding.apply {
            vendorText.text = receipt.vendor
            totalText.text = String.format("$%.2f", receipt.total)
            dateText.text = dateFormat.format(receipt.date)
            categoryText.text = receipt.category.displayName
            
            if (!receipt.notes.isNullOrEmpty()) {
                notesText.text = receipt.notes
                notesText.visibility = android.view.View.VISIBLE
                notesLabel.visibility = android.view.View.VISIBLE
            }

            if (receipt.isWarrantyAlertEnabled && receipt.warrantyExpiryDate != null) {
                warrantyText.text = dateFormat.format(receipt.warrantyExpiryDate)
                warrantyText.visibility = android.view.View.VISIBLE
                warrantyLabel.visibility = android.view.View.VISIBLE
            }

            if (!receipt.imagePath.isNullOrEmpty()) {
                receiptImageView.visibility = android.view.View.VISIBLE
                Glide.with(this@ReceiptDetailActivity)
                    .load(Uri.parse(receipt.imagePath))
                    .centerCrop()
                    .into(receiptImageView)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.receipt_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_edit -> {
                // TODO: Implement edit functionality
                true
            }
            R.id.action_delete -> {
                deleteReceipt()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteReceipt() {
        receipt?.let { receipt ->
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Delete Receipt")
                .setMessage("Are you sure you want to delete this receipt?")
                .setPositiveButton("Delete") { _, _ ->
                    lifecycleScope.launch {
                        repository.deleteReceipt(receipt)
                        finish()
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
