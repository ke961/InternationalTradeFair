module keya.internationaltradefairltd {
    requires javafx.controls;
    requires javafx.fxml;


    opens keya.internationaltradefairltd to javafx.fxml;
    opens keya.internationaltradefairltd.EventManager to javafx.fxml;
    opens keya.internationaltradefairltd.CustomerSupportAgent to javafx.fxml;





    exports keya.internationaltradefairltd;

}