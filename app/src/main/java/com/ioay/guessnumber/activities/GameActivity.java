package com.ioay.guessnumber.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.ioay.guessnumber.R;
import com.ioay.guessnumber.fragments.HomeFragment;
import com.ioay.guessnumber.fragments.ProfileFragment;
import com.ioay.guessnumber.fragments.SettingsFragment;
import com.ioay.guessnumber.model.GuessNumber;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private TextView mTextMessage;
    private String range;
    private int temp;
    Fragment fragment = null;
    GuessNumber guessNumber = new GuessNumber();
    Bundle bundle = new Bundle();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.textView_message);
        navView.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            range = getIntent().getStringExtra("Range");
        }

        guessNumber.setNumberRange(range);

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

        Log.e("Game Activity Number  ", String.valueOf(guessNumber.getGuessNumber()));
        Log.e("Range ", guessNumber.getNumberRange());

        loadFragment(new HomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                bundle.putString("Number", String.valueOf(guessNumber.getGuessNumber()));
                fragment.setArguments(bundle);
                break;
            case R.id.navigation_settings:
                fragment = new SettingsFragment();
                break;
            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }
}
