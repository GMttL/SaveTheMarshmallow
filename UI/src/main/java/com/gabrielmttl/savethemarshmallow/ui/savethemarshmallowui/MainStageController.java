package com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;

public class MainStageController {

    // HANDLES

    private final SaveTheMarshmallowModel model = SaveTheMarshmallowModel.getInstance();

    @FXML
    private ToggleSwitch toggleSwitch;

    @FXML
    private SearchableComboBox<String> searchBox;

    @FXML
    private ListView<String> listView;


    // METHODS

    @FXML
    private void initialize(){

        //TODO: Create error/failure stage to show when an operation fails using the return values from the MODEL.

        refreshListView();

        // ToggleSwitch Listener
        toggleSwitch.selectedProperty().addListener(((observable, oldVal, newVal) -> {
            if (newVal) { // ON
                model.turnBlockON();
            }
            else { // OFF
                model.turnBlockOFF();
            }
        }));

        // Search box listener
        searchBox.getSelectionModel().selectedItemProperty().addListener((options, oldVal, newVal) -> {
            listView.getSelectionModel().select(newVal);
        });

        // List View Listener
        listView.getSelectionModel().selectedItemProperty().addListener((options, oldVal, newVal) -> {
            searchBox.getSelectionModel().select(newVal);
        });
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
        stage.showAndWait();

        refreshListView();
    }


    @FXML
    void removeWebsiteButton(ActionEvent event) {
        model.removeWebsite(listView.getFocusModel().getFocusedItem().toString());
        refreshListView();
    }


    @FXML
    void removeAllWebsitesButton(ActionEvent event) {
        model.removeAllWebsites();
        refreshListView();
    }


    private void refreshListView() {
        ObservableList<String> items = FXCollections.observableArrayList(model.getUrls());
        listView.setItems(items);
        searchBox.setItems(items);
    }

}