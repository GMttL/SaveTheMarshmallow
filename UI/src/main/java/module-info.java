module com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.gabrielmttl.websiteBlocker;


    exports com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui;
    opens com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui to javafx.fxml;
}