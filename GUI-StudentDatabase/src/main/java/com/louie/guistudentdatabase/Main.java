package com.louie.guistudentdatabase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        stage.setTitle("Login Account");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}