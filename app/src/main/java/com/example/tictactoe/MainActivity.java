package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView title;
    private TextView instructions;
    private TextView playerOne;
    private TextView playerTwo;
    private TextView debug;

    private int imageOf1 = 0;
    private int imageOf2 = 0;
    private RequestQueue mQueue;

    private ImageButton[][] imageBut = new ImageButton[2][5];
    private TextView[][] txtSelected = new TextView[2][5];

    private String[][] storeURL = new String[2][5];
    private String singleURL;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.Title);
        instructions = findViewById(R.id.Instructions);
        playerOne = findViewById(R.id.player1);
        playerTwo = findViewById(R.id.player2);
        debug = findViewById(R.id.debug);

        mQueue = Volley.newRequestQueue(this);
        settingImageButton();
        settingSelected();

        Button getNew = findViewById(R.id.startGame2);
        getNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingImageButton();
                settingSelected();
            }
        });

        Button startGame = findViewById(R.id.startGame);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] imageUrls = new String[2];
                imageUrls[0] = storeURL[0][imageOf1];
                imageUrls[1] = storeURL[1][imageOf2];

                Intent intent = new Intent(view.getContext(), Board.class);
                intent.putExtra("URLs", imageUrls);
                startActivity(intent);
            }
        });
    }

    private void settingImageButton() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                String buttonID = "imageB_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                imageBut[i][j] = findViewById(resID);
                imageBut[i][j].setOnClickListener(MainActivity.this);

                if (i == 0) {
                    getRandomURL("https://dog.ceo/api/breeds/image/random", imageBut[i][j],
                            "dog", i, j);
                } else if (i == 1) {
                    getRandomURL("https://aws.random.cat/meow", imageBut[i][j],
                            "cat", i, j);
                }
            }
        }
    }

    private void settingSelected() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                String buttonID = "pet_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                txtSelected[i][j] = findViewById(resID);
            }
        }
    }

    private void resetDogs(int j) {
        for (int qw = 0; qw < 5; qw++) {
            if (qw == j) {
                txtSelected[0][j].setText(getString(R.string.selection));
                continue;
            }
            txtSelected[0][qw].setText("");
        }
    }

    private void resetCats(int j) {
        for (int qw = 0; qw < 5; qw++) {
            if (qw == j) {
                txtSelected[1][j].setText(getString(R.string.selection));
                continue;
            }
            txtSelected[1][qw].setText("");
        }
    }

    @Override
    public void onClick(View v) {
        ImageButton toBeChanged = (ImageButton) v;
        switch (toBeChanged.getId()) {
            case R.id.imageB_00:
                imageOf1 = 0;
                resetDogs(0);
                debug.setText(storeURL[0][0]);
                break;

            case R.id.imageB_01:
                imageOf1 = 1;
                resetDogs(1);
                debug.setText(storeURL[0][1]);
                break;

            case R.id.imageB_02:
                imageOf1 = 2;
                resetDogs(2);
                debug.setText(storeURL[0][2]);
                break;

            case R.id.imageB_03:
                imageOf1 = 3;
                resetDogs(3);
                debug.setText(storeURL[0][3]);
                break;

            case R.id.imageB_04:
                imageOf1 = 4;
                resetDogs(4);
                debug.setText(storeURL[0][4]);
                break;

            //now blues

            case R.id.imageB_10:
                imageOf2 = 0;
                resetCats(0);
                debug.setText(storeURL[1][0]);
                break;

            case R.id.imageB_11:
                imageOf2 = 1;
                resetCats(1);
                debug.setText(storeURL[1][1]);
                break;

            case R.id.imageB_12:
                imageOf2 = 2;
                resetCats(2);
                debug.setText(storeURL[1][2]);
                break;

            case R.id.imageB_13:
                imageOf2 = 3;
                resetCats(3);
                debug.setText(storeURL[1][3]);
                break;

            case R.id.imageB_14:
                imageOf2 = 4;
                resetCats(4);
                debug.setText(storeURL[1][4]);
                break;
        }
    }

    private void getRandomURL(String input, final ImageButton toChangeImage, final String pet,
                              final int i, final int j) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, input, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (pet.equals("dog")) {
                                String imageUrl = response.get("message").toString();
                                storeURL[i][j] = imageUrl;
                                Picasso.get().load(imageUrl).resize(200,200).
                                        into(toChangeImage);
                            } else {
                                String catURL = response.getString("file");
                                storeURL[i][j] = catURL;
                                Picasso.get().load(catURL).resize(200,200).
                                        into(toChangeImage);
                            }
                            // 200 by 200 is the one
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}



