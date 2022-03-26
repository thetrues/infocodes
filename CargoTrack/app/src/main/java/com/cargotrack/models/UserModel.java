package com.cargotrack.models;

public class UserModel {
    int user_id;
    String username, phone, email, address, role, token;
    boolean login;

    public UserModel() {
    }

    public UserModel(int user_id, String username, String phone, String email, String address, String role, String token, boolean login) {
        this.user_id = user_id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.token = token;
        this.login = login;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                ", token='" + token + '\'' +
                ", login=" + login +
                '}';
    }
}
