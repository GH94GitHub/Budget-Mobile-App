package com.example.budgetoverview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class BudgetActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new BudgetFragment();
    }
}
