package com.louie.guistudentdatabase.BackEnd.Login;

import com.louie.guistudentdatabase.DataBase.Student;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;

public class LoginDataBase {
    private static LinkedList<User> userList = new LinkedList<>();
    private static Queue<String> userNames = new PriorityQueue<>();
    private static Vector<String> filesVector = new Vector<>();
    private static File database = new File("D:\\GitHub\\JAVA\\GUI-Student-Database\\GUI-StudentDatabase\\src\\main\\java\\com\\louie\\guistudentdatabase\\DataBase\\DataBase.txt");

    public static void init() {

    }

    public static void readFiles() {
        try {
            filesVector.clear();
            FileReader reader = new FileReader(database);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            String userName;
            String password;
            int count = 0;

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
            System.out.println("Done");
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

            for (int i = 0; i < userList.getListSize(); i++) {
                user = userList.returnNode(i);
                writer.append(user.getUserName()).append("\n");
                writer.append(user.getPassword()).append("\n");
            }
            writer.close();
            System.out.println("Done too");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Queue<String> getListUserNames() {
        User user;

        for (int i = 0; i < userList.getListSize(); i++) {
            user = userList.returnNode(i);
            userNames.add((i+1) + ": " + user.getUserName());
        }
        return userNames;
    }

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
