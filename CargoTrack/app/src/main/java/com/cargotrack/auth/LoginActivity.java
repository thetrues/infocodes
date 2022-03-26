package com.cargotrack.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cargotrack.CargoBaseActivity;
import com.cargotrack.MainActivity;
import com.cargotrack.data.AppConstants;
import com.cargotrack.data.UserPreferences;
import com.cargotrack.databinding.ActivityLoginBinding;
import com.cargotrack.driver.DriverActivity;
import com.cargotrack.models.ApiCallModel;
import com.cargotrack.models.UserModel;
import com.cargotrack.utils.ApiCalls;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Request;

public class LoginActivity extends CargoBaseActivity {
    ActivityLoginBinding binding;
    private UserPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userPreferences = new UserPreferences(this);

        binding.login.setOnClickListener(view -> login());
        binding.newUser.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));
    }

    public void login() {
        String username = binding.username.getText().toString().trim();
        String password = binding.password.getText().toString().trim();
        boolean valid = true;
        if (username.isEmpty()){
            binding.username.setError("Please add username");
            binding.username.setFocusable(true);
            valid = false;
        }

        if (password.isEmpty()){
            binding.password.setError("Please add password");
            binding.password.setFocusable(true);
            valid = false;
        }

        if (valid){
            FormBody body = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build();
            Request request = new Request.Builder()
                    .post(body)
                    .url(AppConstants.MAIN_API+AppConstants.LOGIN_ACCOUNT)
                    .addHeader("Accept", "application/json")
                    .build();

            try {
                ApiCallModel model = ApiCalls.apiCallModel(request);
                if(model.getCode() == 200){
                    userPreferences.SaveUserFromObject(model.getData());
                    if (userPreferences.isLogin()){
                        UserModel userModel = userPreferences.getUser();
                        if (userModel.getRole().equals("1")){
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            startActivity(new Intent(getApplicationContext(), DriverActivity.class));
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(), model.getData(), Toast.LENGTH_LONG).show();
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

}