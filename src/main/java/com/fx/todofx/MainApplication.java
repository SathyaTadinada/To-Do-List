package com.fx.todofx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String fileLocation = "C:\\Users\\sathy\\Downloads\\to do list icon.jpg";
        String fileLocationCSD = "C:\\Users\\stad5246\\Downloads\\to do list logo.png";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("todo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("To Do List");
        stage.setScene(scene);
        stage.getIcons().add(new Image(fileLocationCSD)); // make sure to change depending on where I am
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}