package com.mad1.temperatureconverterdj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private static final DecimalFormat df = new DecimalFormat("0.00");
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

        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    convert();
                }
                return false;
            }
        });
    }

    public void convert()
    {
        if(!input.getText().toString().matches("")){
            float inputValue = Float.parseFloat(input.getText().toString());
            if(toCelsius.isChecked()){
                output.setText(df.format(ConverterUtils.convertFahrenheitToCelsius(inputValue)));
            }else if(toFahr.isChecked()){
                output.setText(df.format(ConverterUtils.convertCelsiusToFahrenheit(inputValue)));
            }

            BasicUtils.hideSoftKeyboard(this);
        }

        else{
            Context context = getApplicationContext();
            CharSequence error = "Please put in a valid value!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, error, duration);
            toast.show();
        }

    }

    public void convert(View view) {
        convert();
    }
}