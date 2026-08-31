module keya.internationaltradefairltd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens keya.internationaltradefairltd to javafx.fxml;
    opens keya.internationaltradefairltd.EventManager to javafx.fxml;
    opens keya.internationaltradefairltd.CustomerSupportAgent to javafx.fxml;
    opens keya.internationaltradefairltd.HelperClass to javafx.base, javafx.fxml;
    opens keya.internationaltradefairltd.User to javafx.base, javafx.fxml;

    exports keya.internationaltradefairltd;
    exports keya.internationaltradefairltd.EventManager;
    exports keya.internationaltradefairltd.CustomerSupportAgent;
    exports keya.internationaltradefairltd.HelperClass;
    exports keya.internationaltradefairltd.User;
}