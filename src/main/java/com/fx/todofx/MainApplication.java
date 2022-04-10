package com.fx.todofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String fileLocation = String.valueOf(MainApplication.class.getResource("to do list icon.jpg"));

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("todo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("To Do List");
        stage.setScene(scene);
        stage.getIcons().add(new Image(fileLocation));
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}