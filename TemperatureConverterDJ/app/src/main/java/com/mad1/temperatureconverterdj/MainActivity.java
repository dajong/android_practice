package com.mad1.temperatureconverterdj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RadioButton toCelsius;
    private RadioButton toFahr;
    private EditText input;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toCelsius = findViewById(R.id.toCelsiusRadioButton);
        toFahr = findViewById(R.id.toFahrRadioButton);
        input = findViewById(R.id.input);
        output = findViewById(R.id.answer);
    }

    public void convert(View view) {
        float inputValue = Float.parseFloat(input.getText().toString());
        if(toCelsius.isChecked()){
            output.setText(Float.toString(ConverterUtils.convertFahrenheitToCelsius(inputValue)));
        }else if(toFahr.isChecked()){
            output.setText(Float.toString(ConverterUtils.convertCelsiusToFahrenheit(inputValue)));
        }

        BasicUtils.hideSoftKeyboard(this);
    }
}