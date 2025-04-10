module com.example.pharmacyinventorysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.xerial.sqlitejdbc;
    requires java.sql;


    opens com.example.pharmacyinventorysystem to javafx.fxml;
    exports com.example.pharmacyinventorysystem;
}