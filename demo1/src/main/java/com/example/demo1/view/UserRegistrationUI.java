package com.example.demo1.view;

import com.example.demo1.controller.UserController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UserRegistrationUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private UserController userController = new UserController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("International Cinema");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label titleLabel = new Label("International Cinema");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        GridPane.setConstraints(titleLabel, 0, 0, 2, 1);

        Label accountLabel = new Label("Account:");
        TextField accountField = new TextField();
        GridPane.setConstraints(accountLabel, 0, 1);
        GridPane.setConstraints(accountField, 1, 1);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        GridPane.setConstraints(passwordLabel, 0, 2);
        GridPane.setConstraints(passwordField, 1, 2);

        ToggleGroup identityGroup = new ToggleGroup();
        RadioButton adminRadioButton = new RadioButton("Admin");
        adminRadioButton.setToggleGroup(identityGroup);
        RadioButton customerRadioButton = new RadioButton("Customer");
        customerRadioButton.setToggleGroup(identityGroup);
        RadioButton managerRadioButton = new RadioButton("Manager");
        managerRadioButton.setToggleGroup(identityGroup);
        GridPane.setConstraints(adminRadioButton, 0, 3);
        GridPane.setConstraints(customerRadioButton, 1, 3);
        GridPane.setConstraints(managerRadioButton, 0, 4);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 0, 5, 2, 1);
        loginButton.setOnAction(e -> {
            if (adminRadioButton.isSelected()) {
                openAdminPage(primaryStage, accountField.getText(), passwordField.getText());
            } else if (customerRadioButton.isSelected()) {
                openCinemaBookingSystem(primaryStage, accountField.getText(), passwordField.getText());
            } else if (managerRadioButton.isSelected()) {
                openManagerPage(primaryStage, accountField.getText(), passwordField.getText());
            }
        });

        Button registerButton = new Button("Register");
        GridPane.setConstraints(registerButton, 0, 6, 2, 1);
        registerButton.setOnAction(e -> {
             Register register = new Register(); // 确保你有一个Register类处理注册界面
             register.start(new Stage());
             primaryStage.close();
        });

        grid.getChildren().addAll(titleLabel, accountLabel, accountField, passwordLabel, passwordField, adminRadioButton, customerRadioButton, managerRadioButton, loginButton, registerButton);

        Scene scene = new Scene(grid, 300, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void openAdminPage(Stage primaryStage, String account, String password) {
        if (userController.login("Admin", password, account)) {
            primaryStage.close();
            AdminPage adminPage = new AdminPage(); // 确保你有一个 AdminPage 类处理管理员界面
            adminPage.start(new Stage());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Login Error");
            alert.setContentText("Account or password is wrong!");
            alert.showAndWait();
        }

    }


    private void openCinemaBookingSystem(Stage primaryStage, String account, String password) {
        if (userController.login("Customer", password, account)) {
            primaryStage.close();
            CinemaBookingSystemGUI cinemaBookingSystemGUI = new CinemaBookingSystemGUI();
            cinemaBookingSystemGUI.setUser(account);
            cinemaBookingSystemGUI.start(new Stage());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Login Error");
            alert.setContentText("Account or password is wrong!");
            alert.showAndWait();
        }
    }
        private void openManagerPage (Stage primaryStage, String account, String password){
            if (userController.login("Manager", password, account)) {
                primaryStage.close();
                ManagerPage managerPage = new ManagerPage(); // 确保你有一个ManagerPage类处理经理界面
                managerPage.start(new Stage());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("Login Error");
                alert.setContentText("Account or password is wrong!");
                alert.showAndWait();
            }
        }
    }