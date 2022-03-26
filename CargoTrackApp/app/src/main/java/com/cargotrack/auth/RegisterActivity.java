package com.cargotrack.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cargotrack.MainActivity;
import com.cargotrack.R;
import com.cargotrack.data.AppConstants;
import com.cargotrack.data.UserPreferences;
import com.cargotrack.databinding.ActivityRegisterBinding;
import com.cargotrack.driver.DriverActivity;
import com.cargotrack.models.ApiCallModel;
import com.cargotrack.models.UserModel;
import com.cargotrack.utils.ApiCalls;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Request;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    String role;
    private UserPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userPreferences = new UserPreferences(this);
        binding.register.setOnClickListener(view -> register());
        binding.existUser.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), LoginActivity.class)));
        binding.driver.setOnClickListener(view -> {
            role = "1";
            binding.user.setChecked(false);
        });
        binding.user.setOnClickListener(view -> {
            role = "2";
            binding.driver.setChecked(false);
        });
    }

    public void register(){
        String username = binding.username.getText().toString().trim();
        String phone = binding.phone.getText().toString().trim();
        String email = binding.email.getText().toString().trim();
        String address = binding.address.getText().toString().trim();
        String password = binding.password.getText().toString().trim();
        boolean valid = true;
        if (username.isEmpty()){
            binding.username.setError("Please add username");
            binding.username.setFocusable(true);
            valid = false;
        }

        if (phone.isEmpty()){
            binding.phone.setError("Please add phone number");
            binding.phone.setFocusable(true);
            valid = false;
        }

        if (email.isEmpty()){
            binding.email.setError("Please add email address");
            binding.email.setFocusable(true);
            valid = false;
        }

        if (address.isEmpty()){
            binding.address.setError("Please add address");
            binding.address.setFocusable(true);
            valid = false;
        }

        if (password.isEmpty()){
            binding.password.setError("Please add password");
            binding.password.setFocusable(true);
            valid = false;
        }

        if(valid){
            FormBody body = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("role", role)
                    .build();
            Request request = new Request.Builder()
                    .post(body)
                    .url(AppConstants.MAIN_API+AppConstants.CREATE_ACCOUNT)
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