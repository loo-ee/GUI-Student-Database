package com.louie.guistudentdatabase;

import com.louie.guistudentdatabase.BackEnd.Login.LoginDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML private Label homePageLabel;
    @FXML private Button surpriseButton;
    @FXML private Button returnToLoginButton;
    @FXML private ImageView scene1ImageView;
    @FXML private ListView<String> homePageListView;

    private static Stage stage;
    private static Scene scene;
    private String loginCss = Objects.requireNonNull(this.getClass().getResource("CSS/login.css")).toExternalForm();
    private Image myImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Files/hehe.png")));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homePageListView.getItems().addAll(LoginDataBase.getListUserNames());
    }

    public static void setScene(Scene scene) {
        HomePageController.scene = scene;
    }

    public static void setStage(Stage stage) {
        HomePageController.stage = stage;
    }

    public void enableSurprise() {
        scene1ImageView.setImage(myImage);
    }

    public void returnToLogin(ActionEvent event) throws IOException {
        FXMLLoader login = new FXMLLoader(getClass().getResource("Scenes/login.fxml"));
        Parent root = login.load();
        Scene loginScene = new Scene(root);
        loginScene.getStylesheets().add(loginCss);
        stage.setScene(loginScene);
        stage.show();
    }
}
