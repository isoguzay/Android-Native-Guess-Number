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

import java.util.Random;

public class GameActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private TextView mTextMessage;
    Fragment fragment = null;
    private String range;
    public int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        loadFragment(new HomeFragment());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.textView_message);
        navView.setOnNavigationItemSelectedListener(this);

        Random rand = new Random();
        number = rand.nextInt(10);
        Log.e("Game Activity Number : ", String.valueOf(number));

        if (savedInstanceState == null) {
            range = getIntent().getStringExtra("Range");
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                Bundle bundle = new Bundle();
                bundle.putString("Number", String.valueOf(number));
                fragment = new HomeFragment();
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
