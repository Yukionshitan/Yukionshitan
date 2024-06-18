package com.example.demo1.view;

import com.example.demo1.dao.OrderDao;
import com.example.demo1.model.OrderInfo;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OrderAdmin extends Application {

    private TableView<OrderInfo> table = new TableView<>();
    private OrderDao orderDao = new OrderDao();

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Set up the columns for the TableView
        TableColumn<OrderInfo, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<OrderInfo, String> userIdColumn = new TableColumn<>("User ID");
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userid"));

        TableColumn<OrderInfo, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<OrderInfo, String> numColumn = new TableColumn<>("Number");
        numColumn.setCellValueFactory(new PropertyValueFactory<>("num"));

        TableColumn<OrderInfo, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<OrderInfo, String> startColumn = new TableColumn<>("Start");
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));

        TableColumn<OrderInfo, String> endColumn = new TableColumn<>("End");
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        TableColumn<OrderInfo, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        table.getColumns().addAll(idColumn, userIdColumn, titleColumn, numColumn, priceColumn, startColumn, endColumn, timeColumn);

        ObservableList<OrderInfo> orderInfos = orderDao.loadOrderInfo();
        table.setItems(orderInfos);

        Button deleteButton = new Button("Delete");
        deleteButton.setPrefSize(70, 25);
        deleteButton.setOnAction(event -> {
            OrderInfo selectedOrder = table.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                int orderId = selectedOrder.getId();
                orderDao.deleteOrderInfo(orderId);
                table.getItems().remove(selectedOrder);
                table.refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText(null);
                alert.setContentText("Please select an order to delete.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setPrefSize(70, 25);
        backButton.setOnAction(e -> {
            stage.close();
            new AdminPage().start(new Stage()); // Replace with your actual back navigation logic
        });

        GridPane.setConstraints(deleteButton, 1, 6);
        GridPane.setConstraints(backButton, 0, 6);
        grid.getChildren().addAll(backButton, deleteButton, table);

        Scene scene = new Scene(grid, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Order Management");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}