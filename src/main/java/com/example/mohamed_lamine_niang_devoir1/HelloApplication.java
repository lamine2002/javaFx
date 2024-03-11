package com.example.mohamed_lamine_niang_devoir1;

import com.example.mohamed_lamine_niang_devoir1.BD.BD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        BD bd = new BD();
        bd.getConnection();
    }

    public static void main(String[] args) {
        launch();
    }
}