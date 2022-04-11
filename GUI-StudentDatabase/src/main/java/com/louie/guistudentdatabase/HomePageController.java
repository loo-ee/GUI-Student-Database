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
    @FXML private ImageView scene1ImageView;
    @FXML private Button returnToLoginButton;
    @FXML private ListView<String> homePageListView;

    private static boolean fetchData = true;
    private static Stage stage;
    private static Scene scene;
    private final String loginCss = Objects.requireNonNull(this.getClass().getResource("CSS/login.css")).toExternalForm();
    private final Image myImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Files/hehe.png")));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homePageListView.getItems().addAll(LoginDataBase.getListUserNames(fetchData));
        fetchData = false;
    }

    public static void setFetchData(boolean status) {
        HomePageController.fetchData = status;
    }

    public static void setStage(Stage stage) {
        HomePageController.stage = stage;
    }

    public void enableSurprise() {
        scene1ImageView.setImage(myImage);
    }

    public void setReturnToLoginButton(ActionEvent event) throws IOException {
        returnToLogin(true);
    }

    public void clearList(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Clear List");
        alert.setHeaderText("You are about to clear existing records!");
        alert.setContentText("Press OK to clear list");

        if (alert.showAndWait().get() == ButtonType.OK) {
            LoginDataBase.clearLinkedList();
            System.out.println("[STATUS] Accounts were cleared");
        }
        returnToLogin(false);
    }

    public void returnToLogin(boolean isButtonPressed) throws IOException {
        FXMLLoader login = new FXMLLoader(getClass().getResource("Scenes/login.fxml"));
        Parent root = login.load();
        Scene loginScene = new Scene(root);

        if (!isButtonPressed) {
            setFetchData(true);
        }

        loginScene.getStylesheets().add(loginCss);
        stage.setScene(loginScene);
        stage.show();
    }
}
