package com.example.budgetoverview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.budgetoverview.database.BillCursorWrapper;
import com.example.budgetoverview.database.BillDBHelper;
import com.example.budgetoverview.database.BillDbSchema;
import com.example.budgetoverview.database.BillDbSchema.BillsTable;

import java.util.ArrayList;
import java.util.List;

public class Bills {

    private static Bills sBills;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static Bills get(Context context) {
        if (sBills == null) {
            sBills = new Bills(context);
        }
        return sBills;
    }

    private Bills(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new BillDBHelper(mContext).getWritableDatabase();
    }

    public List<Bill> getBills() {
        List<Bill> bills = new ArrayList<>();

        BillCursorWrapper cursor = queryBills(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                bills.add(cursor.getBill());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return bills;
    }

    public void addBill(Bill bill) {
        ContentValues values = getContentValues(bill);
        mDatabase.insert(BillsTable.NAME, null, values);
    }

    public void deleteBill(Bill bill) {
        String uuidString = bill.getmId().toString();
        mDatabase.delete(BillsTable.NAME, BillsTable.Cols.BILLDESC + "=?", new String[] {bill.getmBill()});
    }

    public BillCursorWrapper queryBills(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                BillsTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        return new BillCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Bill bill) {
        ContentValues values = new ContentValues();

        values.put(BillsTable.Cols.UUID, bill.getmId().toString());
        values.put(BillsTable.Cols.BILLDESC, bill.getmBill());
        values.put(BillsTable.Cols.AMOUNT, bill.getmAmount());
        values.put(BillsTable.Cols.DUEDATE, bill.getmDueDate());

        return values;
    }
}
