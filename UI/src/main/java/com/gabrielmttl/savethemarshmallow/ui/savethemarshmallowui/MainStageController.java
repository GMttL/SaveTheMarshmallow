package com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;

public class MainStageController {

    @FXML
    private ToggleSwitch toggleSwitch;

    @FXML
    private void initialize(){


        // ToggleSwitch Listener
        toggleSwitch.selectedProperty().addListener(((observable, oldVal, newVal) -> {
            // TODO: toggle button
            if (newVal) { // ON

                System.out.println("On");
            }
            else { // OFF
                System.out.println("Off");
            }
        }));
    }

    @FXML
    void addWebsiteButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStageController.class.getResource("AddWebsiteStage-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 150);
        Stage stage = new Stage();

        // Set scene size as immutable
        stage.setMaxHeight(150);
        stage.setMaxWidth(400);
        stage.setResizable(false);

        stage.setTitle("Save The Marshmallow");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
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