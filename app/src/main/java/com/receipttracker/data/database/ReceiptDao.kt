package com.receipttracker.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.receipttracker.data.model.Receipt
import com.receipttracker.data.model.ReceiptCategory
import java.util.Date

@Dao
interface ReceiptDao {
    @Query("SELECT * FROM receipts ORDER BY date DESC")
    fun getAllReceipts(): LiveData<List<Receipt>>

    @Query("SELECT * FROM receipts WHERE id = :id")
    suspend fun getReceiptById(id: Long): Receipt?

    @Query("SELECT * FROM receipts WHERE category = :category ORDER BY date DESC")
    fun getReceiptsByCategory(category: ReceiptCategory): LiveData<List<Receipt>>

    @Query("SELECT * FROM receipts WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getReceiptsByDateRange(startDate: Date, endDate: Date): LiveData<List<Receipt>>

    @Query("SELECT * FROM receipts WHERE isWarrantyAlertEnabled = 1 AND warrantyExpiryDate IS NOT NULL AND warrantyExpiryDate > :currentDate ORDER BY warrantyExpiryDate ASC")
    suspend fun getReceiptsWithWarrantyAlerts(currentDate: Date): List<Receipt>

    @Query("SELECT category, SUM(total) as totalAmount FROM receipts WHERE date BETWEEN :startDate AND :endDate GROUP BY category")
    suspend fun getMonthlySummaryByCategory(startDate: Date, endDate: Date): List<CategorySummary>

    @Query("SELECT SUM(total) FROM receipts WHERE date BETWEEN :startDate AND :endDate")
    suspend fun getTotalSpentInPeriod(startDate: Date, endDate: Date): Double?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReceipt(receipt: Receipt): Long

    @Update
    suspend fun updateReceipt(receipt: Receipt)

    @Delete
    suspend fun deleteReceipt(receipt: Receipt)

    @Query("DELETE FROM receipts WHERE id = :id")
    suspend fun deleteReceiptById(id: Long)

    @Query("SELECT * FROM receipts WHERE vendor LIKE '%' || :searchQuery || '%' OR notes LIKE '%' || :searchQuery || '%' ORDER BY date DESC")
    fun searchReceipts(searchQuery: String): LiveData<List<Receipt>>
}

data class CategorySummary(
    val category: ReceiptCategory,
    val totalAmount: Double
)
