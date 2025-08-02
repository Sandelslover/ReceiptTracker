package com.receipttracker.ui.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.receipttracker.R
import com.receipttracker.data.database.CategorySummary
import com.receipttracker.data.model.ReceiptCategory
import com.receipttracker.databinding.ItemCategorySummaryBinding

class CategorySummaryAdapter : ListAdapter<CategorySummary, CategorySummaryAdapter.CategorySummaryViewHolder>(CategorySummaryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorySummaryViewHolder {
        val binding = ItemCategorySummaryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategorySummaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategorySummaryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CategorySummaryViewHolder(
        private val binding: ItemCategorySummaryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(categorySummary: CategorySummary) {
            binding.apply {
                categoryNameText.text = categorySummary.category.displayName
                categoryAmountText.text = String.format("$%.2f", categorySummary.totalAmount)
                
                // Set category icon and color
                val (iconRes, colorRes) = getCategoryIconAndColor(categorySummary.category)
                categoryIcon.setImageResource(iconRes)
                categoryIcon.setColorFilter(itemView.context.getColor(colorRes))
                
                // Calculate percentage if we have total data
                val totalSpent = currentList.sumOf { it.totalAmount }
                if (totalSpent > 0) {
                    val percentage = (categorySummary.totalAmount / totalSpent * 100).toInt()
                    categoryPercentageText.text = "$percentage%"
                    categoryProgressBar.progress = percentage
                } else {
                    categoryPercentageText.text = "0%"
                    categoryProgressBar.progress = 0
                }
            }
        }

        private fun getCategoryIconAndColor(category: ReceiptCategory): Pair<Int, Int> {
            return when (category) {
                ReceiptCategory.FOOD -> Pair(R.drawable.ic_restaurant, R.color.category_food)
                ReceiptCategory.ENTERTAINMENT -> Pair(R.drawable.ic_movie, R.color.category_entertainment)
                ReceiptCategory.ELECTRONICS -> Pair(R.drawable.ic_devices, R.color.category_electronics)
                ReceiptCategory.CLOTHING -> Pair(R.drawable.ic_checkroom, R.color.category_clothing)
                ReceiptCategory.TRANSPORTATION -> Pair(R.drawable.ic_directions_car, R.color.category_transportation)
                ReceiptCategory.HEALTHCARE -> Pair(R.drawable.ic_local_hospital, R.color.category_healthcare)
                ReceiptCategory.UTILITIES -> Pair(R.drawable.ic_flash_on, R.color.category_utilities)
                ReceiptCategory.GROCERIES -> Pair(R.drawable.ic_shopping_cart, R.color.category_groceries)
                ReceiptCategory.EDUCATION -> Pair(R.drawable.ic_school, R.color.category_education)
                ReceiptCategory.HOME_GARDEN -> Pair(R.drawable.ic_home, R.color.category_home_garden)
                ReceiptCategory.TRAVEL -> Pair(R.drawable.ic_flight, R.color.category_travel)
                ReceiptCategory.BUSINESS -> Pair(R.drawable.ic_business, R.color.category_business)
                ReceiptCategory.OTHER -> Pair(R.drawable.ic_category, R.color.category_other)
            }
        }
    }
}

class CategorySummaryDiffCallback : DiffUtil.ItemCallback<CategorySummary>() {
    override fun areItemsTheSame(oldItem: CategorySummary, newItem: CategorySummary): Boolean {
        return oldItem.category == newItem.category
    }

    override fun areContentsTheSame(oldItem: CategorySummary, newItem: CategorySummary): Boolean {
        return oldItem == newItem
    }
}
