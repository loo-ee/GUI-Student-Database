package com.louie.guistudentdatabase.DataBase;

public class User {
    private int userID;
    private String userName;
    private String password;
    private String subject = "Not set";

    public User(String userName, String password, String subject) {
        this.userName = userName;
        this.password = password;
        this.subject = subject;
    }

    public User() {}

    public void setUserID(int ID) {
        this.userID = ID;
    }

    public int getUserID() {
        return this.userID;
    }

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

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public String getUserInfo() {
        return toString() + "Subject: " + getSubject() + "\n";
    }

    @Override
    public String toString() {
        return "Username: " + userName + "\nPassword: " + password + "\n";
    }
}
