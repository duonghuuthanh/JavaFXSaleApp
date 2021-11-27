module com.dht.fxenglishappv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.dht.fxenglishappv2 to javafx.fxml;
    exports com.dht.fxenglishappv2;
    exports com.dht.services;
    exports com.dht.pojo;
}
