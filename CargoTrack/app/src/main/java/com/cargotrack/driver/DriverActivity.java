package com.cargotrack.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cargotrack.databinding.ActivityDriverBinding;

public class DriverActivity extends AppCompatActivity {

    ActivityDriverBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDriverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}