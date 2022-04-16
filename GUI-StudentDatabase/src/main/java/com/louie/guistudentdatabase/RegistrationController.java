package com.louie.guistudentdatabase;

import com.louie.guistudentdatabase.Login.ExceptionHandling;
import com.louie.guistudentdatabase.Login.LoginDataBase;
import com.louie.guistudentdatabase.Login.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class RegistrationController {
    @FXML private Label registrationLabel;
    @FXML private TextField userNameField;
    @FXML private TextField passwordField;
    @FXML private TextField confirmField;

    private static Stage stage;
    private final String homePageCss = Objects.requireNonNull(this.getClass().getResource("CSS/homePage.css")).toExternalForm();
    private final String loginCss = Objects.requireNonNull(this.getClass().getResource("CSS/login.css")).toExternalForm();

    public static void setStage(Stage stage) {
        RegistrationController.stage = stage;
    }

    public void register(ActionEvent event) {

        try {
            if (userNameField.getText().equals("") || passwordField.getText().equals("") || confirmField.getText().equals("")) {
                throw new ExceptionHandling("Please fill all fields!");
            }

            if (validateLogin()) {
                userNameField.clear();
                passwordField.clear();
                confirmField.clear();

                throw new ExceptionHandling("Account already exists!");
            }

            if (passwordField.getText().equals(confirmField.getText())) {
                User user = new User();

                user.setUserName(userNameField.getText());
                user.setPassword(passwordField.getText());
                LoginDataBase.addUser(user);
                System.out.println("[STATUS] Database was updated");
                LoginDataBase.displayList("User");
                registrationLabel.setText("Registration completed!");

                userNameField.clear();
                passwordField.clear();
                confirmField.clear();

                LoginDataBase.refreshUserNamesVector();
                LoginDataBase.writeFiles();
                HomePageController.setFetchData(true);
            }
            else {
                registrationLabel.setText("Passwords don't match!");
                registrationLabel.setStyle("-fx-text-fill: white;" + "-fx-background-color: red;");
            }
        } catch (ExceptionHandling e) {
            registrationLabel.setText(e.getMessage());
            registrationLabel.setStyle("-fx-text-fill: white;" + "-fx-background-color: red;");
        }
    }

    public void returnToPreviousScene(ActionEvent event) throws IOException {
        FXMLLoader login;

        if (LoginDataBase.getLogInStatus()) {
            login = new FXMLLoader(getClass().getResource("Scenes/homePage.fxml"));
        }
        else {
            login = new FXMLLoader(getClass().getResource("Scenes/login.fxml"));
        }

        Parent root = login.load();
        Scene loginScene = new Scene(root);

        if (LoginDataBase.getLogInStatus()) {
            loginScene.getStylesheets().add(homePageCss);
        }
        else {
            loginScene.getStylesheets().add(loginCss);
        }

        stage.setScene(loginScene);
        stage.show();
    }

    public boolean validateLogin() {
        String combination = "Username: " + userNameField.getText() + "\nPassword: " + passwordField.getText() + "\n";

        return LoginDataBase.validateUser(combination);
    }
}
