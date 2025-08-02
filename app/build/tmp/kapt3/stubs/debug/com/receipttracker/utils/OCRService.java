package com.receipttracker.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u0007H\u0002J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007H\u0002J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0018\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007H\u0002R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/receipttracker/utils/OCRService;", "", "()V", "categoryKeywords", "", "Lcom/receipttracker/data/model/ReceiptCategory;", "", "", "dateFormats", "Ljava/text/SimpleDateFormat;", "datePattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "textRecognizer", "Lcom/google/mlkit/vision/text/TextRecognizer;", "totalPatterns", "extractDate", "Ljava/util/Date;", "text", "extractTotal", "", "extractVendor", "processReceiptImage", "Lcom/receipttracker/utils/OCRResult;", "bitmap", "Landroid/graphics/Bitmap;", "(Landroid/graphics/Bitmap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "suggestCategory", "vendor", "app_debug"})
public final class OCRService {
    @org.jetbrains.annotations.NotNull()
    private final com.google.mlkit.vision.text.TextRecognizer textRecognizer = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.text.SimpleDateFormat> dateFormats = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.util.regex.Pattern> totalPatterns = null;
    private final java.util.regex.Pattern datePattern = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<com.receipttracker.data.model.ReceiptCategory, java.util.List<java.lang.String>> categoryKeywords = null;
    
    public OCRService() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object processReceiptImage(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.receipttracker.utils.OCRResult> $completion) {
        return null;
    }
    
    private final java.lang.String extractVendor(java.lang.String text) {
        return null;
    }
    
    private final double extractTotal(java.lang.String text) {
        return 0.0;
    }
    
    private final java.util.Date extractDate(java.lang.String text) {
        return null;
    }
    
    private final com.receipttracker.data.model.ReceiptCategory suggestCategory(java.lang.String vendor, java.lang.String text) {
        return null;
    }
}