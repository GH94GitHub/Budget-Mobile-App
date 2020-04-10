package GeorgeHenderson.BudgetApp;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BillsListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private BillAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_delete_bill, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.bill_recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        Bills bills = new Bills();
        List<Bill> billList = bills.getBills();

        mAdapter = new BillAdapter(billList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class BillHolder extends RecyclerView.ViewHolder {
        private Bill mBill;
        private TextView mTitleTextView;

        public BillHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_bill, parent, false));

            mTitleTextView = (TextView) itemView.findViewById(R.id.bill_title);
        }
        public void bind (Bill bill) {
            mBill = bill;
            mTitleTextView.setText(mBill.getBillDesc());
        }
    }

    private class BillAdapter extends RecyclerView.Adapter<BillHolder> {
        private List<Bill> mBills;

        public BillAdapter(List<Bill> bills) {
            mBills = bills;
        }

        @Override
        public BillHolder onCreateViewHolder (ViewGroup parent, int viewType) {
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

    }

}
