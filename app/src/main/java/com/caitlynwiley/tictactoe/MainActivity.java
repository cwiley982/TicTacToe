package com.caitlynwiley.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView blue;
    ImageView green;
    //1 means blue, 2 means green, 0 is empty
    int[][] board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        board = new int[3][3];
    }

    public void switchTurn(View view) {
        ImageView piece = (ImageView) view;
        if (blue.getAlpha() == 1f) {
            piece.setImageResource(R.drawable.blue_piece);
        } else {
            piece.setImageResource(R.drawable.green_piece);
        }
        piece.setAlpha(1f);

        int i = -1;
        int j = -1;
        switch (view.getId()) {
            case R.id.square1:
                i = 0;
                j = 0;
                break;
            case R.id.square2:
                i = 0;
                j = 1;
                break;
            case R.id.square3:
                i = 0;
                j = 2;
                break;
            case R.id.square4:
                i = 1;
                j = 0;
                break;
            case R.id.square5:
                i = 1;
                j = 1;
                break;
            case R.id.square6:
                i = 1;
                j = 2;
                break;
            case R.id.square7:
                i = 2;
                j = 0;
                break;
            case R.id.square8:
                i = 2;
                j = 1;
                break;
            case R.id.square9:
                i = 2;
                j = 2;
                break;
        }

        if (blue.getAlpha() == 1f) {
            blue.setAlpha(0f);
            green.setAlpha(1f);
            board[i][j] = 1;
        } else {
            blue.setAlpha(1f);
            green.setAlpha(0f);
            board[i][j] = 2;
        }

        boolean playerWon = checkForWin(board[i][j]);

        if (playerWon) {
            Toast.makeText(MainActivity.this, "Player " + board[i][j] + " won!", Toast.LENGTH_SHORT).show();
        }

        //TODO: Once a player wins, end the game (or restart)
    }

    private String printBoard() {
        String b = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b += board[i][j];
            }
            b += "/n";
        }
        return b;
    }

    private boolean checkForWin(int player) {
        //check across rows
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == player) {
                    count++;
                }
            }
            if (count == 3) {
                return true;
            } else {
                count = 0;
            }
        }
        //check down columns
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (board[i][j] == player) {
                    count++;
                }
            }
            if (count == 3) {
                return true;
            } else {
                count = 0;
            }
        }
        //check diagonals
        for (int i = 0, j = 0; i < 3 && j < 3; i++, j++) {
            if (board[i][j] == player) {
                count ++;
            }
        }
        if (count == 3) {
            return true;
        } else {
            count = 0;
        }

        for (int i = 2, j = 0; i >= 0 && j < 3; i--, j++) {
            if (board[i][j] == player) {
                count ++;
            }
        }
        return count == 3;
    }
}
