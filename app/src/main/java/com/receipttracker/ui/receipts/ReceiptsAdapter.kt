package com.receipttracker.ui.receipts

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.receipttracker.R
import com.receipttracker.data.model.Receipt
import com.receipttracker.databinding.ItemReceiptBinding
import java.text.SimpleDateFormat
import java.util.*

class ReceiptsAdapter(
    private val onReceiptClick: (Receipt) -> Unit
) : ListAdapter<Receipt, ReceiptsAdapter.ReceiptViewHolder>(ReceiptDiffCallback()) {

    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val binding = ItemReceiptBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReceiptViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ReceiptViewHolder(
        private val binding: ItemReceiptBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(receipt: Receipt) {
            binding.apply {
                vendorText.text = receipt.vendor
                totalText.text = String.format("$%.2f", receipt.total)
                dateText.text = dateFormat.format(receipt.date)
                categoryText.text = receipt.category.displayName

                // Set category color
                val categoryColor = getCategoryColor(receipt.category)
                categoryChip.setChipBackgroundColorResource(categoryColor)

                // Load receipt image if available
                if (!receipt.imagePath.isNullOrEmpty()) {
                    receiptImage.visibility = android.view.View.VISIBLE
                    Glide.with(itemView.context)
                        .load(Uri.parse(receipt.imagePath))
                        .centerCrop()
                        .placeholder(R.drawable.ic_receipt_placeholder)
                        .into(receiptImage)
                } else {
                    receiptImage.visibility = android.view.View.GONE
                }

                // Show warranty indicator if enabled
                warrantyIndicator.visibility = if (receipt.isWarrantyAlertEnabled) {
                    android.view.View.VISIBLE
                } else {
                    android.view.View.GONE
                }

                root.setOnClickListener {
                    onReceiptClick(receipt)
                }
            }
        }

        private fun getCategoryColor(category: com.receipttracker.data.model.ReceiptCategory): Int {
            return when (category) {
                com.receipttracker.data.model.ReceiptCategory.FOOD -> R.color.category_food
                com.receipttracker.data.model.ReceiptCategory.ENTERTAINMENT -> R.color.category_entertainment
                com.receipttracker.data.model.ReceiptCategory.ELECTRONICS -> R.color.category_electronics
                com.receipttracker.data.model.ReceiptCategory.CLOTHING -> R.color.category_clothing
                com.receipttracker.data.model.ReceiptCategory.TRANSPORTATION -> R.color.category_transportation
                com.receipttracker.data.model.ReceiptCategory.HEALTHCARE -> R.color.category_healthcare
                com.receipttracker.data.model.ReceiptCategory.UTILITIES -> R.color.category_utilities
                com.receipttracker.data.model.ReceiptCategory.GROCERIES -> R.color.category_groceries
                com.receipttracker.data.model.ReceiptCategory.EDUCATION -> R.color.category_education
                com.receipttracker.data.model.ReceiptCategory.HOME_GARDEN -> R.color.category_home_garden
                com.receipttracker.data.model.ReceiptCategory.TRAVEL -> R.color.category_travel
                com.receipttracker.data.model.ReceiptCategory.BUSINESS -> R.color.category_business
                com.receipttracker.data.model.ReceiptCategory.OTHER -> R.color.category_other
            }
        }
    }
}

class ReceiptDiffCallback : DiffUtil.ItemCallback<Receipt>() {
    override fun areItemsTheSame(oldItem: Receipt, newItem: Receipt): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Receipt, newItem: Receipt): Boolean {
        return oldItem == newItem
    }
}
