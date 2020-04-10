package GeorgeHenderson.BudgetApp;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bills {
    private List<Bill> mBills;
    private static Bills sBills;

    public String[][] logInfo = new String[1][4];

    public Bills() {
        mBills = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Bill bill = new Bill();
            mBills.add(bill);
        }

        logInfo[0][0] = "2020-04-03";
        logInfo[0][1] = "2020-04-03";
        logInfo[0][2] = "2020-04-16";
        logInfo[0][3] = "$500";
    }

    public List<Bill> getBills() {
        return mBills;
    }

    public Bill getBill(UUID id) {
        for (Bill bill : mBills) {
            if (bill.getId().equals(id)) {
                return bill;
            }
        }
        return null;
    }

    public String[][] getLogInfo() {
        return logInfo;
    }
}
