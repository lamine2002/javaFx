module com.example.mohamed_lamine_niang_devoir1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mohamed_lamine_niang_devoir1 to javafx.fxml;
    opens com.example.mohamed_lamine_niang_devoir1.BD to javafx.base;
    opens com.example.mohamed_lamine_niang_devoir1.Repository to javafx.base;
    exports com.example.mohamed_lamine_niang_devoir1;
}