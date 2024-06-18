package com.example.demo1.view;

import com.example.demo1.dao.OrderDao;
import com.example.demo1.model.OrderInfo;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Administrator
 * @description TODO
 * @date 2024/5/23 0023 11:04
 */
public class MyOrder extends Application {

    private TableView<OrderInfo> table = new TableView<>();

    OrderDao orderDao = new OrderDao();

    private String userName;


    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        ObservableList<OrderInfo> orderInfos = orderDao.loadOrderInfo(userName);
        // 创建并设置列
        TableColumn<OrderInfo, Integer> OrderInfoIDCol = new TableColumn<>(" ID");
        OrderInfoIDCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        table.getColumns().add(OrderInfoIDCol);

        TableColumn<OrderInfo, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        table.getColumns().add(titleCol);

        TableColumn<OrderInfo, String> userid = new TableColumn<>("userid");
        userid.setCellValueFactory(cellData -> cellData.getValue().useridProperty());
        table.getColumns().add(userid);

        TableColumn<OrderInfo, Integer> priceCol = new TableColumn<>("price");
        titleCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject().asString());
        table.getColumns().add(priceCol);

        TableColumn<OrderInfo, String> time = new TableColumn<>("time");
        time.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        table.getColumns().add(time);

        TableColumn<OrderInfo, String> start = new TableColumn<>("start");
        start.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        table.getColumns().add(start);

        TableColumn<OrderInfo, String> end = new TableColumn<>("end");
        end.setCellValueFactory(cellData -> cellData.getValue().endProperty());
        table.getColumns().add(end);

        TableColumn<OrderInfo, String> num = new TableColumn<>("num");
        num.setCellValueFactory(cellData -> cellData.getValue().numProperty());
        table.getColumns().add(num);

        table.setItems(orderInfos);
        table.setEditable(true);
        stage.setTitle("订单管理");
        Button button = new Button("back");
        button.setPrefSize(100, 50);
        button.setOnAction(e -> {
            stage.close();
            CinemaBookingSystemGUI cinemaBookingSystemGUI = new CinemaBookingSystemGUI();
            cinemaBookingSystemGUI.setUser(userName);
            cinemaBookingSystemGUI.start(new Stage());
        });


        Button delete = new Button("delete");
        delete.setPrefSize(100, 50);
        delete.setOnAction(e -> {
            int index = table.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                orderDao.deleteOrderInfo(table.getItems().get(index).getId());
                table.getItems().remove(index);
            }
        });
        GridPane.setConstraints(delete, 0, 4, 1, 1);

        GridPane.setConstraints(button, 0, 5, 2, 1);

        grid.getChildren().addAll(button, delete, table);
        Scene scene = new Scene(grid, 600, 400);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
