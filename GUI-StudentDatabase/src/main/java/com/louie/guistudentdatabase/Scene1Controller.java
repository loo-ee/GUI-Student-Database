package com.louie.guistudentdatabase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Scene1Controller implements Initializable {
    @FXML private Label introLabel;
    @FXML private Button surpriseButton;
    @FXML private ImageView scene1ImageView;

    private Image myImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Files/hehe.png")));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void enableSurprise() {
        scene1ImageView.setImage(myImage);
    }
}
