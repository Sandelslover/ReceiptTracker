package com.receipttracker.ui.summary;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u000eB\u0005\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016\u00a8\u0006\u000f"}, d2 = {"Lcom/receipttracker/ui/summary/CategorySummaryAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/receipttracker/data/database/CategorySummary;", "Lcom/receipttracker/ui/summary/CategorySummaryAdapter$CategorySummaryViewHolder;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "CategorySummaryViewHolder", "app_debug"})
public final class CategorySummaryAdapter extends androidx.recyclerview.widget.ListAdapter<com.receipttracker.data.database.CategorySummary, com.receipttracker.ui.summary.CategorySummaryAdapter.CategorySummaryViewHolder> {
    
    public CategorySummaryAdapter() {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.receipttracker.ui.summary.CategorySummaryAdapter.CategorySummaryViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.receipttracker.ui.summary.CategorySummaryAdapter.CategorySummaryViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001c\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/receipttracker/ui/summary/CategorySummaryAdapter$CategorySummaryViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/receipttracker/databinding/ItemCategorySummaryBinding;", "(Lcom/receipttracker/ui/summary/CategorySummaryAdapter;Lcom/receipttracker/databinding/ItemCategorySummaryBinding;)V", "bind", "", "categorySummary", "Lcom/receipttracker/data/database/CategorySummary;", "getCategoryIconAndColor", "Lkotlin/Pair;", "", "category", "Lcom/receipttracker/data/model/ReceiptCategory;", "app_debug"})
    public final class CategorySummaryViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.receipttracker.databinding.ItemCategorySummaryBinding binding = null;
        
        public CategorySummaryViewHolder(@org.jetbrains.annotations.NotNull()
        com.receipttracker.databinding.ItemCategorySummaryBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.receipttracker.data.database.CategorySummary categorySummary) {
        }
        
        private final kotlin.Pair<java.lang.Integer, java.lang.Integer> getCategoryIconAndColor(com.receipttracker.data.model.ReceiptCategory category) {
            return null;
        }
    }
}