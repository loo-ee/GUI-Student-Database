package com.louie.guistudentdatabase.Login;

import com.louie.guistudentdatabase.DataBase.LinkedList;
import com.louie.guistudentdatabase.DataBase.Student;
import com.louie.guistudentdatabase.DataBase.User;

import java.io.*;
import java.util.Vector;

public class UserControl {
    private static User activeUser;
    private static LinkedList<Student> classRecord = new LinkedList<>();
    private static Vector<String> studentNames = new Vector<>();
    private static Vector<String> studentCredentials = new Vector<>();
    private static File studentDatabase;

    public static void refreshNamesVector() {
        studentNames.clear();
        System.out.println("[INFO] Student data was refreshed");
    }

    public static void init(String combination) {
        setActiveUser(LoginDataBase.getUser(combination));
    }

    public static void init(User user) {
        setActiveUser(user);
    }

    public static int getUserId() {
        return activeUser.getUserID();
    }

    public static Vector<String> getStudentCredentials() {
        return studentCredentials;
    }

    public static void setActiveUser(User user) {
        activeUser = user;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static void setClassRecordName(String name) {
        classRecord.setListName(name);
    }

    public static void addStudent(Student student) {
        classRecord.insertList(student);
    }

    public static String getClassRecordName() {
        return classRecord.getListName();
    }

    public static boolean validateStudentInfo(String combination) {
        return classRecord.validateNode(combination);
    }

    public static void displayClassRecord(String listType) {
        classRecord.displayListContents(listType);
    }

    public static void clearClassRecord() {
        classRecord.clearList();
        studentNames.clear();
        studentCredentials.clear();
    }

    public static void deleteFile() {
        studentDatabase.delete();
        System.out.println("[INFO] Database for user \"" + activeUser.getUserName() + "\" was deleted");
    }
}
