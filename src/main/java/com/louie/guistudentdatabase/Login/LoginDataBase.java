package com.louie.guistudentdatabase.Login;

import java.io.*;
import java.util.Vector;

public class LoginDataBase {
    private static LinkedList<User> userList = new LinkedList<>();
    private static Vector<String> userNames = new Vector<>();
    private static Vector<String> userCredentials = new Vector<>();
    private static File userDatabase = new File("DataBase\\Login Database.txt");

    private static boolean isLoggedIn = false;

    public static void init() {
        readFiles();
    }

    public static void setLogInStatus(boolean status) {
        isLoggedIn = status;
    }

    public static boolean getLogInStatus() {
        return isLoggedIn;
    }

    public static void refreshUserNamesVector() {
        userNames.clear();
        System.out.println("[INFO] Login data was refreshed");
    }

    public static File getUserDatabase() {
        return userDatabase;
    }

    public static void readFiles() {

        try {
            userCredentials.clear();

            if (!userDatabase.isFile()) {
                writeFiles();
                System.out.println("[INFO] Database was created");
            }

            String line;
            String userName;
            String password;
            int count = 0;

            FileReader reader = new FileReader(userDatabase);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while ((line = bufferedReader.readLine()) != null) {
                userCredentials.add(line);
                count++;
            }
            reader.close();

            for (int i = 0; i < count; i += 2) {
                userName = userCredentials.get(i);
                password = userCredentials.get(i+1);

                userList.appendList(new User(userName, password));
            }
            System.out.println("[INFO] Data from database was retrieved");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFiles() {

        try {
            FileWriter writer = new FileWriter(userDatabase);
            User user;
            userCredentials.clear();

            if (userList.getListSize() != 0) {
                for (int i = 0; i < userList.getListSize(); i++) {
                    user = userList.returnNode(i);
                    writer.append(user.getUserName()).append("\n");
                    writer.append(user.getPassword()).append("\n");
                }
                System.out.println("[INFO] Data was uploaded to database");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String combination) {
        return userList.returnNode(combination);
    }

    public static Vector<String> getListUserNames(boolean fetchUserNames) {

        if (fetchUserNames) {
            User user;

            for (int i = 0; i < userList.getListSize(); i++) {
                user = userList.returnNode(i);
                userNames.add((i+1) + ": " + user.getUserName());
            }
        }
        return userNames;
    }

    public static void addUser(User user) {
        userList.appendList(user);
    }

    public static boolean validateUser(String combination) {
        return userList.validateNode(combination);
    }

    public static void displayList(String listType) {
        userList.displayListContents(listType);
    }

    public static void clearUserAccounts() {
        userList.clearList();
        userNames.clear();
        userCredentials.clear();
    }

    public static LinkedList<User> getUserList() {
        return userList;
    }

    public static void deleteUser(User user) {
        String userName = user.getUserName();

        userList.deleteNode(user);
        System.out.println("[INFO] User \"" + userName + "\" was removed from database");
    }
}
