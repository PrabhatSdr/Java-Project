module com.example.javaprojectlib {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javaprojectlib to javafx.fxml;
    exports com.example.javaprojectlib;
}