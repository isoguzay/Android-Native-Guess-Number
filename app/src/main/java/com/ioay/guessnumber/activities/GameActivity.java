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
    private int counter, ratioLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initializeComponents();

        Bundle getDataBundle = getIntent().getExtras();
        if (getDataBundle != null) {
            guess.setGuessNumber(Integer.valueOf(getDataBundle.getString("data")));
        }

        GuessNumber gNumber = (GuessNumber) getIntent().getSerializableExtra("numberObject");
        counter = gNumber.getCounter();
        ratioLevel = gNumber.getRatioLevel();

        if (String.valueOf(guess.getGuessNumber()) != null) {
            Log.e("Game ... ", String.valueOf(guess.getGuessNumber()));
        }
        Log.e("object ", gNumber.toString());
        Log.e("counter ", String.valueOf(counter));

        textViewGuess.setText(String.valueOf(counter));

        final InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        buttonTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonTry.getText().equals("Try It")) {
                    counter--;
                    if (!(counter < 0)) {
                        if (!editTextGuess.getText().equals("")) {
                            try {
                                int guessNum = Integer.valueOf(editTextGuess.getText().toString());

                                if (guessNum < guess.getGuessNumber()) {
                                    setValuesIncrease();
                                }
                                if (guessNum > guess.getGuessNumber()) {
                                    setValuesDecrease();
                                }
                                if (guessNum == guess.getGuessNumber()) {
                                    setValuesWin();
                                }
                            } catch (Exception e) {
                                Log.e("Error Home Fragment", e.getMessage());
                            }
                            textViewGuess.setText(" " + counter);
                        } else {
                            Toast.makeText(GameActivity.this, "Please Enter Your Guess !", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (counter <= 0 && !textViewMessage.getText().equals("Congratulations !")) {
                        setValuesGameOver();
                    }
                }
            }
        });

        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonTry.getText().equals("Game Over") || buttonTry.getText().equals("You Win !")) {
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
                                    counter = 5;
                                    textViewGuess.setText(String.valueOf(5));
                                    ratioLevel=1;
                                    break;
                                case "0..25":
                                    temp = random.nextInt(25);
                                    guess.setGuessNumber(temp);
                                    counter = 8;
                                    textViewGuess.setText(String.valueOf(8));
                                    ratioLevel=2;
                                    break;
                                case "0..50":
                                    temp = random.nextInt(50);
                                    guess.setGuessNumber(temp);
                                    counter = 10;
                                    textViewGuess.setText(String.valueOf(10));
                                    ratioLevel=3;
                                    break;
                                case "0..100":
                                    temp = random.nextInt(100);
                                    guess.setGuessNumber(temp);
                                    counter = 12;
                                    textViewGuess.setText(String.valueOf(12));
                                    ratioLevel=4;
                                    break;
                                default:
                                    temp = random.nextInt(5);
                                    guess.setGuessNumber(temp);
                            }
                            Log.e("New game", String.valueOf(guess.getGuessNumber()));
                        }
                    });
                    builder.show();
                    setValuesNewGame();
                }
            }
        });
    }

    public void setValuesGameOver() {
        textViewGuess.setText(" " + counter);
        buttonTry.setClickable(false);
        buttonTry.setText("Game Over");
        textViewMessage.setText("The Number is " + guess.getGuessNumber());
        trophy.setImageResource(R.drawable.lose);
        trophy.setVisibility(View.VISIBLE);
        buttonNew.setVisibility(View.VISIBLE);
        textViewLeft.setVisibility(View.INVISIBLE);
        textViewGuess.setVisibility(View.INVISIBLE);
    }

    public void setValuesDecrease() {
        arrowDown.setVisibility(View.VISIBLE);
        arrowUp.setVisibility(View.INVISIBLE);
        editTextGuess.setText("");
    }

    public void setValuesIncrease() {
        arrowDown.setVisibility(View.INVISIBLE);
        arrowUp.setVisibility(View.VISIBLE);
        editTextGuess.setText("");
    }

    public void setValuesWin() {
        arrowDown.setVisibility(View.INVISIBLE);
        arrowUp.setVisibility(View.INVISIBLE);
        trophy.setImageResource(R.drawable.trophy);
        trophy.setVisibility(View.VISIBLE);
        textViewMessage.setText("Congratulations !");
        textViewMessage.setTextColor(getResources().getColor(R.color.white));
        buttonTry.setText("You Win !");
        ratingBar.setVisibility(View.VISIBLE);
        setRatioBarLevel(ratioLevel);
        buttonNew.setVisibility(View.VISIBLE);
        textViewLeft.setVisibility(View.INVISIBLE);
        textViewGuess.setVisibility(View.INVISIBLE);
    }

    public void setValuesNewGame() {
        buttonTry.setClickable(true);
        buttonTry.setText("Try It");
        editTextGuess.setText("");
        buttonNew.setVisibility(View.INVISIBLE);
        arrowUp.setVisibility(View.INVISIBLE);
        arrowDown.setVisibility(View.INVISIBLE);
        textViewLeft.setVisibility(View.VISIBLE);
        textViewGuess.setVisibility(View.VISIBLE);
        trophy.setVisibility(View.INVISIBLE);
        ratingBar.setVisibility(View.INVISIBLE);
        textViewMessage.setText(R.string.game_info);
        textViewMessage.setTextColor(getResources().getColor(R.color.pomegranate));
    }

    public void initializeComponents() {
        mTextMessage = findViewById(R.id.textView_message);
        buttonTry = findViewById(R.id.button_try);
        arrowUp = findViewById(R.id.arrow_up);
        arrowDown = findViewById(R.id.arrow_down);
        trophy = findViewById(R.id.trophy);
        textViewMessage = findViewById(R.id.textView_message);
        textViewLeft = findViewById(R.id.textView_leftRight);
        editTextGuess = findViewById(R.id.editText_guessInput);
        textViewGuess = findViewById(R.id.textView_chance);
        ratingBar = findViewById(R.id.ratingBar);
        buttonNew = findViewById(R.id.button_new);
    }

    public void setRatioBarLevel(int _ratioLevel) {
        if (_ratioLevel == 1) {
            if (counter == 4) {
                ratingBar.setRating(5);
            }
            if (counter == 3) {
                ratingBar.setRating(4);
            }
            if (counter == 2) {
                ratingBar.setRating(3);
            }
            if (counter == 1) {
                ratingBar.setRating(2);
            }
            if (counter == 0) {
                ratingBar.setRating(1);
            }
        }
        if (_ratioLevel == 2) {
            if (counter == 7) {
                ratingBar.setRating(5);
            }
            if (counter >= 5 && counter <= 6) {
                ratingBar.setRating(4);
            }
            if (counter >= 3 && counter <= 4) {
                ratingBar.setRating(3);
            }
            if (counter >= 1 && counter <= 2) {
                ratingBar.setRating(2);
            }
            if (counter == 0) {
                ratingBar.setRating(1);
            }
        }
        if (_ratioLevel == 3) {
            if (counter == 9) {
                ratingBar.setRating(5);
            }
            if (counter >= 7 && counter <= 8) {
                ratingBar.setRating(4);
            }
            if (counter >= 4 && counter <= 6) {
                ratingBar.setRating(3);
            }
            if (counter >= 1 && counter <= 3) {
                ratingBar.setRating(2);
            }
            if (counter == 0) {
                ratingBar.setRating(1);
            }
        }
        if (_ratioLevel == 4) {
            if (counter == 11) {
                ratingBar.setRating(5);
            }
            if (counter >= 9 && counter <= 10) {
                ratingBar.setRating(4);
            }
            if (counter >= 5 && counter <= 8) {
                ratingBar.setRating(3);
            }
            if (counter >= 1 && counter <= 4) {
                ratingBar.setRating(2);
            }
            if (counter == 0) {
                ratingBar.setRating(1);
            }
        }
    }
}

