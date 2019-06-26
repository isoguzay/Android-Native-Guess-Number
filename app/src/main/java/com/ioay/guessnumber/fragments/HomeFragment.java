package com.ioay.guessnumber.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

import com.ioay.guessnumber.R;
import com.ioay.guessnumber.activities.MainActivity;

import java.util.Random;

public class HomeFragment extends Fragment {
    private Button buttonTry;
    private ImageView arrowUp, arrowDown, trophy;
    private EditText editTextGuess;
    private TextView textViewMessage;
    private int number;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            number = Integer.valueOf(getArguments().getString("Number"));
            Log.e("Home Fragment ", String.valueOf(number));
        }
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
        Log.e("Home Fragment View ", String.valueOf(number));

        buttonTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int guess = Integer.valueOf(editTextGuess.getText().toString());

                if (guess > number) {
                    arrowDown.setVisibility(View.VISIBLE);
                    arrowUp.setVisibility(View.INVISIBLE);
                }
                if (guess < number) {
                    arrowDown.setVisibility(View.INVISIBLE);
                    arrowUp.setVisibility(View.VISIBLE);
                }
                if (guess == number) {
                    arrowDown.setVisibility(View.INVISIBLE);
                    arrowUp.setVisibility(View.INVISIBLE);
                    trophy.setVisibility(View.VISIBLE);
                    textViewMessage.setText("Congratulations !");
                    textViewMessage.setTextColor(getResources().getColor(R.color.white));
                    editTextGuess.setFocusable(false);
                }
            }
        });
        return v;
    }

}
