package com.louie.guistudentdatabase.DataBase;

import com.louie.guistudentdatabase.Login.LoginDataBase;
import com.louie.guistudentdatabase.Login.UserControl;

import java.sql.*;

public class DatabaseHandling {

    public static Connection getDatabaseConnection() {
        Connection connection = null;

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/studentdatabase";
            String username = "root";
            String password = "mySQL@justopen23";

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("[INFO] Connection established");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    public static void insertUserValue(User userInfo) {
        try {
            Connection connection = getDatabaseConnection();
            String statement = "INSERT INTO users(userName, userPassword) VALUES ('"+userInfo.getUserName()+"', '"+userInfo.getPassword()+"')";
            PreparedStatement posted = connection.prepareStatement(statement);
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getUserInfo() {
        try {
            Connection connection = getDatabaseConnection();
            PreparedStatement statement1;
            statement1 = connection.prepareStatement("SELECT * FROM users");
            ResultSet result1 = statement1.executeQuery();

            while (result1.next()) {
                User userData = new User();
                userData.setUserID(result1.getInt("user_id"));
                userData.setUserName(result1.getString("userName"));
                userData.setPassword(result1.getString("userPassword"));

                LoginDataBase.addUser(userData);
            }

            PreparedStatement statement2;
            statement2 = connection.prepareStatement("SELECT * FROM students");
            ResultSet result2 = statement2.executeQuery();

            while (result2.next()) {
                Student studentData = new Student(result2.getString("name"), result2.getString("email"), result2.getInt("age"));
                studentData.setStudentInfo(result2.getInt("student_id"), result2.getInt("year_level"), result2.getString("course"));

                // TODO -> Create a Linked List for class-students
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id, boolean deleteAllUsers) {
        try {
            Connection connection = getDatabaseConnection();
            PreparedStatement statement;

            if (deleteAllUsers) {
                statement = connection.prepareStatement("DELETE FROM users");
            }
            else {
                statement = connection.prepareStatement("DELETE FROM users WHERE user_id = '"+id+"'");
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertValuesToClassRecord(int[] grades) {
        int[] test = {1,2,3,4};

        try {
            Connection connection = getDatabaseConnection();
            String statement = "INSERT INTO students(English) VALUES("+test[0]+")";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteClassRecord(User luckyUser) {
        try {
            Connection connection = getDatabaseConnection();
            String deleteStatement = "DELETE FROM "+luckyUser.getUserName();
            PreparedStatement statement = connection.prepareStatement(deleteStatement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
