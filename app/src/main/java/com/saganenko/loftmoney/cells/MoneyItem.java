package com.saganenko.loftmoney.cells;

import com.saganenko.loftmoney.remote.models.MoneyItemResponse;

public class MoneyItem {
    private String name;
    private int amount;

    public MoneyItem(final String name, final int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public static MoneyItem getInstance(MoneyItemResponse moneyItemResponse) {
        return new MoneyItem(moneyItemResponse.getName(), moneyItemResponse.getPrice()
        );

    }
}

