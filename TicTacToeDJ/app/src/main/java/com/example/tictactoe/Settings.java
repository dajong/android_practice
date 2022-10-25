package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
    public static final String NEW_NAME_X = "com.example.android.twoactivities.extra.new_name_x";
    public static final String NEW_NAME_O = "com.example.android.twoactivities.extra.new_name_o";
    private EditText player_x;
    private EditText player_o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        player_x = findViewById(R.id.player_name_x);
        player_o = findViewById(R.id.player_name_o);
    }


    public void saveNewNames(View view) {
        Intent replyIntent = new Intent();

        // String new_name_x  = (player_x.getText().toString() != null) ? player_x.getText().toString() : "X";
        //String new_name_o  = (player_o.getText().toString() != null) ? player_o.getText().toString() : "O";

        String new_name_x = "";
        String new_name_o = "";

        if (player_x.getText().length() == 0) {
            player_x.setError("Field cannot be left blank! ");
        } else if (player_o.getText().length() == 0) {
            player_o.setError("Field cannot be left blank! ");
        } else if (player_o.getText().toString().equals(player_x.getText().toString())) {
            player_o.setError("Names cannot be the same! ");
        } else {
            new_name_x = player_x.getText().toString();
            new_name_o = player_o.getText().toString();
            replyIntent.putExtra(NEW_NAME_X, new_name_x);
            replyIntent.putExtra(NEW_NAME_O, new_name_o);
            setResult(RESULT_OK, replyIntent);
            finish();
        }


    }
}