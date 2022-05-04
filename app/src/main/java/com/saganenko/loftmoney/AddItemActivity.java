package com.saganenko.loftmoney;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class AddItemActivity extends AppCompatActivity {

    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_NAME = "name";

    private EditText nameEditText;
    private EditText amountEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Button addButton = findViewById(R.id.addButton);

        nameEditText = findViewById(R.id.price);
        amountEditText = findViewById(R.id.consumption);


        setTextWatcher(nameEditText, addButton);
        setTextWatcher(amountEditText, addButton);


        addButton.setOnClickListener(v -> {

            String name = nameEditText.getText().toString();
            String price = amountEditText.getText().toString();

            Intent intent = new Intent();
            intent.putExtra(KEY_NAME, name);
            intent.putExtra(KEY_AMOUNT, price);

            AddItemActivity.this.setResult(RESULT_OK, intent);


            AddItemActivity.this.finish();
        });
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