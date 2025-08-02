package com.receipttracker.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.receipttracker.databinding.FragmentSettingsBinding
import com.receipttracker.utils.WarrantyAlertWorker

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        
        setupSettings()
    }

    private fun setupSettings() {
        // Warranty alerts setting
        val warrantyAlertsEnabled = sharedPreferences.getBoolean("warranty_alerts_enabled", true)
        binding.warrantyAlertsSwitch.isChecked = warrantyAlertsEnabled
        
        binding.warrantyAlertsSwitch.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit()
                .putBoolean("warranty_alerts_enabled", isChecked)
                .apply()
            
            if (isChecked) {
                WarrantyAlertWorker.scheduleWarrantyCheck(requireContext())
            } else {
                WarrantyAlertWorker.cancelWarrantyCheck(requireContext())
            }
        }

        // Export data button
        binding.exportDataButton.setOnClickListener {
            exportData()
        }

        // Import data button
        binding.importDataButton.setOnClickListener {
            importData()
        }

        // Clear all data button
        binding.clearDataButton.setOnClickListener {
            showClearDataConfirmation()
        }
    }

    private fun exportData() {
        // TODO: Implement data export functionality
        android.widget.Toast.makeText(requireContext(), "Export functionality coming soon", android.widget.Toast.LENGTH_SHORT).show()
    }

    private fun importData() {
        // TODO: Implement data import functionality
        android.widget.Toast.makeText(requireContext(), "Import functionality coming soon", android.widget.Toast.LENGTH_SHORT).show()
    }

    private fun showClearDataConfirmation() {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Clear All Data")
            .setMessage("Are you sure you want to delete all receipts? This action cannot be undone.")
            .setPositiveButton("Delete All") { _, _ ->
                clearAllData()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun clearAllData() {
        // TODO: Implement clear all data functionality
        android.widget.Toast.makeText(requireContext(), "Clear data functionality coming soon", android.widget.Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
