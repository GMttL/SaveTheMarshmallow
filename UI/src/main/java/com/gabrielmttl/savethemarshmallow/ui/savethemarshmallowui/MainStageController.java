package com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.controlsfx.control.ToggleSwitch;

public class MainStageController {

    @FXML
    private ToggleSwitch toggleSwitch;

    @FXML
    private void initialize(){


        // ToggleSwitch Listener
        toggleSwitch.selectedProperty().addListener(((observable, oldVal, newVal) -> {
            if (newVal) { // ON
                System.out.println("On");
            }
            else { // OFF
                System.out.println("Off");
            }
        }));
    }

    @FXML
    void addWebsiteButton(ActionEvent event) {
        // TODO: add button
    }


    @FXML
    void removeWebsiteButton(ActionEvent event) {
        // TODO: remove website button
    }


    @FXML
    void removeAllWebsitesButton(ActionEvent event) {
        // TODO: remove all button
    }




}