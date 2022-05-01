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
            PreparedStatement statement = null;
            statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                User userData = new User();
                userData.setUserID(result.getInt("user_id"));
                userData.setUserName(result.getString("userName"));
                userData.setPassword(result.getString("userPassword"));

                LoginDataBase.addUser(userData);
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

    public static void createClassRecordTable() {
        try {
            Connection connection = getDatabaseConnection();
            String tableName = UserControl.getActiveUser().getUserName();
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "+tableName+"(user_id INT, PRIMARY KEY(user_id), FOREIGN KEY(user_id) REFERENCES users(user_id))");
            statement.executeUpdate();
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
