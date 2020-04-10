package GeorgeHenderson.BudgetApp;


import java.util.UUID;

import static java.util.UUID.randomUUID;

public class Bill {
    private String mBillDesc;
    private int mAmount;
    private int mDueDate;
    private UUID mId;

    public Bill() {
        mId = randomUUID();
        mBillDesc = "null";
        mAmount = 0;
        mDueDate = 10;
    }

    public Bill(String billDesc, int amount, int dueDate) {
        mId = randomUUID();
        mBillDesc = billDesc;
        mAmount = amount;
        mDueDate = dueDate;
    }

    public String getBillDesc() {
        return mBillDesc;
    }

    public int getAmount() {
        return mAmount;
    }

    public int getDueDate() {
        return mDueDate;
    }

    public void setBillDesc(String billDesc) {
        mBillDesc = billDesc;
    }

    public void setAmount(int amount) {
        mAmount = amount;
    }

    public void setDueDate(int dueDate) {
        mDueDate = dueDate;
    }

    public UUID getId() {
        return mId;
    }
}
