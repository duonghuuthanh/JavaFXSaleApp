module com.dht.fxenglishapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
//    requires org.junit.jupiter.api;
    requires java.base;

    opens com.dht.fxenglishapp to javafx.fxml;
    exports com.dht.fxenglishapp;
    exports com.dht.pojo;
}
