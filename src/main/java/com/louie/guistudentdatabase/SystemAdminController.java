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

    public void setListView() {
        dataListView.getItems().clear();
        dataListViewInfo.getItems().clear();

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

        if (!dataListView.getSelectionModel().isEmpty()) {
            if (selectedModel.equals("Users")) {
                dataListViewInfo.getItems().add(LoginDataBase.getUserList().returnNode(dataListView.getSelectionModel().getSelectedIndex()).getUserInfo());

                if (dataListView.getSelectionModel().isSelected(dataListView.getSelectionModel().getSelectedIndex())) {
                    clearListButton.setText("Delete user");
                }
            }
            else {
                if (dataListView.getSelectionModel().isSelected(dataListView.getSelectionModel().getSelectedIndex())) {
                    clearListButton.setText("Clear contents");
                }

                UserControl.init(LoginDataBase.getUserList().returnNode(dataListView.getSelectionModel().getSelectedIndex()));
                dataListViewInfo.getItems().addAll(UserControl.getStudentCredentials());
            }
        }
    }

    public void clearList(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Clear Record");

        if (selectedModel.equals("Users")) {
            if (dataListView.getSelectionModel().isSelected(dataListView.getSelectionModel().getSelectedIndex())) {
                alert.setHeaderText("You are about to clear an existing record!");
                alert.setContentText("Press OK to clear user");

                if (alert.showAndWait().get() == ButtonType.OK) {
                    String adminDetection = LoginDataBase.getUserList().returnNode(dataListView.getSelectionModel().getSelectedIndex()).getUserName();
                    UserControl.setActiveUser(LoginDataBase.getUserList().returnNode(dataListView.getSelectionModel().getSelectedIndex()));
                    UserControl.setFileLocation();
                    UserControl.deleteFile();
                    LoginDataBase.deleteUser(LoginDataBase.getUserList().returnNode(dataListView.getSelectionModel().getSelectedIndex()));

                    if (adminDetection.equals("admin")) {
                        returnToLogin(false);
                    }
                    else {
                        reloadHomePage();
                    }
                    System.out.println("[INFO] Account was deleted");
                }
            }
            else {
                alert.setHeaderText("You are about to clear all records including admin!");
                alert.setContentText("Press OK to clear users");

                if (alert.showAndWait().get() == ButtonType.OK) {
                    clearClassRecords(false);

                    LoginDataBase.clearUserAccounts();
                    System.out.println("[INFO] Accounts were cleared");
                    returnToLogin(false);
                }
            }
        }
        else if (selectedModel.equals("Class Records")) {
            if (dataListView.getSelectionModel().isSelected(dataListView.getSelectionModel().getSelectedIndex())) {
                alert.setHeaderText("You are about to clear an existing record!");
                alert.setContentText("Press OK to clear record");

                if (alert.showAndWait().get() == ButtonType.OK) {
                    clearClassRecords(true);
                }
            }
            else {
                alert.setHeaderText("You are about to clear all class records!");
                alert.setContentText("Press OK to clear records");

                if (alert.showAndWait().get() == ButtonType.OK) {
                    clearClassRecords(false);
                }
            }
            reloadHomePage();
        }
    }

    public void reloadHomePage() throws IOException {
        returnToLogin(false);

        Alert notification = new Alert(Alert.AlertType.INFORMATION);
        notification.setHeaderText("Deletion done!");
        notification.show();

        FXMLLoader sysAdmin = new FXMLLoader(getClass().getResource("Scenes/systemAdmin.fxml"));
        Parent root = sysAdmin.load();
        Scene sysAdminScene = new Scene(root);
        LoginDataBase.setLogInStatus(true);
        stage.setScene(sysAdminScene);
        stage.show();
    }

    public void clearClassRecords(boolean singleDeletion) {

        if (singleDeletion) {
            UserControl.setActiveUser(LoginDataBase.getUserList().returnNode(dataListView.getSelectionModel().getSelectedIndex()));
            UserControl.setFileLocation();
            UserControl.deleteFile();
            UserControl.clearClassRecord();
        }
        else {
            for (int i = 0; i < LoginDataBase.getUserList().getListSize(); i++) {
                UserControl.setActiveUser(LoginDataBase.getUserList().returnNode(i));
                UserControl.setFileLocation();
                UserControl.deleteFile();
                UserControl.clearClassRecord();
            }
        }
    }
}
