package com.ioay.guessnumber.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ioay.guessnumber.R;
import com.ioay.guessnumber.activities.GameActivity;
import com.ioay.guessnumber.activities.MainActivity;
import com.ioay.guessnumber.model.GuessNumber;

import java.util.Random;

public class HomeFragment extends Fragment {
    private Button buttonTry;
    private ImageView arrowUp, arrowDown, trophy;
    private EditText editTextGuess;
    private TextView textViewMessage,textViewGuess;
    private int guessNumber;
    private int counter = 5;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            guessNumber = bundle.getInt("data", 0);
        }
        Log.e("home -- ", String.valueOf(guessNumber));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        buttonTry = v.findViewById(R.id.button_try);
        arrowUp = v.findViewById(R.id.arrowup);
        arrowDown = v.findViewById(R.id.arrowdown);
        trophy = v.findViewById(R.id.trophy);
        textViewMessage = v.findViewById(R.id.textView_message);
        editTextGuess = v.findViewById(R.id.editText_guessInput);
        textViewGuess = v.findViewById(R.id.textView_chance);

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
                            }
                        } catch (Exception e) {
                            Log.e("Error Home Fragment", e.getMessage());
                        }
                        textViewGuess.setText(" "+counter);
                    } else {
                        Toast.makeText(getContext(), "Please Enter Your Guess !", Toast.LENGTH_SHORT).show();
                    }
                }if(counter == 0){
                   editTextGuess.setFocusable(false);
                   buttonTry.setClickable(false);
                    Toast.makeText(getContext(), "Sorry, Game Over :(", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }

}
