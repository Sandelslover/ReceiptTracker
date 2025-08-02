package com.receipttracker.ui.receipts;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\nJ\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0007R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/receipttracker/ui/receipts/ReceiptsViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/receipttracker/data/repository/ReceiptRepository;", "(Lcom/receipttracker/data/repository/ReceiptRepository;)V", "_searchQuery", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "_selectedCategory", "Lcom/receipttracker/data/model/ReceiptCategory;", "receipts", "Landroidx/lifecycle/LiveData;", "", "Lcom/receipttracker/data/model/Receipt;", "getReceipts", "()Landroidx/lifecycle/LiveData;", "clearFilters", "", "filterByCategory", "category", "searchReceipts", "query", "app_debug"})
public final class ReceiptsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.receipttracker.data.repository.ReceiptRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.receipttracker.data.model.ReceiptCategory> _selectedCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.receipttracker.data.model.Receipt>> receipts = null;
    
    public ReceiptsViewModel(@org.jetbrains.annotations.NotNull()
    com.receipttracker.data.repository.ReceiptRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.receipttracker.data.model.Receipt>> getReceipts() {
        return null;
    }
    
    public final void searchReceipts(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void filterByCategory(@org.jetbrains.annotations.Nullable()
    com.receipttracker.data.model.ReceiptCategory category) {
    }
    
    public final void clearFilters() {
    }
}