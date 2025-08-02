package com.receipttracker.ui.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.receipttracker.data.database.CategorySummary
import com.receipttracker.data.repository.ReceiptRepository
import kotlinx.coroutines.launch
import java.util.*

class SummaryViewModel(private val repository: ReceiptRepository) : ViewModel() {
    
    private val _currentMonth = MutableLiveData<Calendar>()
    val currentMonth: LiveData<Calendar> = _currentMonth
    
    private val _totalSpent = MutableLiveData<Double>()
    val totalSpent: LiveData<Double> = _totalSpent
    
    private val _categorySummaries = MutableLiveData<List<CategorySummary>>()
    val categorySummaries: LiveData<List<CategorySummary>> = _categorySummaries
    
    private val _receiptCount = MutableLiveData<Int>()
    val receiptCount: LiveData<Int> = _receiptCount
    
    private val _averageSpent = MutableLiveData<Double>()
    val averageSpent: LiveData<Double> = _averageSpent
    
    fun loadSummaryForMonth(calendar: Calendar) {
        _currentMonth.value = calendar
        
        val startOfMonth = Calendar.getInstance().apply {
            time = calendar.time
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        
        val endOfMonth = Calendar.getInstance().apply {
            time = startOfMonth.time
            add(Calendar.MONTH, 1)
            add(Calendar.MILLISECOND, -1)
        }
        
        viewModelScope.launch {
            try {
                val summaries = repository.getMonthlySummaryByCategory(
                    startOfMonth.time,
                    endOfMonth.time
                )
                _categorySummaries.value = summaries
                
                val total = repository.getTotalSpentInPeriod(
                    startOfMonth.time,
                    endOfMonth.time
                )
                _totalSpent.value = total
                
                val receipts = repository.getReceiptsByDateRange(
                    startOfMonth.time,
                    endOfMonth.time
                ).value ?: emptyList()
                
                _receiptCount.value = receipts.size
                _averageSpent.value = if (receipts.isNotEmpty()) total / receipts.size else 0.0
                
            } catch (e: Exception) {
                _categorySummaries.value = emptyList()
                _totalSpent.value = 0.0
                _receiptCount.value = 0
                _averageSpent.value = 0.0
            }
        }
    }
    
    fun navigateToPreviousMonth() {
        val current = _currentMonth.value ?: Calendar.getInstance()
        val previous = Calendar.getInstance().apply {
            time = current.time
            add(Calendar.MONTH, -1)
        }
        loadSummaryForMonth(previous)
    }
    
    fun navigateToNextMonth() {
        val current = _currentMonth.value ?: Calendar.getInstance()
        val next = Calendar.getInstance().apply {
            time = current.time
            add(Calendar.MONTH, 1)
        }
        loadSummaryForMonth(next)
    }
}

class SummaryViewModelFactory(private val repository: ReceiptRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SummaryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SummaryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
