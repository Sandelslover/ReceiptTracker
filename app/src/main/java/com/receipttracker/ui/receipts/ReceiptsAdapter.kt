package com.receipttracker.ui.receipts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.receipttracker.databinding.ItemReceiptBinding // Generated binding class
import com.receipttracker.data.model.Receipt // Adjust to your model package

class ReceiptsAdapter : RecyclerView.Adapter<ReceiptsAdapter.ReceiptViewHolder>() {
    private val receipts = mutableListOf<Receipt>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val binding = ItemReceiptBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ReceiptViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        holder.bind(receipts[position])
    }

    override fun getItemCount(): Int = receipts.size

    fun submitList(newReceipts: List<Receipt>) {
        receipts.clear()
        receipts.addAll(newReceipts)
        notifyDataSetChanged()
    }

    class ReceiptViewHolder(private val binding: ItemReceiptBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(receipt: Receipt) {
            binding.categoryText.text = receipt.category // Fixed: Use binding
            // Other bindings, e.g., binding.vendorText.text = receipt.vendor
        }
    }
}