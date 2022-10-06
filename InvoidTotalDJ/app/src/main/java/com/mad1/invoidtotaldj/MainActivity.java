package com.mad1.invoidtotaldj;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private EditText subtotal;
    private TextView percent;
    private TextView amount;
    private TextView total;
    private double curDiscount;
    private SharedPreferences savedValues;

    private static NumberFormat percentFormatter = NumberFormat.getPercentInstance();
    private static NumberFormat currency = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        subtotal = findViewById(R.id.subtotal_editText);
        percent = findViewById(R.id.percent);
        amount = findViewById(R.id.amount);
        total = findViewById(R.id.total);



        subtotal.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    calculateAndDisplay();
                }
                return false;
            }
        });

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("resume", "RESUMMEEEEINGGGGGGG!!!!!!!!!!!!!!!");
        String subtotalString = savedValues.getString("subtotal", "");
        subtotal.setText(subtotalString);
        calculateAndDisplay();
    }

    @Override
    protected void onPause() {
       SharedPreferences.Editor editor = savedValues.edit();
       editor.putString("subtotal", subtotal.getText().toString());
       editor.commit();
       Log.d("pause", "PAAAUUUSSSSINGGGGGGGGGG!!!!!!!!!!!!!");
       super.onPause();
    }

    public void calculateAndDisplay(){
        double curSubtotal;
        if (subtotal.getText().toString().equals("")) {
            curSubtotal = 0;
        }
        else {
            curSubtotal = Integer.parseInt(subtotal.getText().toString());
        }
        double discountAmount = curSubtotal;
        if(curSubtotal < 100){
            curDiscount = 1;
            percent.setText(percentFormatter.format(0));
        }
        else if(curSubtotal >= 100 && curSubtotal < 200){
            curDiscount = 0.9;
            percent.setText(percentFormatter.format(0.1));
        }
        else if(curSubtotal >= 200){
            curDiscount = 0.8;
            percent.setText(percentFormatter.format(0.2));
        }

        curSubtotal *= curDiscount;
        curSubtotal = Math.round(curSubtotal);
        discountAmount -= curSubtotal;
        amount.setText(currency.format(discountAmount));
        total.setText(currency.format(curSubtotal));

    }
}

