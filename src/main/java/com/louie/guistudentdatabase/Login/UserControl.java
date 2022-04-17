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
        readFiles();
    }

    public static void init(User user) {
        setActiveUser(user);
        readFiles();
    }

    public static Vector<String> getStudentCredentials() {
        return studentCredentials;
    }

    public static void setFileLocation() {
        studentDatabase = new File("Database\\ClassRecords\\" + activeUser.getUserName() + "-Database.txt");
    }

    public static void setActiveUser(User user) {
        activeUser = user;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static void readFiles() {
        try {
            studentCredentials.clear();
            setFileLocation();

            if (!studentDatabase.isFile()) {
                writeFiles();
                System.out.println("[INFO] Database for user \"" + activeUser.getUserName() + "\" was created");
            }

            String line, name, email;
            int id, age;
            int count = 0;

            FileReader reader = new FileReader(studentDatabase);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while ((line = bufferedReader.readLine()) != null) {
                studentCredentials.add(line);
                count++;
            }
            reader.close();

            for (int i = 0; i < count; i += 4) {
                name = studentCredentials.get(i);
                email = studentCredentials.get(i+1);
                id = Integer.parseInt(studentCredentials.get(i+2));
                age = Integer.parseInt(studentCredentials.get(i+3));

                classRecord.appendList(new Student(name, email, id, age));
            }
            System.out.println("[INFO] Data from file was retrieved");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFiles() {

        try {
            FileWriter writer = new FileWriter(studentDatabase);
            Student student;
            studentCredentials.clear();

            if (classRecord.getListSize() != 0) {
                for (int i = 0; i < classRecord.getListSize(); i++) {
                    student = classRecord.returnNode(i);
                    writer.append(student.getName()).append("\n");
                    writer.append(student.getEmail()).append("\n");
                    writer.append(String.valueOf(student.getId())).append("\n");
                    writer.append(String.valueOf(student.getAge())).append("\n");
                }
                System.out.println("[INFO] Data was uploaded to Class Record");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
