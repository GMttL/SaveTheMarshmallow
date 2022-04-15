package com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AddWebsiteStageController {

    @FXML
    private TextField websiteTextField;
    @FXML
    private Label addLabel;

    private final SaveTheMarshmallowModel model = SaveTheMarshmallowModel.getInstance();

    @FXML
    private void initialize(){
        addLabel.setText("");
    }

    @FXML
    public void blockWebsiteViewButton(KeyEvent keyEvent) {
        addLabel.setText("");
        if(keyEvent.getCode() == KeyCode.ENTER) {
            String url = websiteTextField.getText();

            if (url.startsWith("http://") || url.startsWith("https://")) {
                if (model.addWebsite(url)) {
                    addLabel.setText("Added");
                    websiteTextField.clear();
                }
                else {
                    addLabel.setText(websiteTextField.getText() + " could not be added.");
                }
            }
            else {
                addLabel.setText("URL must begin with http:// or https://");
            }
            websiteTextField.setText("https://");
        }

    }
}