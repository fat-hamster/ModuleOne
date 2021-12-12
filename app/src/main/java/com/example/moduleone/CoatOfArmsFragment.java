package com.example.moduleone;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CoatOfArmsFragment extends Fragment {

    static final String ARG_INDEX = "INDEX";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

        if(arguments != null) {
            City city = (City) arguments.getParcelable(ARG_INDEX);
            ImageView imageCoatOfArms = view.findViewById(R.id.coat_of_arms_image_view);
            @SuppressLint("Recycle") TypedArray images =
                    getResources().obtainTypedArray(R.array.coat_of_arms_img);
            imageCoatOfArms.setImageResource(images.getResourceId(city.getImageIndex(), 0));
            images.recycle();
            TextView tv = view.findViewById(R.id.coat_of_arms_text_view);
            tv.setText(city.getCityName());
        }
    }

    public static CoatOfArmsFragment newInstance(City city) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX, city);
        fragment.setArguments(args);
        return fragment;
    }
}