package com.receipttracker.data.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0016\u001a\u00020\u0017H\'J$\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\'J\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\r2\u0006\u0010\u001a\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u001bJ \u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u001e\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010 \u001a\u00020!H\'J\u0016\u0010\"\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006#"}, d2 = {"Lcom/receipttracker/data/database/ReceiptDao;", "", "deleteReceipt", "", "receipt", "Lcom/receipttracker/data/model/Receipt;", "(Lcom/receipttracker/data/model/Receipt;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReceiptById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllReceipts", "Landroidx/lifecycle/LiveData;", "", "getMonthlySummaryByCategory", "Lcom/receipttracker/data/database/CategorySummary;", "startDate", "Ljava/util/Date;", "endDate", "(Ljava/util/Date;Ljava/util/Date;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReceiptById", "getReceiptsByCategory", "category", "Lcom/receipttracker/data/model/ReceiptCategory;", "getReceiptsByDateRange", "getReceiptsWithWarrantyAlerts", "currentDate", "(Ljava/util/Date;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalSpentInPeriod", "", "insertReceipt", "searchReceipts", "searchQuery", "", "updateReceipt", "app_debug"})
@androidx.room.Dao()
public abstract interface ReceiptDao {
    
    @androidx.room.Query(value = "SELECT * FROM receipts ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.receipttracker.data.model.Receipt>> getAllReceipts();
    
    @androidx.room.Query(value = "SELECT * FROM receipts WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReceiptById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.receipttracker.data.model.Receipt> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM receipts WHERE category = :category ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.receipttracker.data.model.Receipt>> getReceiptsByCategory(@org.jetbrains.annotations.NotNull()
    com.receipttracker.data.model.ReceiptCategory category);
    
    @androidx.room.Query(value = "SELECT * FROM receipts WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.receipttracker.data.model.Receipt>> getReceiptsByDateRange(@org.jetbrains.annotations.NotNull()
    java.util.Date startDate, @org.jetbrains.annotations.NotNull()
    java.util.Date endDate);
    
    @androidx.room.Query(value = "SELECT * FROM receipts WHERE isWarrantyAlertEnabled = 1 AND warrantyExpiryDate IS NOT NULL AND warrantyExpiryDate > :currentDate ORDER BY warrantyExpiryDate ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReceiptsWithWarrantyAlerts(@org.jetbrains.annotations.NotNull()
    java.util.Date currentDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.receipttracker.data.model.Receipt>> $completion);
    
    @androidx.room.Query(value = "SELECT category, SUM(total) as totalAmount FROM receipts WHERE date BETWEEN :startDate AND :endDate GROUP BY category")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMonthlySummaryByCategory(@org.jetbrains.annotations.NotNull()
    java.util.Date startDate, @org.jetbrains.annotations.NotNull()
    java.util.Date endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.receipttracker.data.database.CategorySummary>> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(total) FROM receipts WHERE date BETWEEN :startDate AND :endDate")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalSpentInPeriod(@org.jetbrains.annotations.NotNull()
    java.util.Date startDate, @org.jetbrains.annotations.NotNull()
    java.util.Date endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertReceipt(@org.jetbrains.annotations.NotNull()
    com.receipttracker.data.model.Receipt receipt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateReceipt(@org.jetbrains.annotations.NotNull()
    com.receipttracker.data.model.Receipt receipt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteReceipt(@org.jetbrains.annotations.NotNull()
    com.receipttracker.data.model.Receipt receipt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM receipts WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteReceiptById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM receipts WHERE vendor LIKE \'%\' || :searchQuery || \'%\' OR notes LIKE \'%\' || :searchQuery || \'%\' ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.receipttracker.data.model.Receipt>> searchReceipts(@org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery);
}