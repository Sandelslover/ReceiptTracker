package com.receipttracker.data.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.receipttracker.data.model.Receipt;
import com.receipttracker.data.model.ReceiptCategory;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ReceiptDao_Impl implements ReceiptDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Receipt> __insertionAdapterOfReceipt;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Receipt> __deletionAdapterOfReceipt;

  private final EntityDeletionOrUpdateAdapter<Receipt> __updateAdapterOfReceipt;

  private final SharedSQLiteStatement __preparedStmtOfDeleteReceiptById;

  public ReceiptDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReceipt = new EntityInsertionAdapter<Receipt>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `receipts` (`id`,`vendor`,`total`,`date`,`category`,`imagePath`,`notes`,`warrantyExpiryDate`,`isWarrantyAlertEnabled`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Receipt entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getVendor() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getVendor());
        }
        statement.bindDouble(3, entity.getTotal());
        final Long _tmp = __converters.dateToTimestamp(entity.getDate());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
        final String _tmp_1 = __converters.fromReceiptCategory(entity.getCategory());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        if (entity.getImagePath() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getImagePath());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        final Long _tmp_2 = __converters.dateToTimestamp(entity.getWarrantyExpiryDate());
        if (_tmp_2 == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp_2);
        }
        final int _tmp_3 = entity.isWarrantyAlertEnabled() ? 1 : 0;
        statement.bindLong(9, _tmp_3);
        final Long _tmp_4 = __converters.dateToTimestamp(entity.getCreatedAt());
        if (_tmp_4 == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, _tmp_4);
        }
        final Long _tmp_5 = __converters.dateToTimestamp(entity.getUpdatedAt());
        if (_tmp_5 == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, _tmp_5);
        }
      }
    };
    this.__deletionAdapterOfReceipt = new EntityDeletionOrUpdateAdapter<Receipt>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `receipts` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Receipt entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfReceipt = new EntityDeletionOrUpdateAdapter<Receipt>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `receipts` SET `id` = ?,`vendor` = ?,`total` = ?,`date` = ?,`category` = ?,`imagePath` = ?,`notes` = ?,`warrantyExpiryDate` = ?,`isWarrantyAlertEnabled` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Receipt entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getVendor() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getVendor());
        }
        statement.bindDouble(3, entity.getTotal());
        final Long _tmp = __converters.dateToTimestamp(entity.getDate());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
        final String _tmp_1 = __converters.fromReceiptCategory(entity.getCategory());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        if (entity.getImagePath() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getImagePath());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        final Long _tmp_2 = __converters.dateToTimestamp(entity.getWarrantyExpiryDate());
        if (_tmp_2 == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp_2);
        }
        final int _tmp_3 = entity.isWarrantyAlertEnabled() ? 1 : 0;
        statement.bindLong(9, _tmp_3);
        final Long _tmp_4 = __converters.dateToTimestamp(entity.getCreatedAt());
        if (_tmp_4 == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, _tmp_4);
        }
        final Long _tmp_5 = __converters.dateToTimestamp(entity.getUpdatedAt());
        if (_tmp_5 == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, _tmp_5);
        }
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteReceiptById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM receipts WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertReceipt(final Receipt receipt, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfReceipt.insertAndReturnId(receipt);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteReceipt(final Receipt receipt, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfReceipt.handle(receipt);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateReceipt(final Receipt receipt, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfReceipt.handle(receipt);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteReceiptById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteReceiptById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteReceiptById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Receipt>> getAllReceipts() {
    final String _sql = "SELECT * FROM receipts ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"receipts"}, false, new Callable<List<Receipt>>() {
      @Override
      @Nullable
      public List<Receipt> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVendor = CursorUtil.getColumnIndexOrThrow(_cursor, "vendor");
          final int _cursorIndexOfTotal = CursorUtil.getColumnIndexOrThrow(_cursor, "total");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfWarrantyExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "warrantyExpiryDate");
          final int _cursorIndexOfIsWarrantyAlertEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isWarrantyAlertEnabled");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<Receipt> _result = new ArrayList<Receipt>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Receipt _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpVendor;
            if (_cursor.isNull(_cursorIndexOfVendor)) {
              _tmpVendor = null;
            } else {
              _tmpVendor = _cursor.getString(_cursorIndexOfVendor);
            }
            final double _tmpTotal;
            _tmpTotal = _cursor.getDouble(_cursorIndexOfTotal);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = __converters.fromTimestamp(_tmp);
            final ReceiptCategory _tmpCategory;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toReceiptCategory(_tmp_1);
            final String _tmpImagePath;
            if (_cursor.isNull(_cursorIndexOfImagePath)) {
              _tmpImagePath = null;
            } else {
              _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Date _tmpWarrantyExpiryDate;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfWarrantyExpiryDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfWarrantyExpiryDate);
            }
            _tmpWarrantyExpiryDate = __converters.fromTimestamp(_tmp_2);
            final boolean _tmpIsWarrantyAlertEnabled;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsWarrantyAlertEnabled);
            _tmpIsWarrantyAlertEnabled = _tmp_3 != 0;
            final Date _tmpCreatedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __converters.fromTimestamp(_tmp_4);
            final Date _tmpUpdatedAt;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _tmpUpdatedAt = __converters.fromTimestamp(_tmp_5);
            _item = new Receipt(_tmpId,_tmpVendor,_tmpTotal,_tmpDate,_tmpCategory,_tmpImagePath,_tmpNotes,_tmpWarrantyExpiryDate,_tmpIsWarrantyAlertEnabled,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getReceiptById(final long id, final Continuation<? super Receipt> $completion) {
    final String _sql = "SELECT * FROM receipts WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Receipt>() {
      @Override
      @Nullable
      public Receipt call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVendor = CursorUtil.getColumnIndexOrThrow(_cursor, "vendor");
          final int _cursorIndexOfTotal = CursorUtil.getColumnIndexOrThrow(_cursor, "total");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfWarrantyExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "warrantyExpiryDate");
          final int _cursorIndexOfIsWarrantyAlertEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isWarrantyAlertEnabled");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final Receipt _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpVendor;
            if (_cursor.isNull(_cursorIndexOfVendor)) {
              _tmpVendor = null;
            } else {
              _tmpVendor = _cursor.getString(_cursorIndexOfVendor);
            }
            final double _tmpTotal;
            _tmpTotal = _cursor.getDouble(_cursorIndexOfTotal);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = __converters.fromTimestamp(_tmp);
            final ReceiptCategory _tmpCategory;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toReceiptCategory(_tmp_1);
            final String _tmpImagePath;
            if (_cursor.isNull(_cursorIndexOfImagePath)) {
              _tmpImagePath = null;
            } else {
              _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Date _tmpWarrantyExpiryDate;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfWarrantyExpiryDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfWarrantyExpiryDate);
            }
            _tmpWarrantyExpiryDate = __converters.fromTimestamp(_tmp_2);
            final boolean _tmpIsWarrantyAlertEnabled;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsWarrantyAlertEnabled);
            _tmpIsWarrantyAlertEnabled = _tmp_3 != 0;
            final Date _tmpCreatedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __converters.fromTimestamp(_tmp_4);
            final Date _tmpUpdatedAt;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _tmpUpdatedAt = __converters.fromTimestamp(_tmp_5);
            _result = new Receipt(_tmpId,_tmpVendor,_tmpTotal,_tmpDate,_tmpCategory,_tmpImagePath,_tmpNotes,_tmpWarrantyExpiryDate,_tmpIsWarrantyAlertEnabled,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Receipt>> getReceiptsByCategory(final ReceiptCategory category) {
    final String _sql = "SELECT * FROM receipts WHERE category = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromReceiptCategory(category);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"receipts"}, false, new Callable<List<Receipt>>() {
      @Override
      @Nullable
      public List<Receipt> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVendor = CursorUtil.getColumnIndexOrThrow(_cursor, "vendor");
          final int _cursorIndexOfTotal = CursorUtil.getColumnIndexOrThrow(_cursor, "total");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfWarrantyExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "warrantyExpiryDate");
          final int _cursorIndexOfIsWarrantyAlertEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isWarrantyAlertEnabled");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<Receipt> _result = new ArrayList<Receipt>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Receipt _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpVendor;
            if (_cursor.isNull(_cursorIndexOfVendor)) {
              _tmpVendor = null;
            } else {
              _tmpVendor = _cursor.getString(_cursorIndexOfVendor);
            }
            final double _tmpTotal;
            _tmpTotal = _cursor.getDouble(_cursorIndexOfTotal);
            final Date _tmpDate;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = __converters.fromTimestamp(_tmp_1);
            final ReceiptCategory _tmpCategory;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toReceiptCategory(_tmp_2);
            final String _tmpImagePath;
            if (_cursor.isNull(_cursorIndexOfImagePath)) {
              _tmpImagePath = null;
            } else {
              _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Date _tmpWarrantyExpiryDate;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfWarrantyExpiryDate)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfWarrantyExpiryDate);
            }
            _tmpWarrantyExpiryDate = __converters.fromTimestamp(_tmp_3);
            final boolean _tmpIsWarrantyAlertEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWarrantyAlertEnabled);
            _tmpIsWarrantyAlertEnabled = _tmp_4 != 0;
            final Date _tmpCreatedAt;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __converters.fromTimestamp(_tmp_5);
            final Date _tmpUpdatedAt;
            final Long _tmp_6;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _tmpUpdatedAt = __converters.fromTimestamp(_tmp_6);
            _item = new Receipt(_tmpId,_tmpVendor,_tmpTotal,_tmpDate,_tmpCategory,_tmpImagePath,_tmpNotes,_tmpWarrantyExpiryDate,_tmpIsWarrantyAlertEnabled,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Receipt>> getReceiptsByDateRange(final Date startDate, final Date endDate) {
    final String _sql = "SELECT * FROM receipts WHERE date BETWEEN ? AND ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final Long _tmp = __converters.dateToTimestamp(startDate);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    _argIndex = 2;
    final Long _tmp_1 = __converters.dateToTimestamp(endDate);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_1);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"receipts"}, false, new Callable<List<Receipt>>() {
      @Override
      @Nullable
      public List<Receipt> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVendor = CursorUtil.getColumnIndexOrThrow(_cursor, "vendor");
          final int _cursorIndexOfTotal = CursorUtil.getColumnIndexOrThrow(_cursor, "total");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfWarrantyExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "warrantyExpiryDate");
          final int _cursorIndexOfIsWarrantyAlertEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isWarrantyAlertEnabled");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<Receipt> _result = new ArrayList<Receipt>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Receipt _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpVendor;
            if (_cursor.isNull(_cursorIndexOfVendor)) {
              _tmpVendor = null;
            } else {
              _tmpVendor = _cursor.getString(_cursorIndexOfVendor);
            }
            final double _tmpTotal;
            _tmpTotal = _cursor.getDouble(_cursorIndexOfTotal);
            final Date _tmpDate;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = __converters.fromTimestamp(_tmp_2);
            final ReceiptCategory _tmpCategory;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toReceiptCategory(_tmp_3);
            final String _tmpImagePath;
            if (_cursor.isNull(_cursorIndexOfImagePath)) {
              _tmpImagePath = null;
            } else {
              _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Date _tmpWarrantyExpiryDate;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfWarrantyExpiryDate)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfWarrantyExpiryDate);
            }
            _tmpWarrantyExpiryDate = __converters.fromTimestamp(_tmp_4);
            final boolean _tmpIsWarrantyAlertEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsWarrantyAlertEnabled);
            _tmpIsWarrantyAlertEnabled = _tmp_5 != 0;
            final Date _tmpCreatedAt;
            final Long _tmp_6;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __converters.fromTimestamp(_tmp_6);
            final Date _tmpUpdatedAt;
            final Long _tmp_7;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_7 = null;
            } else {
              _tmp_7 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _tmpUpdatedAt = __converters.fromTimestamp(_tmp_7);
            _item = new Receipt(_tmpId,_tmpVendor,_tmpTotal,_tmpDate,_tmpCategory,_tmpImagePath,_tmpNotes,_tmpWarrantyExpiryDate,_tmpIsWarrantyAlertEnabled,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getReceiptsWithWarrantyAlerts(final Date currentDate,
      final Continuation<? super List<Receipt>> $completion) {
    final String _sql = "SELECT * FROM receipts WHERE isWarrantyAlertEnabled = 1 AND warrantyExpiryDate IS NOT NULL AND warrantyExpiryDate > ? ORDER BY warrantyExpiryDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Long _tmp = __converters.dateToTimestamp(currentDate);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Receipt>>() {
      @Override
      @NonNull
      public List<Receipt> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVendor = CursorUtil.getColumnIndexOrThrow(_cursor, "vendor");
          final int _cursorIndexOfTotal = CursorUtil.getColumnIndexOrThrow(_cursor, "total");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfWarrantyExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "warrantyExpiryDate");
          final int _cursorIndexOfIsWarrantyAlertEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isWarrantyAlertEnabled");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<Receipt> _result = new ArrayList<Receipt>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Receipt _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpVendor;
            if (_cursor.isNull(_cursorIndexOfVendor)) {
              _tmpVendor = null;
            } else {
              _tmpVendor = _cursor.getString(_cursorIndexOfVendor);
            }
            final double _tmpTotal;
            _tmpTotal = _cursor.getDouble(_cursorIndexOfTotal);
            final Date _tmpDate;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = __converters.fromTimestamp(_tmp_1);
            final ReceiptCategory _tmpCategory;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toReceiptCategory(_tmp_2);
            final String _tmpImagePath;
            if (_cursor.isNull(_cursorIndexOfImagePath)) {
              _tmpImagePath = null;
            } else {
              _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Date _tmpWarrantyExpiryDate;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfWarrantyExpiryDate)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfWarrantyExpiryDate);
            }
            _tmpWarrantyExpiryDate = __converters.fromTimestamp(_tmp_3);
            final boolean _tmpIsWarrantyAlertEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWarrantyAlertEnabled);
            _tmpIsWarrantyAlertEnabled = _tmp_4 != 0;
            final Date _tmpCreatedAt;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __converters.fromTimestamp(_tmp_5);
            final Date _tmpUpdatedAt;
            final Long _tmp_6;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _tmpUpdatedAt = __converters.fromTimestamp(_tmp_6);
            _item = new Receipt(_tmpId,_tmpVendor,_tmpTotal,_tmpDate,_tmpCategory,_tmpImagePath,_tmpNotes,_tmpWarrantyExpiryDate,_tmpIsWarrantyAlertEnabled,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getMonthlySummaryByCategory(final Date startDate, final Date endDate,
      final Continuation<? super List<CategorySummary>> $completion) {
    final String _sql = "SELECT category, SUM(total) as totalAmount FROM receipts WHERE date BETWEEN ? AND ? GROUP BY category";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final Long _tmp = __converters.dateToTimestamp(startDate);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    _argIndex = 2;
    final Long _tmp_1 = __converters.dateToTimestamp(endDate);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_1);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CategorySummary>>() {
      @Override
      @NonNull
      public List<CategorySummary> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCategory = 0;
          final int _cursorIndexOfTotalAmount = 1;
          final List<CategorySummary> _result = new ArrayList<CategorySummary>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CategorySummary _item;
            final ReceiptCategory _tmpCategory;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toReceiptCategory(_tmp_2);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            _item = new CategorySummary(_tmpCategory,_tmpTotalAmount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTotalSpentInPeriod(final Date startDate, final Date endDate,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(total) FROM receipts WHERE date BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final Long _tmp = __converters.dateToTimestamp(startDate);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    _argIndex = 2;
    final Long _tmp_1 = __converters.dateToTimestamp(endDate);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_1);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp_2;
            if (_cursor.isNull(0)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getDouble(0);
            }
            _result = _tmp_2;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Receipt>> searchReceipts(final String searchQuery) {
    final String _sql = "SELECT * FROM receipts WHERE vendor LIKE '%' || ? || '%' OR notes LIKE '%' || ? || '%' ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (searchQuery == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, searchQuery);
    }
    _argIndex = 2;
    if (searchQuery == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, searchQuery);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"receipts"}, false, new Callable<List<Receipt>>() {
      @Override
      @Nullable
      public List<Receipt> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVendor = CursorUtil.getColumnIndexOrThrow(_cursor, "vendor");
          final int _cursorIndexOfTotal = CursorUtil.getColumnIndexOrThrow(_cursor, "total");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfWarrantyExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "warrantyExpiryDate");
          final int _cursorIndexOfIsWarrantyAlertEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isWarrantyAlertEnabled");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<Receipt> _result = new ArrayList<Receipt>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Receipt _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpVendor;
            if (_cursor.isNull(_cursorIndexOfVendor)) {
              _tmpVendor = null;
            } else {
              _tmpVendor = _cursor.getString(_cursorIndexOfVendor);
            }
            final double _tmpTotal;
            _tmpTotal = _cursor.getDouble(_cursorIndexOfTotal);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = __converters.fromTimestamp(_tmp);
            final ReceiptCategory _tmpCategory;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toReceiptCategory(_tmp_1);
            final String _tmpImagePath;
            if (_cursor.isNull(_cursorIndexOfImagePath)) {
              _tmpImagePath = null;
            } else {
              _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Date _tmpWarrantyExpiryDate;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfWarrantyExpiryDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfWarrantyExpiryDate);
            }
            _tmpWarrantyExpiryDate = __converters.fromTimestamp(_tmp_2);
            final boolean _tmpIsWarrantyAlertEnabled;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsWarrantyAlertEnabled);
            _tmpIsWarrantyAlertEnabled = _tmp_3 != 0;
            final Date _tmpCreatedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __converters.fromTimestamp(_tmp_4);
            final Date _tmpUpdatedAt;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _tmpUpdatedAt = __converters.fromTimestamp(_tmp_5);
            _item = new Receipt(_tmpId,_tmpVendor,_tmpTotal,_tmpDate,_tmpCategory,_tmpImagePath,_tmpNotes,_tmpWarrantyExpiryDate,_tmpIsWarrantyAlertEnabled,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
