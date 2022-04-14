package com.gabrielmttl.savethemarshmallow.ui.savethemarshmallowui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SaveTheMarshmallowApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        presentMainStage(stage);
    }

    private void presentMainStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SaveTheMarshmallowApplication.class.getResource("MainStage-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 420);

        // Set scene size as immutable
        stage.setMaxHeight(420.0);
        stage.setMaxWidth(640.0);
        stage.setResizable(false);

        //Customise stage
        stage.setTitle("Save The Marshmallow");
        // TODO: add application icon

        // Set and Show Scene
        stage.setScene(scene);
        stage.show();
    }
}