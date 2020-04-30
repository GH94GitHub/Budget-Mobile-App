package com.example.budgetoverview.database;

import java.util.UUID;

public class BillDbSchema {

public static final class BillsTable {
    public static final String NAME = "billsT";

    public static final class Cols {
        public static final String UUID = "uuid";
        public static final String BILLDESC = "billDesc";
        public static final String AMOUNT = "amount";
        public static final String DUEDATE = "dueDate";
    }

}

public static final class Log_BillsCurrent {
    public static final String NAME = "log_billsCurrent";

    public static final class Cols {
        public static final String BILL = "bill";
        public static final String AMOUNT = "amount";
        public static final String DUEDATE = "dueDate";
    }
}

public static final class Log_Info {
    public static final String NAME = "log_info";

    public static final class Cols {
        public static final String DATECALC = "dateCalc";
        public static final String CALSTART = "calStart";
        public static final String CALEND = "calEnd";
        public static final String BUDGET = "budget";
    }
}

}
