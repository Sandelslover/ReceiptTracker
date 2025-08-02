package com.receipttracker.utils

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.receipttracker.data.model.ReceiptCategory
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

data class OCRResult(
    val vendor: String,
    val total: Double,
    val date: Date,
    val suggestedCategory: ReceiptCategory,
    val rawText: String
)

class OCRService {
    private val textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    
    // Common date formats found on receipts
    private val dateFormats = listOf(
        SimpleDateFormat("MM/dd/yyyy", Locale.US),
        SimpleDateFormat("dd/MM/yyyy", Locale.US),
        SimpleDateFormat("yyyy-MM-dd", Locale.US),
        SimpleDateFormat("MMM dd, yyyy", Locale.US),
        SimpleDateFormat("dd MMM yyyy", Locale.US),
        SimpleDateFormat("MM-dd-yyyy", Locale.US),
        SimpleDateFormat("dd-MM-yyyy", Locale.US)
    )
    
    // Patterns for extracting information
    private val totalPatterns = listOf(
        Pattern.compile("(?i)total[:\\s]*\\$?([0-9]+\\.?[0-9]*)", Pattern.CASE_INSENSITIVE),
        Pattern.compile("(?i)amount[:\\s]*\\$?([0-9]+\\.?[0-9]*)", Pattern.CASE_INSENSITIVE),
        Pattern.compile("\\$([0-9]+\\.?[0-9]*)", Pattern.CASE_INSENSITIVE),
        Pattern.compile("([0-9]+\\.[0-9]{2})\\s*$", Pattern.MULTILINE)
    )
    
    private val datePattern = Pattern.compile(
        "\\b(\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}|\\d{4}[/-]\\d{1,2}[/-]\\d{1,2}|\\w{3}\\s+\\d{1,2},?\\s+\\d{4}|\\d{1,2}\\s+\\w{3}\\s+\\d{4})\\b"
    )
    
    // Vendor keywords for category suggestion
    private val categoryKeywords = mapOf(
        ReceiptCategory.FOOD to listOf("restaurant", "cafe", "pizza", "burger", "food", "diner", "bistro", "grill"),
        ReceiptCategory.GROCERIES to listOf("market", "grocery", "supermarket", "walmart", "target", "costco", "safeway"),
        ReceiptCategory.ELECTRONICS to listOf("best buy", "electronics", "apple", "samsung", "computer", "phone", "tech"),
        ReceiptCategory.ENTERTAINMENT to listOf("cinema", "movie", "theater", "netflix", "spotify", "game", "entertainment"),
        ReceiptCategory.CLOTHING to listOf("clothing", "fashion", "apparel", "nike", "adidas", "zara", "h&m"),
        ReceiptCategory.TRANSPORTATION to listOf("gas", "fuel", "uber", "lyft", "taxi", "transport", "parking"),
        ReceiptCategory.HEALTHCARE to listOf("pharmacy", "hospital", "clinic", "medical", "doctor", "cvs", "walgreens"),
        ReceiptCategory.UTILITIES to listOf("electric", "water", "gas", "internet", "phone", "utility", "bill")
    )

    suspend fun processReceiptImage(bitmap: Bitmap): OCRResult = suspendCoroutine { continuation ->
        val image = InputImage.fromBitmap(bitmap, 0)
        
        textRecognizer.process(image)
            .addOnSuccessListener { visionText ->
                try {
                    val rawText = visionText.text
                    val vendor = extractVendor(rawText)
                    val total = extractTotal(rawText)
                    val date = extractDate(rawText)
                    val category = suggestCategory(vendor, rawText)
                    
                    val result = OCRResult(
                        vendor = vendor,
                        total = total,
                        date = date,
                        suggestedCategory = category,
                        rawText = rawText
                    )
                    continuation.resume(result)
                } catch (e: Exception) {
                    continuation.resumeWithException(e)
                }
            }
            .addOnFailureListener { e ->
                continuation.resumeWithException(e)
            }
    }
    
    private fun extractVendor(text: String): String {
        val lines = text.split("\n").filter { it.trim().isNotEmpty() }
        
        // Usually the vendor name is in the first few lines
        for (i in 0 until minOf(3, lines.size)) {
            val line = lines[i].trim()
            if (line.length > 3 && !line.matches(Regex(".*\\d{4}.*")) && 
                !line.contains("receipt", true) && !line.contains("invoice", true)) {
                return line
            }
        }
        
        return "Unknown Vendor"
    }
    
    private fun extractTotal(text: String): Double {
        for (pattern in totalPatterns) {
            val matcher = pattern.matcher(text)
            val amounts = mutableListOf<Double>()
            
            while (matcher.find()) {
                try {
                    val amountStr = matcher.group(1) ?: matcher.group(0)
                    val amount = amountStr.replace("$", "").toDouble()
                    amounts.add(amount)
                } catch (e: NumberFormatException) {
                    continue
                }
            }
            
            if (amounts.isNotEmpty()) {
                // Return the largest amount found (likely the total)
                return amounts.maxOrNull() ?: 0.0
            }
        }
        
        return 0.0
    }
    
    private fun extractDate(text: String): Date {
        val matcher = datePattern.matcher(text)
        
        while (matcher.find()) {
            val dateStr = matcher.group()
            
            for (format in dateFormats) {
                try {
                    return format.parse(dateStr) ?: continue
                } catch (e: Exception) {
                    continue
                }
            }
        }
        
        // If no date found, return current date
        return Date()
    }
    
    private fun suggestCategory(vendor: String, text: String): ReceiptCategory {
        val combinedText = "$vendor $text".lowercase()
        
        for ((category, keywords) in categoryKeywords) {
            for (keyword in keywords) {
                if (combinedText.contains(keyword)) {
                    return category
                }
            }
        }
        
        return ReceiptCategory.OTHER
    }
}
