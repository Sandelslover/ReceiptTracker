package com.receipttracker.ui.receipts

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.receipttracker.R
import com.receipttracker.data.database.ReceiptDatabase
import com.receipttracker.data.repository.ReceiptRepository
import com.receipttracker.databinding.FragmentReceiptsBinding
import com.receipttracker.ui.receipt.ReceiptDetailActivity

class ReceiptsFragment : Fragment() {
    private var _binding: FragmentReceiptsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: ReceiptsViewModel
    private lateinit var adapter: ReceiptsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReceiptsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViewModel()
        setupRecyclerView()
        setupObservers()
        setupSearch()
        setHasOptionsMenu(true)
    }

    private fun setupViewModel() {
        val database = ReceiptDatabase.getDatabase(requireContext())
        val repository = ReceiptRepository(database.receiptDao())
        val factory = ReceiptsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ReceiptsViewModel::class.java]
    }

    private fun setupRecyclerView() {
        adapter = ReceiptsAdapter { receipt ->
            val intent = Intent(requireContext(), ReceiptDetailActivity::class.java)
            intent.putExtra("receipt_id", receipt.id)
            startActivity(intent)
        }
        
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.receipts.observe(viewLifecycleOwner) { receipts ->
            adapter.submitList(receipts)
            binding.emptyView.visibility = if (receipts.isEmpty()) View.VISIBLE else View.GONE
            binding.recyclerView.visibility = if (receipts.isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchReceipts(newText ?: "")
                return true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.receipts_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                showFilterDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showFilterDialog() {
        val dialog = FilterDialogFragment { category ->
            viewModel.filterByCategory(category)
        }
        dialog.show(parentFragmentManager, "filter_dialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
