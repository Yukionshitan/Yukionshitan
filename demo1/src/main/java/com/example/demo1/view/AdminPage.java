package com.example.demo1.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminPage extends Application {


    @Override
    public void start(Stage primaryStage) {

        // 创建菜单项
        MenuItem manageMovies = new MenuItem("Manage film");
        manageMovies.setOnAction(event -> {
            Films films = new Films();
            films.start(primaryStage);
        });

        MenuItem manageUserOrders = new MenuItem("Manage user orders");
        manageUserOrders.setOnAction(event -> {
            OrderAdmin orderAdmin = new OrderAdmin();
            orderAdmin.start(primaryStage);
        });

        MenuItem exit = new MenuItem("go out");
        exit.setOnAction(event -> {
            UserRegistrationUI userRegistrationUI = new UserRegistrationUI();
            userRegistrationUI.start(primaryStage);
        });


        // 创建菜单
        Menu fileMenu = new Menu("menu");
        fileMenu.getItems().addAll(manageMovies, manageUserOrders, exit);

        // 创建菜单栏并添加菜单
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        // 创建一个VBox作为内容容器（虽然在这个例子中我们不需要它，因为我们只使用MenuBar）
        VBox vbox = new VBox(menuBar);

        // 创建场景并设置内容
        Scene scene = new Scene(vbox, 800, 600); // 你可以指定场景的宽度和高度

        // 设置舞台的标题和场景
        primaryStage.setTitle("Admin Dashboard");
        primaryStage.setScene(scene);

        // 显示舞台
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
