module com.louie.guistudentdatabase {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.louie.guistudentdatabase to javafx.fxml;
    exports com.louie.guistudentdatabase;
}