package com.example.budgetoverview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class BudgetFragment extends Fragment {

    private Button mAddBillButton;
    private Button mDeleteBillButton;
    private Button mBudgetOverviewButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_budget, container, false);

        mAddBillButton = (Button) v.findViewById(R.id.addBillButton);
        mAddBillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBillFragment addBillFragment = new AddBillFragment();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, addBillFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        mDeleteBillButton = (Button) v.findViewById(R.id.deleteBillButton);
        mDeleteBillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteBillFragment deleteBillFragment = new DeleteBillFragment();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, deleteBillFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        mBudgetOverviewButton = (Button) v.findViewById(R.id.budgetOverviewButton);
        mBudgetOverviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BudgetOverviewFragment budgetOverviewFragment = new BudgetOverviewFragment();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, budgetOverviewFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setTitle(R.string.app_name);
    }
}
