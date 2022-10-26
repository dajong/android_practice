package com.example.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;
    private boolean single_mode = false;
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
    private String name_x;
    private String name_o;
    private TextView name_x_label;
    private TextView name_o_label;
    private TextView namex_score;
    private TextView nameo_score;
    private int score_x;
    private int score_o;

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
        name_x_label = findViewById(R.id.namex_label);
        name_o_label = findViewById(R.id.nameo_label);
        nameo_score = findViewById(R.id.nameo_score);
        namex_score = findViewById(R.id.namex_score);

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        name_x = savedValues.getString("name_x", "X");
        name_o = savedValues.getString("name_o", "O");

        name_x_label.setText("Score " + name_x + ": ");
        name_o_label.setText("Score " + name_o + ": ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.menu_night_mode).setTitle(R.string.day_mode);
            menu.findItem(R.id.menu_night_mode).setIcon(R.drawable.ic_light_mode);
            menu.findItem(R.id.menu_new_game).setIcon(R.drawable.ic_new_game_dark_mode);
        } else {
            menu.findItem(R.id.menu_night_mode).setIcon(R.drawable.ic_night_mode);
            menu.findItem(R.id.menu_new_game).setIcon(R.drawable.ic_new_game_light_mode);
            menu.findItem(R.id.menu_night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_new_game:
                gameReset();
                return true;
            case R.id.menu_night_mode:
                int nightMode = AppCompatDelegate.getDefaultNightMode();
                if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode
                            (AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode
                            (AppCompatDelegate.MODE_NIGHT_YES);
                }
                // Recreate the activity for the theme change to take effect.
                recreate();
                return true;

            case R.id.menu_single_mode:
                gameReset();
                if (single_mode) {
                    single_mode = false;
                } else {
                    single_mode = true;
                }
                return true;
            case R.id.menu_settings:
                Intent intent = new Intent(MainActivity.this,
                        Settings.class);
                startActivityForResult(intent, TEXT_REQUEST);
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
        for (int i = 0; i < gameStatus.length(); i++) {
            gameState[i] = gameStatus.charAt(i) - '0';
        }
        counter = savedValues.getInt("count", 0);

        // Player
        activePlayer = savedValues.getInt("activePlayer", 0);
        name_x = (savedValues.getString("name_x", "X"));
        name_o = (savedValues.getString("name_o", "O"));
        name_x_label.setText("Score " + name_x + ": ");
        name_o_label.setText("Score " + name_o + ": ");

        namex_score.setText(String.valueOf(savedValues.getInt("score_x", 0)));
        nameo_score.setText(String.valueOf(savedValues.getInt("score_o", 0)));
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
        editor.putString("name_x", name_x);
        editor.putString("name_o", name_o);
        editor.putInt("activePlayer", activePlayer);
        editor.putBoolean("gameActive", gameActive);

        // Putting game state into cache
        StringBuilder sb = new StringBuilder();
        for (int i : gameState) {
            sb.append(i);
        }
        editor.putString("gameState", sb.toString());
        editor.putInt("count", counter);

        editor.putString("name_x", name_x);
        editor.putString("name_o", name_o);

        editor.putInt("score_x", score_x);
        editor.putInt("score_o", score_o);

        editor.commit();
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String new_name_x = data.getStringExtra(Settings.NEW_NAME_X);
                String new_name_o = data.getStringExtra(Settings.NEW_NAME_O);
                name_x = new_name_x;
                name_o = new_name_o;

                SharedPreferences.Editor editor = savedValues.edit();


                editor.putString("name_x", name_x);
                editor.putString("name_o", name_o);


                if (counter < 9) {
                    if (activePlayer == 0 && !gameActive) {
                        editor.putString("status", name_x + " has won");
                    } else if (activePlayer == 1 && !gameActive) {
                        editor.putString("status", name_o + " has won");
                    } else if (activePlayer == 0 && gameActive) {
                        editor.putString("status", name_x + " 's Turn - Tap to play");
                    } else if (activePlayer == 1 && gameActive) {
                        editor.putString("status", name_o + " 's Turn - Tap to play");
                    }

                }

                editor.commit();
            }
        }
    }

    // this function will be called every time a
    // players tap in an empty box of the grid
    public void playerTap(View view) {

        curButton = (Button) view;
        int tappedButton = Integer.parseInt(curButton.getTag().toString());
        // game reset function will be called
        // if someone wins or the boxes are full
        if (!gameActive) {
            status.setText("Start a new game!");
        } else {
            // if the tapped image is empty
            if (gameState[tappedButton] == 2) {
                // increase the counter
                // after every tap
                counter++;

                // check if its the last box
                if (counter == 9) {
                    // reset the game
                    gameActive = false;
                }

                // mark this position
                gameState[tappedButton] = activePlayer;

                // change the active player
                // from 0 to 1 or 1 to 0
                if (activePlayer == 0) {
                    // set the image of x
                    curButton.setText("X");
                    activePlayer = 1;
                    // change the status
                    status.setText(name_o + " 's Turn - Tap to play");
                } else {
                    // set the image of o
                    curButton.setText("O");
                    activePlayer = 0;

                    // change the status
                    status.setText(name_x + " 's Turn - Tap to play");
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
                        winnerStr = name_x + " has won";
                        score_x++;
                        namex_score.setText(String.valueOf(score_x));
                    } else {
                        winnerStr = name_o + " has won";
                        score_o++;
                        nameo_score.setText(String.valueOf(score_o));
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

    public void gameReset() {
        gameActive = true;
        activePlayer = 0;
        counter = 0;
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

        status.setText(name_x + " 's Turn - Tap to play");
    }


    public void reset_score(View view) {
        score_x = 0;
        score_o = 0;

        namex_score.setText(String.valueOf(savedValues.getInt("score_x", 0)));
        nameo_score.setText(String.valueOf(savedValues.getInt("score_o", 0)));
    }

    public Button selectButton(String id) {
        switch (id) {
            case "btn_0":
                return btn_0;
            case "btn_1":
                return btn_1;
            case "btn_2":
                return btn_2;
            case "btn_3":
                return btn_3;
            case "btn_4":
                return btn_4;
            case "btn_5":
                return btn_5;
            case "btn_6":
                return btn_6;
            case "btn_7":
                return btn_7;
            case "btn_8":
                return btn_8;
        }

        return null;
    }
}