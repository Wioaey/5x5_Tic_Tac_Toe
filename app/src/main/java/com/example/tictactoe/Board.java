package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Board extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[5][5];
    private boolean player1turn = true;
    private int roundCount;
    private int player1points;
    private int player2points;

    private TextView txtViewP1;
    private TextView txtViewP2;

    private int[] colorsArray;
    private int redColor = 0;
    private int blueColor = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        txtViewP1 = findViewById(R.id.text_view_p1);
        txtViewP2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(Board.this);
            }
        }

        colorsArray = getIntent().getIntArrayExtra("colors");
        unpackColors();

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        roundCount++;
        if (player1turn) {
            ((Button) v).setText("X");
            setRedColor(v);
            player1turn = false;

            return;
        } else {
            ((Button) v).setText("O");
            setBlueColor(v);
            player1turn = true;
            return;
        }


    }

    private boolean checkForWin() {
        String[][] field = new String[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                field[i][j]  = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 5; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][1])) {

            }
        }
        return true;
    }

    private void unpackColors() {
        redColor = colorsArray[0];
        blueColor = colorsArray[1];
    }

    private void setRedColor(View v) {
        switch (redColor) {
            case 1:
                ((Button) v).setBackgroundTintList(getResources().getColorStateList(R.color.red1));
                break;

            case 2:
                ((Button) v).setBackgroundTintList(getResources().getColorStateList(R.color.red2));
                break;

            case 3:
                ((Button) v).setBackgroundTintList(getResources().getColorStateList(R.color.red3));
                break;

            case 4:
                ((Button) v).setBackgroundTintList(getResources().getColorStateList(R.color.red4));
                break;

            case 5:
                ((Button) v).setBackgroundTintList(getResources().getColorStateList(R.color.red5));
                break;
        }
    }

    private void setBlueColor(View v) {
        switch (blueColor) {
            case 1:
                ((Button) v).setBackgroundTintList(getResources().getColorStateList(R.color.blue1));
                break;

            case 2:
                ((Button) v).setBackgroundTintList(getResources().getColorStateList(R.color.blue2));
                break;

            case 3:
                ((Button) v).setBackgroundTintList(getResources().getColorStateList(R.color.blue3));
                break;

            case 4:
                ((Button) v).setBackgroundTintList(getResources().getColorStateList(R.color.blue4));
                break;

            case 5:
                ((Button) v).setBackgroundTintList(getResources().getColorStateList(R.color.blue5));
                break;

        }
    }
}
