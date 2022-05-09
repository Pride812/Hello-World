package com.saganenko.loftmoney;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;

import com.saganenko.loftmoney.remote.MoneyApi;
import com.saganenko.loftmoney.remote.models.MoneyListResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class AddItemActivity extends AppCompatActivity {

    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_NAME = "name";

    private EditText nameEditText;
    private EditText amountEditText;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MoneyApi moneyApi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Button addButton = findViewById(R.id.addButton);

        nameEditText = findViewById(R.id.price);
        amountEditText = findViewById(R.id.consumption);


        setTextWatcher(nameEditText, addButton);
        setTextWatcher(amountEditText, addButton);

        moneyApi = ((LoftApp) getApplication()).moneyApi;
        addButton.setOnClickListener(v -> {

            String name = nameEditText.getText().toString();
            int price = Integer.parseInt(amountEditText.getText().toString());
            Bundle arguments = getIntent().getExtras();
            String type = arguments.getString(BudgetFragment.TYPE);
            Disposable disposable = moneyApi.addItem(price, name, type)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            moneyListResponse -> {
                                finish();
                            }, error-> {
                                Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
                            }

                    );



            compositeDisposable.add(disposable);

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    private void setTextWatcher(EditText editText, Button addButton) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!nameEditText.getText().toString().isEmpty() && !amountEditText.getText().toString().isEmpty()) {
                    addButton.setEnabled(true);
                } else {
                    addButton.setEnabled(false);
                }
            }
        });
    }


}