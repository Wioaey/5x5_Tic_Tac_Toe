package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Board extends AppCompatActivity implements View.OnClickListener {

    private ImageButton[][] buttons = new ImageButton[5][5];
    private boolean player1turn = true;
    private int roundCount;
    private int player1points;
    private int player2points;

    private TextView txtViewP1;
    private TextView txtViewP2;

    private String[] urls;
    private String dogUrl;
    private String catUrl;

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

        urls = getIntent().getStringArrayExtra("URLs");
        unpackUrls();

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
        ImageButton pressed = (ImageButton) v;

        roundCount++;
        if (player1turn) {
            Picasso.get().load(dogUrl).fit().into(pressed);
            player1turn = false;

            return;
        } else {
            Picasso.get().load(catUrl).fit().into(pressed);
            player1turn = true;
            return;
        }


    }

    private boolean checkForWin() {
        String[][] field = new String[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

            }
        }

        for (int i = 0; i < 5; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][1])) {

            }
        }
        return true;
    }

    private void unpackUrls() {
        dogUrl = urls[0];
        catUrl = urls[1];
    }
}
