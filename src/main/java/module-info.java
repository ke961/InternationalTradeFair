module keya.internationaltradefairltd {
    requires javafx.controls;
    requires javafx.fxml;


    opens keya.internationaltradefairltd to javafx.fxml;
    opens keya.internationaltradefairltd.EventManager to javafx.fxml;
    opens keya.internationaltradefairltd.CustomerSupportAgent to javafx.fxml;
    opens keya.internationaltradefairltd.HelperClass to javafx.base;






    exports keya.internationaltradefairltd;

}