package com.example.budgetoverview;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.budgetoverview.database.BillDBHelper;
import com.example.budgetoverview.database.BillDbSchema;

import java.util.List;

public class AddBillFragment extends Fragment {

    private EditText mNameEditText;
    private EditText mAmountEditText;
    private EditText mDayOfMonthEditText;
    private Button mAddButton;

    private Bill mBill;
    private Bills mBills;

    SQLiteDatabase mDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBills = Bills.get(getActivity());
        mDatabase = new BillDBHelper(getActivity()).getWritableDatabase();

        getActivity().setTitle(R.string.title_add_bill);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_bill, container, false);

        mNameEditText = (EditText) v.findViewById(R.id.addNameEdit);

        mAmountEditText = (EditText) v.findViewById(R.id.addAmountEdit);

        mDayOfMonthEditText = (EditText) v.findViewById(R.id.addDueDateEdit);

        mAddButton = (Button) v.findViewById(R.id.add_addButton);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String billName = mNameEditText.getText().toString();
                double billAmount = Double.parseDouble(mAmountEditText.getText().toString());
                int billDueDate = Integer.parseInt(mDayOfMonthEditText.getText().toString());

                mBill = new Bill(billName, billAmount, billDueDate);
                newBill(mDatabase, billName, billAmount, billDueDate);

                mNameEditText.getText().clear();
                mAmountEditText.getText().clear();
                mDayOfMonthEditText.getText().clear();
            }
        });

        return v;
    }

    private void newBill(SQLiteDatabase db, String bill, double amount, int dueDate) {
        db.execSQL("insert into " + BillDbSchema.BillsTable.NAME + "(" +
                BillDbSchema.BillsTable.Cols.BILLDESC + ", " +
                BillDbSchema.BillsTable.Cols.AMOUNT + ", " +
                BillDbSchema.BillsTable.Cols.DUEDATE + ")" +
                " values ('" + bill + "', '" + amount + "', '" + dueDate + "')");
    }
}
