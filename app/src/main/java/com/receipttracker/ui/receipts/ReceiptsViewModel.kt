package com.receipttracker.ui.receipts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.switchMap
import com.receipttracker.data.model.Receipt
import com.receipttracker.data.model.ReceiptCategory
import com.receipttracker.data.repository.ReceiptRepository

class ReceiptsViewModel(private val repository: ReceiptRepository) : ViewModel() {
    
    private val _searchQuery = MutableLiveData("")
    private val _selectedCategory = MutableLiveData<ReceiptCategory?>(null)
    
    val receipts: LiveData<List<Receipt>> = _searchQuery.switchMap { query ->
        val category = _selectedCategory.value
        when {
            query.isNotEmpty() -> repository.searchReceipts(query)
            category != null -> repository.getReceiptsByCategory(category)
            else -> repository.getAllReceipts()
        }
    }
    
    fun searchReceipts(query: String) {
        _searchQuery.value = query
    }
    
    fun filterByCategory(category: ReceiptCategory?) {
        _selectedCategory.value = category
        _searchQuery.value = "" // Reset search when filtering
    }
    
    fun clearFilters() {
        _selectedCategory.value = null
        _searchQuery.value = ""
    }
}

class ReceiptsViewModelFactory(private val repository: ReceiptRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReceiptsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReceiptsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
