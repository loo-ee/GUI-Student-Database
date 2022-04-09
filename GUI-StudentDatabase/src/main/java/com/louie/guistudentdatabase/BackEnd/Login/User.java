package com.louie.guistudentdatabase.BackEnd.Login;

public class User {
    private String userName;
    private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Username: " + userName + "\nPassword: " + password + "\n";
    }
}