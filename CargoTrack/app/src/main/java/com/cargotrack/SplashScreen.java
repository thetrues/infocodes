package com.cargotrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cargotrack.auth.LoginActivity;
import com.cargotrack.data.UserPreferences;
import com.cargotrack.databinding.ActivitySplashScreenBinding;
import com.cargotrack.driver.DriverActivity;
import com.cargotrack.models.UserModel;

public class SplashScreen extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    private UserPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userPreferences = new UserPreferences(this);
        if (userPreferences.isLogin()){
            UserModel user = userPreferences.getUser();
            if (user.getRole().equals("1")){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }else{
                startActivity(new Intent(getApplicationContext(), DriverActivity.class));
            }
        }else {
            new Handler().postDelayed(() -> startActivity(new Intent(SplashScreen.this, LoginActivity.class)), 1000);
        }
        }
}