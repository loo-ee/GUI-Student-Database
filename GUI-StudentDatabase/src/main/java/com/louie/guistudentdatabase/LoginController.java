package com.louie.guistudentdatabase;

import com.louie.guistudentdatabase.BackEnd.Login.LoginDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private Label loginLabel;
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;
    @FXML private Button loginButton;
    @FXML private Button registerButton;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;

    private String userName;
    private String password;
    private final String scene1Css = Objects.requireNonNull(this.getClass().getResource("CSS/scene1.css")).toExternalForm();
    private final String registrationCss = Objects.requireNonNull(this.getClass().getResource("CSS/registration.css")).toExternalForm();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void login(ActionEvent event) {

        try {

            if (validateLogin()) {
                FXMLLoader scene1Loader = new FXMLLoader(getClass().getResource("Scenes/scene1.fxml"));
                Parent root = scene1Loader.load();
                Stage scene1Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene1 = new Scene(root);
                scene1.getStylesheets().add(scene1Css);
                scene1Stage.setScene(scene1);
                scene1Stage.setTitle("Hehe");
                scene1Stage.show();
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
        Stage registrationStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root);
        scene1.getStylesheets().add(registrationCss);
        registrationStage.setScene(scene1);
        registrationStage.show();

    }

    public boolean validateLogin() {
        String combination = "Username: " + usernameField.getText() + "\nPassword: " + passwordField.getText() + "\n";
        return LoginDataBase.validateUser(combination);
    }
}