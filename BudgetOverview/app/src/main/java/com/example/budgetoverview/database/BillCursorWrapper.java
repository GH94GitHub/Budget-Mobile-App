package com.example.budgetoverview.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.budgetoverview.Bill;
import com.example.budgetoverview.database.BillDbSchema.BillsTable;

public class BillCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public BillCursorWrapper(Cursor cursor) {
        super(cursor);
    }

public Bill getBill() {

        String billName = getString(getColumnIndex(BillsTable.Cols.BILLDESC));
        double amount = getDouble(getColumnIndex(BillsTable.Cols.AMOUNT));
        int dueDate = getInt(getColumnIndex(BillsTable.Cols.DUEDATE));


        Bill bill = new Bill(billName, amount, dueDate);


        return bill;
}
}
