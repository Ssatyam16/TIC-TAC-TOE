package com.example.home.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0 for red
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // 2 means unplayed.
    int[][] winningLocations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8},
            {2, 4, 6}};
    boolean gameOver = false;

    public void gameLogic(View view) {

        ImageView tappedView = (ImageView) view;

        int tappedLocation = Integer.parseInt(view.getTag().toString());

        if (gameState[tappedLocation] == 2 && !gameOver) {

            gameState[tappedLocation] = activePlayer;

            tappedView.setTranslationY(-3000f);

            if (activePlayer == 0) {

                tappedView.setImageResource(R.drawable.tick);
                activePlayer = 1;

            } else if (activePlayer == 1) {

                tappedView.setImageResource(R.drawable.toe);
                activePlayer = 0;

            }


            tappedView.animate().translationYBy(3000f).setDuration(500);

            String msg = "";

            for (int[] winningPosition : winningLocations) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {

                    if (activePlayer == 0)
                        msg = "Player 2 is Winner!";

                    if (activePlayer == 1)
                        msg = "Player 1 is Winner!";


                    LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
                    winnerLayout.setVisibility(View.VISIBLE);

                    TextView winnerMsg = (TextView) findViewById(R.id.textView);
                    winnerMsg.setText(msg);

                    gameOver = true;
                }

            }

        }


    }

    public void playAgain(View view){

        LinearLayout winnerLayout = (LinearLayout)findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(View.INVISIBLE);

        gameOver = false;
        activePlayer = 0;

        for(int i = 0; i < gameState.length; i++)
            gameState[i] = 2;

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++)
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(View.INVISIBLE);
    }

}
