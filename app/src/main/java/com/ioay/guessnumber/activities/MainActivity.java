package com.ioay.guessnumber.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ioay.guessnumber.R;
import com.ioay.guessnumber.model.GuessNumber;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    GuessNumber guessNumber = new GuessNumber();
    private String range;
    private int temp;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = findViewById(R.id.button_start);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] range = {"0..9", "0..25", "0..50", "0..100"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Your Number Range !");
                builder.setItems(range, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        guessNumber.setNumberRange(range[i]);

                        switch (guessNumber.getNumberRange()) {
                            case "0..9":
                                temp = random.nextInt(9);
                                guessNumber.setGuessNumber(temp);
                                break;
                            case "0..25":
                                temp = random.nextInt(25);
                                guessNumber.setGuessNumber(temp);
                                break;
                            case "0..50":
                                temp = random.nextInt(50);
                                guessNumber.setGuessNumber(temp);
                                break;
                            case "0..100":
                                temp = random.nextInt(100);
                                guessNumber.setGuessNumber(temp);
                                break;
                            default:
                                temp = random.nextInt(5);
                                  guessNumber.setGuessNumber(temp);
                        }

                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        intent.putExtra("data", String.valueOf(guessNumber.getGuessNumber()));
                        intent.putExtra("numberObject", guessNumber);

                        Log.e("Main ac Number", String.valueOf(guessNumber.getGuessNumber()));
                        startActivity(intent);
                        finish();
                    }
                });
                builder.show();
            }
        });
    }
}
