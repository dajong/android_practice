package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    private TextView status;
    private Button curButton;
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private SharedPreferences savedValues;

    // Player representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    // put all win positions in a 2D array
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        status = findViewById(R.id.status_text);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_new_game:
                gameReset();
                return true;
            case R.id.menu_settings:
                Intent intent = new Intent(MainActivity.this,
                        Settings.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The second argument is the default string we return

        //Buttons
        btn_0.setText(savedValues.getString("btn_0", ""));
        btn_1.setText(savedValues.getString("btn_1", ""));
        btn_2.setText(savedValues.getString("btn_2", ""));
        btn_3.setText(savedValues.getString("btn_3", ""));
        btn_4.setText(savedValues.getString("btn_4", ""));
        btn_5.setText(savedValues.getString("btn_5", ""));
        btn_6.setText(savedValues.getString("btn_6", ""));
        btn_7.setText(savedValues.getString("btn_7", ""));
        btn_8.setText(savedValues.getString("btn_8", ""));

        // Game Status
        status.setText(savedValues.getString("status", ""));
        gameActive = savedValues.getBoolean("gameActive", true);
        String gameStatus = savedValues.getString("gameState", "");
        for(int i = 0; i < gameStatus.length(); i++){
            gameState[i] = gameStatus.charAt(i) - '0';
        }
        counter = savedValues.getInt("count", 0);

        // Player
        activePlayer = savedValues.getInt("activePlayer", 0);
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("status", status.getText().toString());
        editor.putString("btn_0", btn_0.getText().toString());
        editor.putString("btn_1", btn_1.getText().toString());
        editor.putString("btn_2", btn_2.getText().toString());
        editor.putString("btn_3", btn_3.getText().toString());
        editor.putString("btn_4", btn_4.getText().toString());
        editor.putString("btn_5", btn_5.getText().toString());
        editor.putString("btn_6", btn_6.getText().toString());
        editor.putString("btn_7", btn_7.getText().toString());
        editor.putString("btn_8", btn_8.getText().toString());
        editor.putInt("activePlayer", activePlayer);
        editor.putBoolean("gameActive", gameActive);

        // Putting game state into cache
        StringBuilder sb = new StringBuilder();
        for(int i : gameState){
            sb.append(i);
        }
        editor.putString("gameState", sb.toString());
        editor.putInt("count", counter);

        editor.commit();
        super.onPause();
    }

    // this function will be called every time a
    // players tap in an empty box of the grid
    public void playerTap(View view) {

        curButton = (Button) view;
        int tappedImage = Integer.parseInt(curButton.getTag().toString());
        // game reset function will be called
        // if someone wins or the boxes are full
        if (!gameActive) {
            status.setText("Start a new game!");
        }
        else{
            // if the tapped image is empty
            if (gameState[tappedImage] == 2) {
                // increase the counter
                // after every tap
                counter++;

                // check if its the last box
                if (counter == 9) {
                    // reset the game
                    gameActive = false;
                }

                // mark this position
                gameState[tappedImage] = activePlayer;

                // change the active player
                // from 0 to 1 or 1 to 0
                if (activePlayer == 0) {
                    // set the image of x
                    curButton.setText("X");
                    activePlayer = 1;
                    // change the status
                    status.setText("O's Turn - Tap to play");
                } else {
                    // set the image of o
                    curButton.setText("O");
                    activePlayer = 0;

                    // change the status
                    status.setText("X's Turn - Tap to play");
                }
            }
            int flag = 0;
            // Check if any player has won
            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2) {
                    flag = 1;

                    // Somebody has won! - Find out who!
                    String winnerStr;

                    // game reset function be called
                    gameActive = false;
                    if (gameState[winPosition[0]] == 0) {
                        winnerStr = "X has won";
                    } else {
                        winnerStr = "O has won";
                    }
                    // Update the status bar for winner announcement
                    status.setText(winnerStr);
                }
            }
            // set the status if the match draw
            if (counter == 9 && flag == 0) {
                status.setText("Match Draw");
            }
        }

    }

    // reset the game
    public void gameReset(View view) {
        gameReset();
    }

    public void gameReset(){
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        // remove all the images from the boxes inside the grid
        btn_0.setText("");
        btn_1.setText("");
        btn_2.setText("");
        btn_3.setText("");
        btn_4.setText("");
        btn_5.setText("");
        btn_6.setText("");
        btn_7.setText("");
        btn_8.setText("");

        status.setText("X's Turn - Tap to play");
    }
}