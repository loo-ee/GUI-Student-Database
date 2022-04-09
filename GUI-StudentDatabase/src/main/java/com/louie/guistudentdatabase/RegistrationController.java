package com.louie.guistudentdatabase;

import com.louie.guistudentdatabase.BackEnd.Login.ExceptionHandling;
import com.louie.guistudentdatabase.BackEnd.Login.LoginDataBase;
import com.louie.guistudentdatabase.BackEnd.Login.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    private String loginCss = Objects.requireNonNull(this.getClass().getResource("CSS/login.css")).toExternalForm();

    public void register(ActionEvent event) throws ExceptionHandling {

        try {

            if (userNameField.getText().equals("") || passwordField.getText().equals("") || confirmField.getText().equals("")) {
                throw new ExceptionHandling("Please fill all fields");
            }

            if (passwordField.getText().equals(confirmField.getText())) {
                User user = new User();
                user.setUserName(userNameField.getText());
                user.setPassword(passwordField.getText());
                LoginDataBase.addUser(user);
                LoginDataBase.displayList();
                registrationLabel.setText("Registration completed!");
                userNameField.clear();
                passwordField.clear();
                confirmField.clear();
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

    public void returnLogin(ActionEvent event) throws IOException {
        FXMLLoader login = new FXMLLoader(getClass().getResource("Scenes/login.fxml"));
        Parent root = login.load();
        Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root);
        scene1.getStylesheets().add(loginCss);
        loginStage.setScene(scene1);
        loginStage.show();
    }
}
