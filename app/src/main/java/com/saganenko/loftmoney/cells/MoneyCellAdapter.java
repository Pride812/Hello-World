package com.saganenko.loftmoney.cells;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.saganenko.loftmoney.R;
import com.saganenko.loftmoney.cells.MoneyItem;

import java.util.ArrayList;
import java.util.List;


public class MoneyCellAdapter extends RecyclerView.Adapter<MoneyCellAdapter.MoneyViewHolder> {

    private final List<MoneyItem> itemsList = new ArrayList<>();

    private final int colorId;

    public MoneyCellAdapter(int colorId) {
        this.colorId = colorId;
    }


    @NonNull
    @Override
    public MoneyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.item, null);

        return new MoneyViewHolder(itemView, colorId);
    }


    @Override
    public void onBindViewHolder(@NonNull final MoneyViewHolder holder, final int position) {

        holder.bindItem(itemsList.get(position));
    }


    public void addItem(MoneyItem item) {
        itemsList.add(item);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    static class MoneyViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;
        private final TextView mAmountView;

        public MoneyViewHolder(@NonNull final View itemView, int colorId) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.PS5);
            mAmountView = itemView.findViewById(R.id.valuePS);
            mAmountView.setTextColor(ContextCompat.getColor(mAmountView.getContext(), colorId));
        }

        public void bindItem(@NonNull final MoneyItem item) {
            mTextView.setText(item.getName());
            mAmountView.setText(String.valueOf(item.getAmount()) + " ₽");
        }
    }
}