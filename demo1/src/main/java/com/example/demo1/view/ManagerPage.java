package com.example.demo1.view;

import com.example.demo1.controller.CinemaController;
import com.example.demo1.model.Cinema;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

public class ManagerPage extends Application {
    private TableView<Cinema> table = new TableView<>();
    private ObservableList<Cinema> data = FXCollections.observableArrayList();
    private CinemaController cinemaController = new CinemaController();

    @Override
    public void start(Stage primaryStage) {
        setupTableView();
        loadDataFromDatabase();

        TextField nameInput = new TextField();
        nameInput.setPromptText("Cinema Name");

        Button addButton = new Button("Add Cinema");
        addButton.setOnAction(e -> {
            cinemaController.addCinema(nameInput.getText());
            data.add(new Cinema(nameInput.getText()));  // 假定addCinema方法已经实现，并且会自动生成cinemaID
            nameInput.clear(); // 清空输入框
        });

        Button deleteButton = new Button("Delete Cinema");
        deleteButton.setOnAction(e -> {
            Cinema selectedCinema = table.getSelectionModel().getSelectedItem();
            if (selectedCinema != null) {
                cinemaController.deleteCinema(selectedCinema.getCinemaID());
                data.remove(selectedCinema);
            }
        });

        Button backButton = new Button("Back to User Registration");
        backButton.setOnAction(e -> {
            // 假定UserRegistrationUI是另一个已经实现的界面
            UserRegistrationUI userRegistrationUI = new UserRegistrationUI();
            userRegistrationUI.start(new Stage());
            primaryStage.close();
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table, nameInput, addButton, deleteButton, backButton);

        Scene scene = new Scene(vbox);
        primaryStage.setTitle("Manager Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupTableView() {
        TableColumn<Cinema, String> nameCol = new TableColumn<>("Cinema Name");
        nameCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCinemaName()));
        table.getColumns().add(nameCol);
        table.setItems(data);
    }

    private void loadDataFromDatabase() {
        // 假定loadCinemas方法已经实现，并且会返回Cinema列表
        data.setAll(cinemaController.loadCinemas());
    }

    public static void main(String[] args) {
        launch(args);
    }
}