package com.louie.guistudentdatabase.BackEnd.Login;

public class LoginDataBase {
    private static LinkedList<User> userList = new LinkedList<>();

    public static void addUser(User user) {
        userList.insertList(user);
    }

    public static boolean validateUser(String combination) {
        return userList.validateNode(combination);
    }

    public static void displayList() {
        userList.displayListContents();
    }
}
