package com.mad1.playerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mad1.playerapp.data.Player;
import com.mad1.playerapp.database.PlayerDB;

import java.util.ArrayList;

public class GameEmulatorActivity extends AppCompatActivity {
    private TextView player_1_tv;
    private TextView player_2_tv;
    private Button btn_player_1;
    private Button btn_player_2;
    private Player player_1;
    private Player player_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_emulator);

        // get db and StringBuilder objects
        PlayerDB db = new PlayerDB(this);
        StringBuilder sb = new StringBuilder();

        // Get players
        // display all tasks (id + name)
        ArrayList<Player> players = db.getPlayers();
        player_1 = players.get(0);
        player_2 = players.get(1);

        player_1_tv = findViewById(R.id.player_1_tv);
        player_2_tv = findViewById(R.id.player_2_tv);
        btn_player_1 = findViewById(R.id.btn_player_1_wins);
        btn_player_2 = findViewById(R.id.btn_player_2_wins);

        player_1_tv.setText(player_1.getName());
        player_2_tv.setText(player_2.getName());
        btn_player_1.setText(player_1.getName() + " wins!");
        btn_player_2.setText(player_2.getName() + " wins!");
    }

    public void player_1_wins(View view) {
        Intent replyIntent = new Intent();
        player_1.setWins(player_1.getWins() + 1);
        player_2.setLosses(player_2.getLosses() + 1);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    public void player_2_wins(View view) {
        Intent replyIntent = new Intent();
        player_1.setLosses(player_1.getLosses() + 1);
        player_2.setWins(player_2.getWins() + 1);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    public void game_ties(View view) {
        Intent replyIntent = new Intent();
        player_1.setTies(player_1.getTies() + 1);
        player_2.setTies(player_2.getTies() + 1);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}