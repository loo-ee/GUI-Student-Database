package com.louie.guistudentdatabase;

import com.louie.guistudentdatabase.BackEnd.Login.LoginDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private Label loginLabel;
    @FXML private TextField userNameField;
    @FXML private TextField passwordField;

    private static Stage stage;
    private final String scene1Css = Objects.requireNonNull(this.getClass().getResource("CSS/scene1.css")).toExternalForm();
    private final String registrationCss = Objects.requireNonNull(this.getClass().getResource("CSS/registration.css")).toExternalForm();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static void setStage(Stage stage) {
        LoginController.stage = stage;
    }

    public void login(ActionEvent event) {

        try {
            if (validateLogin()) {
                FXMLLoader scene1Loader = new FXMLLoader(getClass().getResource("Scenes/HomePage.fxml"));
                Parent root = scene1Loader.load();
                Scene homePageScene = new Scene(root);

                homePageScene.getStylesheets().add(scene1Css);
                stage.setScene(homePageScene);
                stage.show();
            }
            else {
                loginLabel.setText("Account not found!");
                loginLabel.setStyle("-fx-text-fill: white;" + "-fx-background-color: red;");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(ActionEvent event) throws IOException {
        FXMLLoader registration = new FXMLLoader(getClass().getResource("Scenes/registration.fxml"));
        Parent root = registration.load();
        Scene scene1 = new Scene(root);

        scene1.getStylesheets().add(registrationCss);
        stage.setScene(scene1);
        stage.show();
    }

    public boolean validateLogin() {
        String combination = "Username: " + userNameField.getText() + "\nPassword: " + passwordField.getText() + "\n";

        return LoginDataBase.validateUser(combination);
    }
}