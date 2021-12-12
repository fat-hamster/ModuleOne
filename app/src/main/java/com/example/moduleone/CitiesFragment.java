package com.example.moduleone;

import static com.example.moduleone.R.*;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CitiesFragment extends Fragment {

    private static final String CURRENT_CITY = "CurrentCity";
    private City city = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState != null) {
            city = (City) savedInstanceState.getSerializable(CURRENT_CITY);
        }

        initList(view);

        if(isLandscape()) {
            showLandCoatOfArms(city);
        }
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(array.cities);

        for (int i = 0, citiesLength = cities.length; i < citiesLength; i++) {
            String currentCity = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(currentCity);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int position = i;
            tv.setOnClickListener(v -> {
                city = new City(position, currentCity);
                showCoatOfArms(city);
            });
        }
    }

    private void showCoatOfArms(City city) {
        if(isLandscape()) {
            showLandCoatOfArms(city);
        } else {
            showPortCoatOfArms(city);
        }
    }

    private void showLandCoatOfArms(City city) {
        CoatOfArmsFragment detail = CoatOfArmsFragment.newInstance(city);
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(id.coat_of_arms_container, detail)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private void showPortCoatOfArms(City city) {
        Activity activity = requireActivity();
        final Intent intent = new Intent(activity, CoatOfArmsActivity.class);
        intent.putExtra(CoatOfArmsFragment.ARG_INDEX, city);
        activity.startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(CURRENT_CITY, city);
        super.onSaveInstanceState(outState);
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}