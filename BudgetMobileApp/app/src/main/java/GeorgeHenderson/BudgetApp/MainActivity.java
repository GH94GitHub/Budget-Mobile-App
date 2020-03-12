package GeorgeHenderson.BudgetApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static Button mAddButton;
    private static Button mDeleteButton;
    private static Button mBudgetOverviewButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddButton = (Button)findViewById(R.id.form_add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddDeleteBill.class);
                startActivity(intent);
            }
        });

        mDeleteButton = (Button)findViewById(R.id.form_delete_button);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, delete_bill.class);
                startActivity(intent);
            }
        });

        mBudgetOverviewButton = (Button)findViewById(R.id.budget_overview_button);
        mBudgetOverviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BudgetOverview.class);
                startActivity(intent);
            }
        });
    }
}
