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
    private TextView textViewMessage;
    private int guessNumber;

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

        buttonTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextGuess.getText().equals("")) {
                    try {
                        int guess = Integer.valueOf(editTextGuess.getText().toString());

                        if (guess < guessNumber) {
                            arrowDown.setVisibility(View.VISIBLE);
                            arrowUp.setVisibility(View.INVISIBLE);
                        }
                        if (guess > guessNumber) {
                            arrowDown.setVisibility(View.INVISIBLE);
                            arrowUp.setVisibility(View.VISIBLE);
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
                } else {
                    Toast.makeText(getContext(), "Please Enter Your Guess !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

}
