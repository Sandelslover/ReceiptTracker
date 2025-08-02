package com.receipttracker.data.repository

import androidx.lifecycle.LiveData
import com.receipttracker.data.database.CategorySummary
import com.receipttracker.data.database.ReceiptDao
import com.receipttracker.data.model.Receipt
import com.receipttracker.data.model.ReceiptCategory
import java.util.Date

class ReceiptRepository(private val receiptDao: ReceiptDao) {

    fun getAllReceipts(): LiveData<List<Receipt>> = receiptDao.getAllReceipts()

    suspend fun getReceiptById(id: Long): Receipt? = receiptDao.getReceiptById(id)

    fun getReceiptsByCategory(category: ReceiptCategory): LiveData<List<Receipt>> =
        receiptDao.getReceiptsByCategory(category)

    fun getReceiptsByDateRange(startDate: Date, endDate: Date): LiveData<List<Receipt>> =
        receiptDao.getReceiptsByDateRange(startDate, endDate)

    suspend fun getReceiptsWithWarrantyAlerts(currentDate: Date): List<Receipt> =
        receiptDao.getReceiptsWithWarrantyAlerts(currentDate)

    suspend fun getMonthlySummaryByCategory(startDate: Date, endDate: Date): List<CategorySummary> =
        receiptDao.getMonthlySummaryByCategory(startDate, endDate)

    suspend fun getTotalSpentInPeriod(startDate: Date, endDate: Date): Double =
        receiptDao.getTotalSpentInPeriod(startDate, endDate) ?: 0.0

    suspend fun insertReceipt(receipt: Receipt): Long = receiptDao.insertReceipt(receipt)

    suspend fun updateReceipt(receipt: Receipt) = receiptDao.updateReceipt(receipt)

    suspend fun deleteReceipt(receipt: Receipt) = receiptDao.deleteReceipt(receipt)

    suspend fun deleteReceiptById(id: Long) = receiptDao.deleteReceiptById(id)

    fun searchReceipts(searchQuery: String): LiveData<List<Receipt>> =
        receiptDao.searchReceipts(searchQuery)
}
