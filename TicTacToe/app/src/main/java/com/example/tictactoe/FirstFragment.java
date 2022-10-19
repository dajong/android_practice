package com.example.tictactoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tictactoe.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
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
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        status = getView().findViewById(R.id.status_text);
        btn_0 = getView().findViewById(R.id.btn_0);
        btn_1 = getView().findViewById(R.id.btn_1);
        btn_2 = getView().findViewById(R.id.btn_2);
        btn_3 = getView().findViewById(R.id.btn_3);
        btn_4 = getView().findViewById(R.id.btn_4);
        btn_5 = getView().findViewById(R.id.btn_5);
        btn_6 = getView().findViewById(R.id.btn_6);
        btn_7 = getView().findViewById(R.id.btn_7);
        btn_8 = getView().findViewById(R.id.btn_8);
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // this function will be called every time a
    // players tap in an empty box of the grid
    public void playerTap(View view) {

        curButton = btn_0;
        int tappedImage = Integer.parseInt(curButton.getTag().toString());
        // game reset function will be called
        // if someone wins or the boxes are full
        if (!gameActive) {
            gameReset(view);
        }

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

    // reset the game
    public void gameReset(View view) {
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