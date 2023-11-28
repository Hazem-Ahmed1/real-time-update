package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    Button startBtn = new Button("Start");
    Button cancelBtn = new Button("Cancel");
    Button exitBtn = new Button("Exit");
    EvenTask task = new EvenTask(1,20,1000);

    @Override
    public void start(Stage stage) throws IOException {
        startBtn.setOnAction(e->{
            Thread thread1 = new Thread(task);
            thread1.setDaemon(true);
            thread1.start();
        });
        cancelBtn.setOnAction(e->{
            task.cancel();
        });
        exitBtn.setOnAction(e->{
            stage.close();
        });
        GridPane pane  = new WorkerUi(task);
        HBox box = new HBox(5,startBtn,cancelBtn,exitBtn);
        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(box);
        Scene scene = new Scene(root,500,450);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}