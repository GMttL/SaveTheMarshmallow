package com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AddWebsiteStageController {

    @FXML
    private TextField websiteTextField;

    @FXML
    public void blockWebsiteViewButton(KeyEvent keyEvent) {
        // TODO: Implement Add website view functionality
        // check textfield value is starts with https:// or http://
        if(keyEvent.getCode() == KeyCode.ENTER) {
            System.out.println(websiteTextField.getText());
        }
    }
}