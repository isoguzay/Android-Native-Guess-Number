package com.ioay.guessnumber.activities;

import android.content.Intent;
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

import java.io.Serializable;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView mTextMessage;
    Fragment fragment = null;
    String value;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.textView_message);
        navView.setOnNavigationItemSelectedListener(this);

        Bundle getDataBundle = getIntent().getExtras();
        if (getDataBundle != null) {
            value = getDataBundle.getString("data");
        }

        GuessNumber gNumber = (GuessNumber) getIntent().getSerializableExtra("numberObject");

        if (value != null) {
            Log.e("Game ... ", value);
        }
            Log.e("object ", gNumber.toString());

        Bundle bundle = new Bundle();
        bundle.putInt("data",Integer.valueOf(value));
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        loadFragment(homeFragment);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                bundle.putInt("data",Integer.valueOf(value));
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
