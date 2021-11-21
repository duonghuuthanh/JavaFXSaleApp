module com.dht.bmiappdemo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dht.bmiappdemo to javafx.fxml;
    exports com.dht.bmiappdemo;
}
