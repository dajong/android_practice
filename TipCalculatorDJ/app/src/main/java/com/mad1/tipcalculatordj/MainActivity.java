package com.mad1.tipcalculatordj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private EditText billAmountEditText;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private SeekBar seekBar;

    private String billAmountString = "";
    private float tipPercent;

    public MainActivity() {
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NumberFormat percent = NumberFormat.getPercentInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmountEditText = findViewById(R.id.bill_amount);
        tipTextView = findViewById(R.id.tip);
        percentTextView = findViewById(R.id.percent_tv);
        totalTextView = findViewById(R.id.total);
        seekBar = findViewById(R.id.seekBar);
        tipPercent = Float.parseFloat(percentTextView.getText().toString());
        percentTextView.setText(percent.format(tipPercent / 100));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tipPercent = (float) i;
                percentTextView.setText(percent.format(tipPercent / 100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        billAmountEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    calculateAndDisplay();
                }
                return false;
            }
        });
    }

    public void calculateAndDisplay() {


        // get the bill amount
        billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if (billAmountString.equals("")) {
            billAmount = 0;
        }
        else {
            billAmount = Float.parseFloat(billAmountString);
        }

        // calculate tip and total
        float tipAmount = billAmount * tipPercent / 100;
        float totalAmount = billAmount + tipAmount;

        // display the other results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent / 100));
    }

    public void percentUp(View view) {
        NumberFormat percent = NumberFormat.getPercentInstance();
        seekBar.setProgress(seekBar.getProgress() + 1);
        percentTextView.setText(percent.format(tipPercent / 100));
    }

    public void percentDown(View view) {
        NumberFormat percent = NumberFormat.getPercentInstance();
        seekBar.setProgress(seekBar.getProgress() - 1);
        percentTextView.setText(percent.format(tipPercent / 100));
    }

    public void calculateAndDisplay(View view) {
        calculateAndDisplay();
    }
}