package com.receipttracker.data.database

import androidx.room.TypeConverter
import com.receipttracker.data.model.ReceiptCategory
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromReceiptCategory(category: ReceiptCategory): String {
        return category.name
    }

    @TypeConverter
    fun toReceiptCategory(categoryString: String): ReceiptCategory {
        return ReceiptCategory.valueOf(categoryString)
    }
}
