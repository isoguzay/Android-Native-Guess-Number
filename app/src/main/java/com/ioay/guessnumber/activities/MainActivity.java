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

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = findViewById(R.id.button_start);

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
                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        intent.putExtra("Range", range[i]);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.show();
            }
        });
    }
}
