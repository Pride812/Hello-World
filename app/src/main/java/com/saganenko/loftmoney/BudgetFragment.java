package com.saganenko.loftmoney;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saganenko.loftmoney.cells.MoneyCellAdapter;
import com.saganenko.loftmoney.cells.MoneyItem;
import com.saganenko.loftmoney.remote.MoneyApi;
import com.saganenko.loftmoney.remote.models.MoneyItemResponse;


import java.util.ArrayList;
import java.util.List;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import io.reactivex.schedulers.Schedulers;

public class BudgetFragment extends Fragment {


    private static final String COLOR_ID = "colorId";
    public static final String TYPE = "fragmentType";
    private MoneyApi moneyApi;
    private String type;

    private final   CompositeDisposable compositeDisposable = new CompositeDisposable();


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
    public String getType() {
        return type;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_budget, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moneyApi = ((LoftApp) getActivity().getApplication()).moneyApi;


        RecyclerView recyclerView = view.findViewById(R.id.budget_item_list);

        if (getArguments() != null) {
            mAdapter = new MoneyCellAdapter(getArguments().getInt(COLOR_ID));
            type = getArguments().getString(TYPE);

        } else {
            mAdapter = new MoneyCellAdapter(R.color.purple_500);
        }

        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        loadItems();
    }

    private void loadItems() {
        Disposable disposable = moneyApi.getItems(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moneyListResponse -> {
                    List<MoneyItem> moneyItems = new ArrayList<>();

                    for (MoneyItemResponse moneyRemoteItem : moneyListResponse.getItemList()){
                        moneyItems.add(MoneyItem.getInstance(moneyRemoteItem));
                    }
                    mAdapter.setData(moneyItems);

                }, error-> Toast.makeText(getContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT)
                        .show());
        compositeDisposable.add(disposable);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}



