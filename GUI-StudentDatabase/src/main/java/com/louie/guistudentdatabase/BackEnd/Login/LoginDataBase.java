package com.louie.guistudentdatabase.BackEnd.Login;

import java.io.*;
import java.util.Vector;

public class LoginDataBase {
    private static LinkedList<User> userList = new LinkedList<>();
    private static Vector<String> userNames = new Vector<>();
    private static Vector<String> filesVector = new Vector<>();
    private static File database = new File("LoginDatabase\\Login Database.txt");

    public static void init() {
        readFiles();
    }

    public static void refreshVector() {
        userNames.clear();
        System.out.println("[INFO] Login data was refreshed");
    }

    public static void readFiles() {

        try {
            filesVector.clear();

            if (!database.isFile()) {
                writeFiles();
                System.out.println("[INFO] Database created");
            }

            String line;
            String userName;
            String password;
            int count = 0;

            FileReader reader = new FileReader(database);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while ((line = bufferedReader.readLine()) != null) {
                filesVector.add(line);
                count++;
            }
            reader.close();

            for (int i = 0; i < count; i+=2) {
                userName = filesVector.get(i);
                password = filesVector.get(i+1);

                userList.appendList(new User(userName, password));
            }
            System.out.println("[INFO] Data from file was retrieved");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFiles() {

        try {
            FileWriter writer = new FileWriter(database);
            User user;
            filesVector.clear();

            if (userList.getListSize() != 0) {
                for (int i = 0; i < userList.getListSize(); i++) {
                    user = userList.returnNode(i);
                    writer.append(user.getUserName()).append("\n");
                    writer.append(user.getPassword()).append("\n");
                }
                System.out.println("[INFO] Data was uploaded to file");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void clearLinkedList() {
        userList.clearList();
        userNames.clear();
        filesVector.clear();
    }
}
