package com.louie.guistudentdatabase;

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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SystemAdminController implements Initializable {
    @FXML private ListView<String> dataListView;
    @FXML private ListView<String> dataListViewInfo;

    @FXML private RadioButton users, classRecords;
    @FXML private Label listLabel;
    @FXML private Button clearListButton;

    private static boolean fetchData = true;
    private static Stage stage;
    private static String selectedModel;

    private final String loginCss = Objects.requireNonNull(this.getClass().getResource("CSS/login.css")).toExternalForm();
    private final File recordsDatabase = new File("Database\\ClassRecords");
    private String[] records = recordsDatabase.list();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public static void setFetchData(boolean status) {
        fetchData = status;
    }

    public static void setStage(Stage stage) {
        SystemAdminController.stage = stage;
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

    public void setListView(ActionEvent event) {
        dataListView.getItems().clear();

        if(users.isSelected()) {
            for (int i = 0; i < LoginDataBase.getUserList().getListSize(); i++) {
                dataListView.getItems().add(LoginDataBase.getUserList().returnNode(i).getUserName());
                listLabel.setText("List of users in database");
                selectedModel = "Users";
                clearListButton.setText("Delete all users");
            }
        }
        else if (classRecords.isSelected()) {
            dataListView.getItems().addAll(records);
            listLabel.setText("List of students in class record");
            selectedModel = "Class Records";
            clearListButton.setText("Delete class record");
        }
        fetchData = false;
    }

    public void setDataListViewItems() {
        dataListViewInfo.getItems().clear();

        if (selectedModel.equals("Users")) {
            dataListViewInfo.getItems().add(LoginDataBase.getUserList().returnNode(dataListView.getSelectionModel().getSelectedIndex()).toString());
        }
        else {
            UserControl.init(LoginDataBase.getUserList().returnNode(dataListView.getSelectionModel().getSelectedIndex()));
            dataListViewInfo.getItems().addAll(UserControl.getStudentCredentials());
        }
    }

    public void clearList(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Clear List");
        alert.setHeaderText("You are about to clear existing records!");
        alert.setContentText("Press OK to clear list");

        if (alert.showAndWait().get() == ButtonType.OK) {

            if (selectedModel.equals("Users")) {
                if (dataListView.getSelectionModel().isSelected(dataListView.getSelectionModel().getSelectedIndex())) {
                    UserControl.setActiveUser(LoginDataBase.getUserList().returnNode(dataListView.getSelectionModel().getSelectedIndex()));
                    UserControl.setFileLocation();
                    UserControl.deleteFile();
                    LoginDataBase.deleteUser(LoginDataBase.getUserList().returnNode(dataListView.getSelectionModel().getSelectedIndex()));
                }
                else {
                    for (int i = 0; i < LoginDataBase.getUserList().getListSize(); i++) {
                        UserControl.setActiveUser(LoginDataBase.getUserList().returnNode(i));
                        UserControl.setFileLocation();
                        UserControl.deleteFile();
                        UserControl.clearClassRecord();
                    }

                    LoginDataBase.clearUserAccounts();
                    System.out.println("[INFO] Accounts were cleared");
                }
            }
            else {
                UserControl.setActiveUser(LoginDataBase.getUserList().returnNode(dataListView.getSelectionModel().getSelectedIndex()));
                UserControl.setFileLocation();
                UserControl.deleteFile();
                UserControl.clearClassRecord();
            }
        }
        returnToLogin(false);
    }
}
