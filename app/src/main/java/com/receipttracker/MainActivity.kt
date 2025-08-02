package com.receipttracker

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.receipttracker.databinding.ActivityMainBinding
import com.receipttracker.ui.camera.CameraActivity
import com.receipttracker.ui.receipt.AddReceiptActivity
import com.receipttracker.ui.receipts.ReceiptsFragment
import com.receipttracker.ui.summary.SummaryFragment
import com.receipttracker.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupBottomNavigation()
        setupFab()

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(ReceiptsFragment())
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_receipts -> {
                    loadFragment(ReceiptsFragment())
                    true
                }
                R.id.nav_summary -> {
                    loadFragment(SummaryFragment())
                    true
                }
                R.id.nav_settings -> {
                    loadFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun setupFab() {
        binding.fab.setOnClickListener {
            showAddReceiptOptions()
        }
    }

    private fun showAddReceiptOptions() {
        val bottomSheet = com.google.android.material.bottomsheet.BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_add_receipt, null)
        
        view.findViewById<android.widget.LinearLayout>(R.id.camera_option).setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
            bottomSheet.dismiss()
        }
        
        view.findViewById<android.widget.LinearLayout>(R.id.manual_option).setOnClickListener {
            startActivity(Intent(this, AddReceiptActivity::class.java))
            bottomSheet.dismiss()
        }
        
        bottomSheet.setContentView(view)
        bottomSheet.show()
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // TODO: Implement search functionality
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
