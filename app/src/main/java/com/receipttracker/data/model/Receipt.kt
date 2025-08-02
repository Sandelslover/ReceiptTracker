package com.receipttracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "receipts")
data class Receipt(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val vendor: String,
    val total: Double,
    val date: Date,
    val category: ReceiptCategory,
    val imagePath: String? = null,
    val notes: String? = null,
    val warrantyExpiryDate: Date? = null,
    val isWarrantyAlertEnabled: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
) : Parcelable

enum class ReceiptCategory(val displayName: String) {
    FOOD("Food"),
    ENTERTAINMENT("Entertainment"),
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    TRANSPORTATION("Transportation"),
    HEALTHCARE("Healthcare"),
    UTILITIES("Utilities"),
    GROCERIES("Groceries"),
    EDUCATION("Education"),
    HOME_GARDEN("Home & Garden"),
    TRAVEL("Travel"),
    BUSINESS("Business"),
    OTHER("Other")
}
