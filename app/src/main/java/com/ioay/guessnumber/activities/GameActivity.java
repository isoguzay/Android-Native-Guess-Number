package com.ioay.guessnumber.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ioay.guessnumber.R;
import com.ioay.guessnumber.model.GuessNumber;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Button buttonTry, buttonNew;
    private ImageView arrowUp, arrowDown, trophy;
    private EditText editTextGuess;
    private TextView textViewMessage, textViewGuess, textViewLeft;
    private RatingBar ratingBar;
    private int temp;
    GuessNumber guess = new GuessNumber();
    Random random = new Random();
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mTextMessage = findViewById(R.id.textView_message);

        Bundle getDataBundle = getIntent().getExtras();
        if (getDataBundle != null) {
            guess.setGuessNumber(Integer.valueOf(getDataBundle.getString("data")));
        }

        GuessNumber gNumber = (GuessNumber) getIntent().getSerializableExtra("numberObject");

        if (String.valueOf(guess.getGuessNumber()) != null) {
            Log.e("Game ... ", String.valueOf(guess.getGuessNumber()));
        }
        Log.e("object ", gNumber.toString());

        buttonTry = findViewById(R.id.button_try);
        arrowUp = findViewById(R.id.arrowup);
        arrowDown = findViewById(R.id.arrowdown);
        trophy = findViewById(R.id.trophy);
        textViewMessage = findViewById(R.id.textView_message);
        textViewLeft = findViewById(R.id.textView_leftRight);
        editTextGuess = findViewById(R.id.editText_guessInput);
        textViewGuess = findViewById(R.id.textView_chance);
        ratingBar = findViewById(R.id.ratingBar);
        buttonNew = findViewById(R.id.button_new);

        final InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        buttonTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonTry.getText().equals("Try It")) {
                    counter--;
                    if (counter != 0) {
                        if (!editTextGuess.getText().equals("")) {
                            try {
                                int guessNum = Integer.valueOf(editTextGuess.getText().toString());

                                if (guessNum < guess.getGuessNumber()) {
                                    arrowDown.setVisibility(View.VISIBLE);
                                    arrowUp.setVisibility(View.INVISIBLE);
                                    editTextGuess.setText("");
                                }
                                if (guessNum > guess.getGuessNumber()) {
                                    arrowDown.setVisibility(View.INVISIBLE);
                                    arrowUp.setVisibility(View.VISIBLE);
                                    editTextGuess.setText("");
                                }
                                if (guessNum == guess.getGuessNumber()) {
                                    arrowDown.setVisibility(View.INVISIBLE);
                                    arrowUp.setVisibility(View.INVISIBLE);
                                    trophy.setVisibility(View.VISIBLE);
                                    textViewMessage.setText("Congratulations !");
                                    textViewMessage.setTextColor(getResources().getColor(R.color.white));
                                    buttonTry.setText("You are Win !");
                                    ratingBar.setVisibility(View.VISIBLE);
                                    ratingBar.setRating(counter + 1);
                                    buttonNew.setVisibility(View.VISIBLE);
                                    textViewLeft.setVisibility(View.INVISIBLE);
                                    textViewGuess.setVisibility(View.INVISIBLE);
                                }
                            } catch (Exception e) {
                                Log.e("Error Home Fragment", e.getMessage());
                            }
                            textViewGuess.setText(" " + counter);
                        } else {
                            Toast.makeText(GameActivity.this, "Please Enter Your Guess !", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (counter == 0) {
                        textViewGuess.setText(" " + counter);
                        buttonTry.setClickable(false);
                        Toast.makeText(GameActivity.this, "Sorry, Game Over :(", Toast.LENGTH_SHORT).show();
                        buttonTry.setText("Game Over");
                        buttonNew.setVisibility(View.VISIBLE);
                        textViewLeft.setVisibility(View.INVISIBLE);
                        textViewGuess.setVisibility(View.INVISIBLE);

                    }
                }
            }
        });


        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonTry.getText().equals("Game Over") || buttonTry.getText().equals("You are Win !")) {
                    final String[] range = {"0..9", "0..25", "0..50", "0..100"};
                    final AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                    builder.setTitle("Select Your Number Range !");
                    builder.setItems(range, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            guess.setNumberRange(range[i]);
                            Log.e("New Range", String.valueOf(range[i]));
                            switch (guess.getNumberRange()) {
                                case "0..9":
                                    temp = random.nextInt(9);
                                    guess.setGuessNumber(temp);
                                    break;
                                case "0..25":
                                    temp = random.nextInt(25);
                                    guess.setGuessNumber(temp);
                                    break;
                                case "0..50":
                                    temp = random.nextInt(50);
                                    guess.setGuessNumber(temp);
                                    break;
                                case "0..100":
                                    temp = random.nextInt(100);
                                    guess.setGuessNumber(temp);
                                    break;
                                default:
                                    temp = random.nextInt(5);
                                    guess.setGuessNumber(temp);
                            }
                            Log.e("New game", String.valueOf(guess.getGuessNumber()));
                        }
                    });
                    builder.show();
                    buttonTry.setClickable(true);
                    buttonTry.setText("Try It");
                    editTextGuess.setText("");
                    buttonNew.setVisibility(View.INVISIBLE);
                    arrowUp.setVisibility(View.INVISIBLE);
                    arrowDown.setVisibility(View.INVISIBLE);
                    textViewLeft.setVisibility(View.VISIBLE);
                    textViewGuess.setVisibility(View.VISIBLE);
                    counter = 5;
                    textViewGuess.setText("5");
                    trophy.setVisibility(View.INVISIBLE);
                    ratingBar.setVisibility(View.INVISIBLE);
                    textViewMessage.setText(R.string.game_info);
                    textViewMessage.setTextColor(getResources().getColor(R.color.pomegranate));
                }
            }
        });
    }

}

