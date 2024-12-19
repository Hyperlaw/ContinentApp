package com.example.continentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class CityFragment extends Fragment {

    private City city;

    public CityFragment(City city) {
        this.city = city;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.cityImageView);
        TextView nameTextView = view.findViewById(R.id.cityNameTextView);
        TextView descriptionTextView = view.findViewById(R.id.cityDescriptionTextView);

        imageView.setImageResource(city.getImageResId());
        nameTextView.setText(city.getName());
        descriptionTextView.setText(city.getDescription());
    }
}