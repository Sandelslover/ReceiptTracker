package com.receipttracker.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.receipttracker.data.database.ReceiptDatabase
import com.receipttracker.data.repository.ReceiptRepository
import com.receipttracker.databinding.FragmentSummaryBinding
import java.text.SimpleDateFormat
import java.util.*

class SummaryFragment : Fragment() {
    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: SummaryViewModel
    private lateinit var categoryAdapter: CategorySummaryAdapter
    private val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViewModel()
        setupRecyclerView()
        setupObservers()
        setupMonthNavigation()
        
        // Load current month by default
        viewModel.loadSummaryForMonth(Calendar.getInstance())
    }

    private fun setupViewModel() {
        val database = ReceiptDatabase.getDatabase(requireContext())
        val repository = ReceiptRepository(database.receiptDao())
        val factory = SummaryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[SummaryViewModel::class.java]
    }

    private fun setupRecyclerView() {
        categoryAdapter = CategorySummaryAdapter()
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.categoryRecyclerView.adapter = categoryAdapter
    }

    private fun setupObservers() {
        viewModel.currentMonth.observe(viewLifecycleOwner) { calendar ->
            binding.monthText.text = dateFormat.format(calendar.time)
        }

        viewModel.totalSpent.observe(viewLifecycleOwner) { total ->
            binding.totalAmountText.text = String.format("$%.2f", total)
        }

        viewModel.categorySummaries.observe(viewLifecycleOwner) { summaries ->
            categoryAdapter.submitList(summaries)
            binding.emptyView.visibility = if (summaries.isEmpty()) View.VISIBLE else View.GONE
            binding.categoryRecyclerView.visibility = if (summaries.isEmpty()) View.GONE else View.VISIBLE
        }

        viewModel.receiptCount.observe(viewLifecycleOwner) { count ->
            binding.receiptCountText.text = "$count receipts"
        }

        viewModel.averageSpent.observe(viewLifecycleOwner) { average ->
            binding.averageSpentText.text = String.format("$%.2f avg per receipt", average)
        }
    }

    private fun setupMonthNavigation() {
        binding.previousMonthButton.setOnClickListener {
            viewModel.navigateToPreviousMonth()
        }

        binding.nextMonthButton.setOnClickListener {
            viewModel.navigateToNextMonth()
        }

        binding.monthText.setOnClickListener {
            showMonthPickerDialog()
        }
    }

    private fun showMonthPickerDialog() {
        val calendar = viewModel.currentMonth.value ?: Calendar.getInstance()
        
        val monthPickerDialog = android.app.DatePickerDialog(
            requireContext(),
            { _, year, month, _ ->
                val selectedCalendar = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, 1)
                }
                viewModel.loadSummaryForMonth(selectedCalendar)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        
        monthPickerDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
