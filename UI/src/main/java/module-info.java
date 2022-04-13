module com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui to javafx.fxml;
    exports com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui;
}