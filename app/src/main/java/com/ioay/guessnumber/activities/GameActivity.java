package com.ioay.guessnumber.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ioay.guessnumber.R;
import com.ioay.guessnumber.model.GuessNumber;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Bundle bundle = new Bundle();
    private Button buttonTry;
    private ImageView arrowUp, arrowDown, trophy;
    private EditText editTextGuess;
    private TextView textViewMessage,textViewGuess;
    private int guessNumber,temp;
    GuessNumber  guess = new GuessNumber();
    Random random = new Random();
    private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mTextMessage = findViewById(R.id.textView_message);

        Bundle getDataBundle = getIntent().getExtras();
        if (getDataBundle != null) {
            guessNumber = Integer.valueOf(getDataBundle.getString("data"));
        }

        GuessNumber gNumber = (GuessNumber) getIntent().getSerializableExtra("numberObject");

        if (String.valueOf(guessNumber)!= null) {
            Log.e("Game ... ", String.valueOf(guessNumber));
        }
            Log.e("object ", gNumber.toString());

        buttonTry = findViewById(R.id.button_try);
        arrowUp = findViewById(R.id.arrowup);
        arrowDown = findViewById(R.id.arrowdown);
        trophy = findViewById(R.id.trophy);
        textViewMessage = findViewById(R.id.textView_message);
        editTextGuess = findViewById(R.id.editText_guessInput);
        textViewGuess = findViewById(R.id.textView_chance);

        if (buttonTry.getText().equals("Try It")){
            buttonTry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counter--;

                    if(counter !=0){
                        if (!editTextGuess.getText().equals("")) {
                            try {
                                int guess = Integer.valueOf(editTextGuess.getText().toString());

                                if (guess < guessNumber) {
                                    arrowDown.setVisibility(View.VISIBLE);
                                    arrowUp.setVisibility(View.INVISIBLE);
                                    editTextGuess.setText("");
                                }
                                if (guess > guessNumber) {
                                    arrowDown.setVisibility(View.INVISIBLE);
                                    arrowUp.setVisibility(View.VISIBLE);
                                    editTextGuess.setText("");
                                }
                                if (guess == guessNumber) {
                                    arrowDown.setVisibility(View.INVISIBLE);
                                    arrowUp.setVisibility(View.INVISIBLE);
                                    trophy.setVisibility(View.VISIBLE);
                                    textViewMessage.setText("Congratulations !");
                                    textViewMessage.setTextColor(getResources().getColor(R.color.white));
                                    editTextGuess.setFocusable(false);
                                    buttonTry.setClickable(false);
                                    buttonTry.setText("Game Over");
                                }
                            } catch (Exception e) {
                                Log.e("Error Home Fragment", e.getMessage());
                            }
                            textViewGuess.setText(" "+counter);
                        } else {
                            Toast.makeText(GameActivity.this, "Please Enter Your Guess !", Toast.LENGTH_SHORT).show();
                        }
                    }if(counter == 0){
                        textViewGuess.setText(" "+counter);
                        editTextGuess.setFocusable(false);
                        buttonTry.setClickable(false);
                        Toast.makeText(GameActivity.this, "Sorry, Game Over :(", Toast.LENGTH_SHORT).show();
                        buttonTry.setText("New Game");
                    }
                }
            });
        }


        if (buttonTry.getText().equals("New Game")){
            buttonTry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String[] range = {"0..9", "0..25", "0..50", "0..100"};
                    final AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                    builder.setTitle("Select Your Number Range !");
                    builder.setItems(range, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            guess.setNumberRange(range[i]);

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
                        }
                    });
                    builder.show();
                }

            });
            buttonTry.setText("Try It");
        }



    }

}

