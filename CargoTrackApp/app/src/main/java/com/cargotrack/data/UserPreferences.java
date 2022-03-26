package com.cargotrack.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.cargotrack.models.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

public class UserPreferences {
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    private Context mContext;

    private static final int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "cargo_user";
    private static final String USER_ID = "USER_ID";
    private static final String USERNAME = "username";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String ROLE = "role";
    private static final String TOKEN = "token";
    private static final String LOGIN = "login";



    public UserPreferences(Context context) {
        this.mContext = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void SaveUserFromObject(String object) throws JSONException {
        JSONObject ob = new JSONObject(object);
        JSONObject userOb = ob.getJSONObject("user");
        UserModel user = new UserModel();
        user.setUser_id(userOb.getInt("id"));
        user.setUsername(userOb.getString("username"));
        user.setPhone(userOb.getString("phone"));
        user.setEmail(userOb.getString("email"));
        user.setAddress(userOb.getString("address"));
        user.setRole(userOb.getString("role"));
        user.setLogin(true);
        user.setToken(ob.getString("token"));
        saveUser(user);
    }

    public void saveUser(UserModel model) {
        editor.putInt(USER_ID, model.getUser_id());
        editor.putString(USERNAME, model.getUsername());
        editor.putString(PHONE, model.getPhone());
        editor.putString(EMAIL, model.getEmail());
        editor.putString(ADDRESS, model.getAddress());
        editor.putString(ROLE, model.getRole());
        editor.putString(TOKEN, model.getToken());
        editor.putBoolean(LOGIN, model.isLogin());
        editor.commit();
    }

    public UserModel getUser() {
        return new UserModel(
                preferences.getInt(USER_ID, 0),
                preferences.getString(USERNAME,null),
                preferences.getString(PHONE,null),
                preferences.getString(EMAIL, null),
                preferences.getString(ADDRESS, null),
                preferences.getString(ROLE, null),
                preferences.getString(TOKEN, null),
                preferences.getBoolean(LOGIN,false)
        );
    }


    public Boolean isLogin() {
        return preferences.getBoolean(LOGIN, false);
    }


    public void clearSession() {
        editor.clear();
        editor.commit();
    }
}

