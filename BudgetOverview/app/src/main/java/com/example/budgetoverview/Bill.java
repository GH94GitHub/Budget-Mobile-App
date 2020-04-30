package com.example.budgetoverview;

import java.util.UUID;

public class Bill {

    //Bill, amount, duedate

    private UUID mId;
    private String mBill;
    private double mAmount;
    private int mDueDate;

    public Bill(String bill, double amount, int dueDate) {
        mId = UUID.randomUUID();
        mBill = bill;
        mAmount = amount;
        mDueDate = dueDate;
    }

    public UUID getmId() {
        return mId;
    }

    public String getmBill() {
        return mBill;
    }

    public void setmBill(String mBill) {
        this.mBill = mBill;
    }

    public double getmAmount() {
        return mAmount;
    }

    public void setmAmount(double mAmount) {
        this.mAmount = mAmount;
    }

    public int getmDueDate() {
        return mDueDate;
    }

    public void setmDueDate(int mDueDate) {
        this.mDueDate = mDueDate;
    }
}
