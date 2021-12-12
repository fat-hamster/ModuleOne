package com.example.moduleone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class CoatOfArmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coat_of_arms);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        City city = (City) getIntent().getExtras().getSerializable(CoatOfArmsFragment.ARG_INDEX);
        if(savedInstanceState == null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.coat_of_arms_fragment_container, CoatOfArmsFragment.newInstance(city))
                .commit();
        }
    }
}