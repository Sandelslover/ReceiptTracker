package com.receipttracker.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.receipttracker.MainActivity
import com.receipttracker.R
import com.receipttracker.data.database.ReceiptDatabase
import com.receipttracker.data.repository.ReceiptRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit

class WarrantyAlertWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val database = ReceiptDatabase.getDatabase(applicationContext)
            val repository = ReceiptRepository(database.receiptDao())
            
            val currentDate = Date()
            val receiptsWithWarranty = repository.getReceiptsWithWarrantyAlerts(currentDate)
            
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, 30) // Check for warranties expiring in 30 days
            val thirtyDaysFromNow = calendar.time
            
            val expiringReceipts = receiptsWithWarranty.filter { receipt ->
                receipt.warrantyExpiryDate?.let { expiryDate ->
                    expiryDate.after(currentDate) && expiryDate.before(thirtyDaysFromNow)
                } ?: false
            }
            
            if (expiringReceipts.isNotEmpty()) {
                showWarrantyNotification(expiringReceipts)
            }
            
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private fun showWarrantyNotification(receipts: List<com.receipttracker.data.model.Receipt>) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        
        createNotificationChannel(notificationManager)
        
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val title = if (receipts.size == 1) {
            "Warranty Expiring Soon"
        } else {
            "${receipts.size} Warranties Expiring Soon"
        }
        
        val content = if (receipts.size == 1) {
            "Warranty for ${receipts.first().vendor} expires soon"
        } else {
            "Multiple warranties are expiring within 30 days"
        }
        
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_warning)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Warranty Alerts",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for expiring warranties"
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val CHANNEL_ID = "warranty_alerts"
        private const val NOTIFICATION_ID = 1001
        private const val WORK_NAME = "warranty_check"

        fun scheduleWarrantyCheck(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<WarrantyAlertWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
        }

        fun cancelWarrantyCheck(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
        }
    }
}
