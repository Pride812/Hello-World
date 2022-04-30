package com.saganenko.loftmoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cells.MoneyCellAdapter;
import cells.MoneyItem;

public class MainActivity extends AppCompatActivity {
   private RecyclerView itemsview;
   private MoneyCellAdapter moneyCellAdapter = new MoneyCellAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureRecyclerView();
        generatemoney();
    }
    private void generatemoney(){
        List<MoneyItem> moneyItems = new ArrayList<>();
        moneyItems.add(new MoneyItem("PS 5", "50000P"));
        moneyItems.add(new MoneyItem("Salary", "200000P"));

        moneyCellAdapter.setData(moneyItems);

    }
    private void configureRecyclerView() {
        itemsview = findViewById(R.id.itemsview);
        itemsview.setAdapter(moneyCellAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        itemsview.setLayoutManager(layoutManager);
    }

    }
