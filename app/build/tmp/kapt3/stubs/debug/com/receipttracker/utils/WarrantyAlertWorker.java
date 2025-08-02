package com.receipttracker.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u000e\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002\u00a8\u0006\u0013"}, d2 = {"Lcom/receipttracker/utils/WarrantyAlertWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "createNotificationChannel", "", "notificationManager", "Landroid/app/NotificationManager;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showWarrantyNotification", "receipts", "", "Lcom/receipttracker/data/model/Receipt;", "Companion", "app_debug"})
public final class WarrantyAlertWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "warranty_alerts";
    private static final int NOTIFICATION_ID = 1001;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String WORK_NAME = "warranty_check";
    @org.jetbrains.annotations.NotNull()
    public static final com.receipttracker.utils.WarrantyAlertWorker.Companion Companion = null;
    
    public WarrantyAlertWorker(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters params) {
        super(null, null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> $completion) {
        return null;
    }
    
    private final void showWarrantyNotification(java.util.List<com.receipttracker.data.model.Receipt> receipts) {
    }
    
    private final void createNotificationChannel(android.app.NotificationManager notificationManager) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/receipttracker/utils/WarrantyAlertWorker$Companion;", "", "()V", "CHANNEL_ID", "", "NOTIFICATION_ID", "", "WORK_NAME", "cancelWarrantyCheck", "", "context", "Landroid/content/Context;", "scheduleWarrantyCheck", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void scheduleWarrantyCheck(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
        
        public final void cancelWarrantyCheck(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
    }
}