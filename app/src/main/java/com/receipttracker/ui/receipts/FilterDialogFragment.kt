package com.receipttracker.ui.receipts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.receipttracker.R // Ensure correct R import

class FilterDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout (e.g., R.layout.fragment_filter_dialog)
        return inflater.inflate(R.layout.fragment_filter_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Example: Setting up a dialog with category filters
        val categories = listOf("Electronics", "Clothing", "Travel") // Example categories
        val array = categories.toTypedArray() // Fixed: No explicit type needed
        // Example: Use array in an AlertDialog
        // AlertDialog.Builder(requireContext())
        //     .setItems(array) { _, which -> /* Handle selection */ }
        //     .show()
    }
}