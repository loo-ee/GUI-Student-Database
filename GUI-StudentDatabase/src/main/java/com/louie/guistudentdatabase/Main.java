package com.louie.guistudentdatabase;

import com.louie.guistudentdatabase.BackEnd.Login.LoginDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scenes/login.fxml")));
        Scene loginScene = new Scene(root);

        String loginCss = Objects.requireNonNull(this.getClass().getResource("CSS/login.css")).toExternalForm();
        loginScene.getStylesheets().add(loginCss);
        LoginDataBase.readFiles();

        stage.setTitle("Login Account");
        stage.setScene(loginScene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            logout(stage);
        });

        LoginController.setStage(stage);
        RegistrationController.setStage(stage);
        HomePageController.setStage(stage);
    }

    public void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout application");
        alert.setHeaderText("You are about to logout the application!");
        alert.setContentText("Press OK to logout");

        if (alert.showAndWait().get() == ButtonType.OK) {
            LoginDataBase.writeFiles();
            System.out.println("You have exited the program");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}