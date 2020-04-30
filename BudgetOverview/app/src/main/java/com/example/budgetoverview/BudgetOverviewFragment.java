package com.example.budgetoverview;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.budgetoverview.database.BillCursorWrapper;
import com.example.budgetoverview.database.BillDBHelper;
import com.example.budgetoverview.database.BillDbSchema;
import com.example.budgetoverview.database.BillDbSchema.Log_Info;

import org.w3c.dom.Text;

public class BudgetOverviewFragment extends Fragment {

    private TextView mDateCalculated;
    private TextView mDateRange;

    private TextView mBudget;

    private EditText mAddEdit;
    private EditText mSubtractEdit;

    private Button mAddButton;
    private Button mSubtractButton;

    private SQLiteDatabase mDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = new BillDBHelper(getActivity()).getWritableDatabase();

        getActivity().setTitle(R.string.title_budget_overview);
    }

    private String getDateCalc() {
        String dateCalc;

        Cursor cursor = queryDB(new String[] {Log_Info.Cols.DATECALC}, null, null);

        cursor.moveToFirst();
        dateCalc = cursor.getString(cursor.getColumnIndex(Log_Info.Cols.DATECALC));
        cursor.close();

        return "Date Calculated: " + dateCalc;
    }

    private String getDateRange() {
        String dateStart;
        String dateEnd;

        Cursor cursor = queryDB(new String[] {Log_Info.Cols.CALSTART, Log_Info.Cols.CALEND}, null, null);

        cursor.moveToFirst();
        dateStart = cursor.getString(cursor.getColumnIndex(Log_Info.Cols.CALSTART));
        dateEnd = cursor.getString(cursor.getColumnIndex(Log_Info.Cols.CALEND));

        return "For Dates: " + dateStart + " through " + dateEnd;
    }

    private String getBudget() {
        String budget;

        Cursor cursor = queryDB(new String[] {Log_Info.Cols.BUDGET}, null, null);

        cursor.moveToFirst();
        budget = cursor.getString(cursor.getColumnIndex(Log_Info.Cols.BUDGET));

        return budget;
    }

    private BillCursorWrapper queryDB(String[] col, String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                Log_Info.NAME,
                col,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new BillCursorWrapper(cursor);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_budget_overview, container, false);

        mDateCalculated = (TextView) v.findViewById(R.id.dateCalculatedTextView);
        mDateCalculated.setText(getDateCalc());

        mDateRange = (TextView) v.findViewById(R.id.dateRangeTextView);
        mDateRange.setText(getDateRange());

        mBudget = (TextView) v.findViewById(R.id.overviewBudgetTextView);
        mBudget.setText("$" + String.format("%.2f", Double.parseDouble(getBudget())));

        mAddEdit = (EditText) v.findViewById(R.id.overviewAddEdit);

        mSubtractEdit = (EditText) v.findViewById(R.id.overviewSubtractEdit);

        mAddButton = (Button) v.findViewById(R.id.overviewAddButton);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double budget;

                budget = Double.parseDouble(getBudget());
                budget += Double.parseDouble(mAddEdit.getText().toString());
                mBudget.setText("$" + String.format("%.2f", budget));
                mAddEdit.getText().clear();

                ContentValues values = new ContentValues();
                values.put(Log_Info.Cols.BUDGET, budget);

                mDatabase.update(Log_Info.NAME, values, null, null);
            }
        });

        mSubtractButton = (Button) v.findViewById(R.id.overviewSubtractButton);
        mSubtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double budget;

                budget = Double.parseDouble(getBudget());
                budget -= Double.parseDouble(mSubtractEdit.getText().toString());
                mBudget.setText("$" + String.format("%.2f", budget));
                mSubtractEdit.getText().clear();

                ContentValues values = new ContentValues();
                values.put(Log_Info.Cols.BUDGET, budget);

                mDatabase.update(Log_Info.NAME, values, null, null);
            }
        });

        return v;
    }
}
