module com.dht.bmiappdemov2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dht.bmiappdemov2 to javafx.fxml;
    exports com.dht.bmiappdemov2;
}
