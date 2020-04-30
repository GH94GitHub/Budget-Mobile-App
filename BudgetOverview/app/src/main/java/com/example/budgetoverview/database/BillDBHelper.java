package com.example.budgetoverview.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.budgetoverview.database.BillDbSchema.BillsTable;
import com.example.budgetoverview.database.BillDbSchema.Log_BillsCurrent;
import com.example.budgetoverview.database.BillDbSchema.Log_Info;

public class BillDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "billsDB";
    private static final int VERSION = 1;

    public BillDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + BillsTable.NAME + "(" +
                BillsTable.Cols.BILLDESC + ", " +
                BillsTable.Cols.AMOUNT + ", " +
                BillsTable.Cols.DUEDATE + ")");

        db.execSQL("create table " + Log_BillsCurrent.NAME + "(" +
                Log_BillsCurrent.Cols.BILL + ", " +
                Log_BillsCurrent.Cols.AMOUNT + ", " +
                Log_BillsCurrent.Cols.DUEDATE + ")");

        db.execSQL("create table " + Log_Info.NAME + "(" +
                Log_Info.Cols.DATECALC + ", " +
                Log_Info.Cols.CALSTART + ", " +
                Log_Info.Cols.CALEND + ", " +
                Log_Info.Cols.BUDGET + ")");

        //Data that would originally be inserted into database by Desktop application
        //(This mobile app is meant to delete bills from Log_BillsCurrent when they have been paid
        // and update the budget from Log_Info with purchases through the week. It can also add and
        // delete bills from the general bill list)
        db.execSQL("insert into " + Log_Info.NAME + "(" +
                        Log_Info.Cols.DATECALC + ", " +
                        Log_Info.Cols.CALSTART + ", " +
                        Log_Info.Cols.CALEND + ", " +
                        Log_Info.Cols.BUDGET + ")" +
                " values ('04-25-2020', '06-26', '07-09', 135.80)"
                );

        newBill(db, "AlloInternet", 100.00, 1);
        newBill(db, "StudentLoanK", 62.00, 4);
        newBill(db, "Rent", 1175.00, 5);
        newBill(db, "CreditCardT", 150.00, 28);
        newBill(db, "Netflix", 14.00, 30);

        newCurrentBill(db, "AlloInternet", 100.00, 1);
        newCurrentBill(db, "StudentLoanK", 62.00, 4);
        newCurrentBill(db, "Rent", 1175.00, 5);
        newCurrentBill(db, "CreditCardT", 150.00, 28);
        newCurrentBill(db, "Netflix", 14.00, 30);



    }

    private void newCurrentBill(SQLiteDatabase db, String bill, double amount, int dueDate) {
        db.execSQL("insert into " + Log_BillsCurrent.NAME + "(" +
                Log_BillsCurrent.Cols.BILL + ", " +
                Log_BillsCurrent.Cols.AMOUNT + ", " +
                Log_BillsCurrent.Cols.DUEDATE + ")" +
                " values ('" + bill + "', '" + amount + "', '" + dueDate + "')");
    }

    private void newBill(SQLiteDatabase db, String bill, double amount, int dueDate) {
        db.execSQL("insert into " + BillsTable.NAME + "(" +
                BillsTable.Cols.BILLDESC + ", " +
                BillsTable.Cols.AMOUNT + ", " +
                BillsTable.Cols.DUEDATE + ")" +
                " values ('" + bill + "', '" + amount + "', '" + dueDate + "')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
