module com.louie.guistudentdatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.louie.guistudentdatabase to javafx.fxml;
    exports com.louie.guistudentdatabase;
}