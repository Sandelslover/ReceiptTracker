package com.receipttracker.ui.summary;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/receipttracker/ui/summary/CategorySummaryDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/receipttracker/data/database/CategorySummary;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
public final class CategorySummaryDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.receipttracker.data.database.CategorySummary> {
    
    public CategorySummaryDiffCallback() {
        super();
    }
    
    @java.lang.Override()
    public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
    com.receipttracker.data.database.CategorySummary oldItem, @org.jetbrains.annotations.NotNull()
    com.receipttracker.data.database.CategorySummary newItem) {
        return false;
    }
    
    @java.lang.Override()
    public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
    com.receipttracker.data.database.CategorySummary oldItem, @org.jetbrains.annotations.NotNull()
    com.receipttracker.data.database.CategorySummary newItem) {
        return false;
    }
}