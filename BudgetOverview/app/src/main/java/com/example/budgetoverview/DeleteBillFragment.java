package com.example.budgetoverview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetoverview.database.BillCursorWrapper;
import com.example.budgetoverview.database.BillDbSchema;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DeleteBillFragment extends Fragment {

    private List<String> deleteList = new ArrayList<>();
    private List<Bill> billDeleteList = new ArrayList<>();

    private RecyclerView mBillRecyclerView;
    private BillAdapter mAdapter;
    private Bills mBills;

    private Button mDeleteButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBills = Bills.get(getActivity());

        getActivity().setTitle(R.string.title_delete_bill);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_delete_bill, container, false);

        mBillRecyclerView = (RecyclerView) v.findViewById(R.id.billRecyclerView);
        mBillRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDeleteButton = (Button) v.findViewById(R.id.deleteButton);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Bill bill : billDeleteList) {

                    mBills.deleteBill(bill);
                }
                updateUI();
            }
        });

        updateUI();

        return v;
    }

    private class BillHolder extends RecyclerView.ViewHolder {

        private CheckBox mBillCheckBox;
        private Bill mBill;

        public BillHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_bill_fragment, parent, false));

            mBillCheckBox = (CheckBox) itemView.findViewById(R.id.billCheckBox);
            mBillCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBillCheckBox.isChecked()) {
                        billDeleteList.add(mBill);
                    }
                    else{
                        billDeleteList.remove(mBill);
                    }
                }
            });
        }

        public void bind(Bill bill) {
            mBill = bill;
            mBillCheckBox.setText(mBill.getmBill());
        }
    }

//Adapter
    private class BillAdapter extends RecyclerView.Adapter<BillHolder> {

        private List<Bill> mBills;

        public BillAdapter(List<Bill> bills) {
            mBills = bills;
        }

        @NonNull
        @Override
        public BillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new BillHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(BillHolder holder, int position) {
            Bill bill = mBills.get(position);
            holder.bind(bill);
        }

        @Override
        public int getItemCount() {
            return mBills.size();
        }

        public void setBills(List<Bill> bills) {
            mBills = bills;
        }
    }


    private void updateUI() {
        Bills bills = Bills.get(getActivity());
        List<Bill> billList = bills.getBills();


        if (mAdapter == null) {
            mAdapter = new BillAdapter(billList);
            mBillRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setBills(billList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
