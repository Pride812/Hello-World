package com.saganenko.loftmoney;

import static android.app.Activity.RESULT_OK;
import static com.saganenko.loftmoney.AddItemActivity.KEY_AMOUNT;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saganenko.loftmoney.cells.MoneyCellAdapter;
import com.saganenko.loftmoney.cells.MoneyItem;

public class BudgetFragment extends Fragment {

    private static final int ADD_ITEM_REQUEST_CODE = 100;
    private static final String COLOR_ID = "colorId";
    private static final String TYPE = "fragmentType";


    private MoneyCellAdapter mAdapter;

    @NonNull
    public static BudgetFragment newInstance(final int colorId, final String type) {
        BudgetFragment budgetFragment = new BudgetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(COLOR_ID, colorId);
        bundle.putString(TYPE, type);
        budgetFragment.setArguments(bundle);
        return budgetFragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_budget, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        FloatingActionButton addButton = view.findViewById(R.id.add_button);


        addButton.setOnClickListener(v->startActivityForResult(
                new Intent(getActivity(), AddItemActivity.class), ADD_ITEM_REQUEST_CODE));

        RecyclerView recyclerView = view.findViewById(R.id.budget_item_list);

        if (getArguments() != null) {
            mAdapter = new MoneyCellAdapter(getArguments().getInt(COLOR_ID));
        } else {
            mAdapter = new MoneyCellAdapter(R.color.purple_500);
        }

        recyclerView.setAdapter(mAdapter);
        mAdapter.addItem(new

                MoneyItem("Car", 3000000));
        mAdapter.addItem(new

                MoneyItem("pie", 50));
    }

        public void onActivityResult ( final int requestCode, final int resultCode,
        @Nullable final Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
                if (data != null)
                    mAdapter.addItem(
                            new MoneyItem(
                                    data.getStringExtra(AddItemActivity.KEY_NAME),
                                    parseInt(data.getStringExtra(KEY_AMOUNT))
                            )
                    );


            }
        }
    }

