package com.receipttracker.ui.receipts

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.receipttracker.data.model.ReceiptCategory

class FilterDialogFragment(
    private val onCategorySelected: (ReceiptCategory?) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val categories = arrayOf("All Categories") + ReceiptCategory.values().map { it.displayName }
        
        return AlertDialog.Builder(requireContext())
            .setTitle("Filter by Category")
            .setItems(categories.toTypedArray()) { _, which ->
                val selectedCategory = if (which == 0) {
                    null // All categories
                } else {
                    ReceiptCategory.values()[which - 1]
                }
                onCategorySelected(selectedCategory)
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}
