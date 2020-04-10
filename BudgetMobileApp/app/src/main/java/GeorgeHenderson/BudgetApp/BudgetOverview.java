package GeorgeHenderson.BudgetApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BudgetOverview extends AppCompatActivity {

    private TextView mDateCalculatedTextView;
    private TextView mForDatesTextView;
    private TextView mBudgetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_overview);

        Bills bills = new Bills();
        String[][] logInfo = bills.logInfo;

        mDateCalculatedTextView = (TextView) findViewById(R.id.date_calculated);
        mDateCalculatedTextView.setText("Date Calculated: " + logInfo[0][0]);

        mForDatesTextView = (TextView) findViewById(R.id.for_dates);
        mForDatesTextView.setText("For Dates: " + logInfo[0][1] + " Through " + logInfo[0][2]);

        mBudgetTextView = (TextView) findViewById(R.id.budget);
        mBudgetTextView.setText(logInfo[0][3]);
    }


}
