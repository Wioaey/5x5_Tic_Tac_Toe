package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
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

    private int colorRed = 1;
    private int colorBlue = 1;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.Title);
        instructions = findViewById(R.id.Instructions);
        playerOne = findViewById(R.id.player1);
        playerTwo = findViewById(R.id.player2);

        mQueue = Volley.newRequestQueue(this);
        settingColors(3);

        Button startGame = findViewById(R.id.startGame);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] colorsArray = new int[2];
                colorsArray[0] = colorRed;
                colorsArray[1] = colorBlue;
                Intent intent = new Intent(view.getContext(), Board.class);
                intent.putExtra("colors", colorsArray);
                startActivity(intent);
            }
        });
    }

    private void settingColors(int i) {

        Button blueOne = findViewById(R.id.blue1);
        blueOne.setOnClickListener(this);
        Button blueTwo = findViewById(R.id.blue2);
        blueTwo.setOnClickListener(this);
        Button blueThree = findViewById(R.id.blue3);
        blueThree.setOnClickListener(this);
        Button blueFour = findViewById(R.id.blue4);
        blueFour.setOnClickListener(this);
        Button blueFive = findViewById(R.id.blue5);
        blueFive.setOnClickListener(this);

        ImageButton redOne = findViewById(R.id.red1);
        redOne.setOnClickListener(this);
        getRandomURL("https://random.dog/woof.json", redOne);

        Button redTwo = findViewById(R.id.red2);
        redTwo.setOnClickListener(this);
        Button redThree = findViewById(R.id.red3);
        redThree.setOnClickListener(this);
        Button redFour = findViewById(R.id.red4);
        redFour.setOnClickListener(this);
        Button redFive = findViewById(R.id.red5);
        redFive.setOnClickListener(this);

        if (i == 0) {
            //reset reds
            redFive.setText("");
            redFour.setText("");
            redThree.setText("");
            redTwo.setText("");
        }
        if (i == 1) {
            //reset blues
            blueFive.setText("");
            blueFour.setText("");
            blueThree.setText("");
            blueTwo.setText("");
            blueOne.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.red1:
                settingColors(0);
                colorRed = 1;
                ((Button) v).setText("X");
                break;

            case R.id.red2:
                settingColors(0);
                colorRed = 2;
                ((Button) v).setText("X");
                break;

            case R.id.red3:
                settingColors(0);
                colorRed = 3;
                ((Button) v).setText("X");
                break;

            case R.id.red4:
                settingColors(0);
                colorRed = 4;
                ((Button) v).setText("X");
                break;

            case R.id.red5:
                settingColors(0);
                colorRed = 5;
                ((Button) v).setText("X");
                break;

            //now blues

            case R.id.blue1:
                settingColors(1);
                colorBlue = 1;
                ((Button) v).setText("X");
                break;

            case R.id.blue2:
                settingColors(1);
                colorBlue = 2;
                ((Button) v).setText("X");
                break;

            case R.id.blue3:
                settingColors(1);
                colorBlue = 3;
                ((Button) v).setText("X");
                break;

            case R.id.blue4:
                settingColors(1);
                colorBlue = 4;
                ((Button) v).setText("X");
                break;

            case R.id.blue5:
                settingColors(1);
                colorBlue = 5;
                ((Button) v).setText("X");
                break;
        }
    }

    private void getRandomURL(String input, final ImageButton toChangeImage) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, input, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String imageUrl = response.get("url").toString();
                            Picasso.get().load(imageUrl).
                                    resize(200, 200).into(toChangeImage);
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



