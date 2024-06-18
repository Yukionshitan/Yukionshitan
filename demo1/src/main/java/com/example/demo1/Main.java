package com.example.demo1;

import com.example.demo1.view.UserRegistrationUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        UserRegistrationUI userRegistrationUI = new UserRegistrationUI();
        userRegistrationUI.start(new Stage());
    }
}
