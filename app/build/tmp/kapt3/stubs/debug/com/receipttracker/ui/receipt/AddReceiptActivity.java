package com.receipttracker.ui.receipt;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\bH\u0002J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u000fH\u0002J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0002J\b\u0010\u0018\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/receipttracker/ui/receipt/AddReceiptActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/receipttracker/databinding/ActivityAddReceiptBinding;", "dateFormat", "Ljava/text/SimpleDateFormat;", "imagePath", "", "repository", "Lcom/receipttracker/data/repository/ReceiptRepository;", "selectedDate", "Ljava/util/Date;", "warrantyDate", "handleIntent", "", "loadReceiptImage", "path", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveReceipt", "setupUI", "showDatePicker", "showWarrantyDatePicker", "app_debug"})
public final class AddReceiptActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.receipttracker.databinding.ActivityAddReceiptBinding binding;
    private com.receipttracker.data.repository.ReceiptRepository repository;
    @org.jetbrains.annotations.NotNull()
    private java.util.Date selectedDate;
    @org.jetbrains.annotations.Nullable()
    private java.util.Date warrantyDate;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String imagePath;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dateFormat = null;
    
    public AddReceiptActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupUI() {
    }
    
    private final void handleIntent() {
    }
    
    private final void loadReceiptImage(java.lang.String path) {
    }
    
    private final void showDatePicker() {
    }
    
    private final void showWarrantyDatePicker() {
    }
    
    private final void saveReceipt() {
    }
}