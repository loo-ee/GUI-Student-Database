package com.louie.guistudentdatabase;

import com.louie.guistudentdatabase.DataBase.DatabaseHandling;
import com.louie.guistudentdatabase.Login.LoginDataBase;
import com.louie.guistudentdatabase.Login.UserControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML private ListView<String> studentListView;
    @FXML private ListView<String> gradesListView;
    @FXML private TextField classRecordNameTextField;
    @FXML private Label homePageLabel;
    @FXML private Label gradesLabel;
    @FXML private Label userListLabel;

    private static boolean fetchData = true;
    private static boolean isClassRecordNamed = false;
    private static Stage stage;
    private final String registrationCss = Objects.requireNonNull(this.getClass().getResource("CSS/registration.css")).toExternalForm();
    private final String loginCss = Objects.requireNonNull(this.getClass().getResource("CSS/login.css")).toExternalForm();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO -> Change source of HomePageController-init
        studentListView.getItems().addAll(LoginDataBase.getListUserNames(fetchData));
        fetchData = false;
    }

    public static void setFetchData(boolean status) {
        fetchData = status;
    }

    public static void setStage(Stage stage) {
        HomePageController.stage = stage;
    }

    public void setReturnToLoginButton(ActionEvent event) throws IOException {
        returnToLogin(true);
    }

    public void returnToLogin(boolean isButtonPressed) throws IOException {
        FXMLLoader login = new FXMLLoader(getClass().getResource("Scenes/login.fxml"));
        Parent root = login.load();
        Scene loginScene = new Scene(root);
        LoginDataBase.setLogInStatus(false);

        if (!isButtonPressed) {
            setFetchData(true);
        }

        loginScene.getStylesheets().add(loginCss);
        stage.setScene(loginScene);
        stage.show();
    }

    public void inputGrades(ActionEvent event) {
        String[] subjects = {"English", "Math", "History", "Economics"};
        int[] grades = new int[4];

        for (int i = 0; i < 4; i++) {

        }
    }

    public void addStudent(ActionEvent event) {

    }

    public void openClassRecord(ActionEvent event) {

    }
}
