package com.example.demo1.view;

import com.example.demo1.PaymentInterface;
import com.example.demo1.dao.OrderDao;
import com.example.demo1.dao.SessionDao;
import com.example.demo1.model.Film;
import com.example.demo1.model.Order;
import com.example.demo1.model.SessionZ;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

public class IntegratedBookingSystem extends Application {
    private boolean seatsLocked = false;
    private boolean seatsSelected = false;

    ObservableList<SessionZ> sessions;

    SessionDao sessionDao = new SessionDao();

    private Film film;


    ComboBox<String> sessionComboBox;

    OrderDao orderDao = new OrderDao();

    ObservableList<Order> orders;
    GridPane seating = new GridPane();

    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFilem(Film film) {
        this.film = film;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Horizon Cinemas - Integrated Booking System");

        GridPane bookingGrid = new GridPane();
        bookingGrid.setHgap(10);
        bookingGrid.setVgap(10);
        bookingGrid.setPadding(new Insets(10));

        Label lblDate = new Label("Select Date:");
        DatePicker datePicker = new DatePicker();
        Label lblFilm = new Label("Select Film:");

        Label lblFilmName = new Label(film.getTitle());

        datePicker.setOnAction(
                e -> {
                    if (datePicker.getValue() != null) {
                        LocalDate value = datePicker.getValue();
                        //将value转成string xxxx/xx/xx
                        String date = value.getYear() + "/" + value.getMonthValue() + "/" + value.getDayOfMonth();
                        this.sessions = sessionDao.loadSession(film.getFilmID() + "", date, null, null);
                        if (this.sessions != null) {
                            sessionComboBox.getItems().clear();
                            for (SessionZ session : this.sessions) {
                                sessionComboBox.getItems().add(session.getStart() + "-" + session.getEnd());
                            }
                        }
                    }
                }
        );

        //场次
        Label lblSession = new Label("Select Session:");
        sessionComboBox = new ComboBox<>();

        sessionComboBox.setOnAction(e -> {
            if (sessionComboBox.getValue() != null) {
                LocalDate value = datePicker.getValue();
                //将value转成string xxxx/xx/xx
                String date = value.getYear() + "/" + value.getMonthValue() + "/" + value.getDayOfMonth();
                String session = sessionComboBox.getValue();
                String[] sessionTime = session.split("-");
                String startTime = sessionTime[0];
                String endTime = sessionTime[1];
                this.orders = orderDao.loadOrder(startTime, endTime, date, film.getFilmID() + "");
                seating.getChildren().forEach(node -> {
                    if (node instanceof Button) {
                        ((Button) node).setStyle("-fx-background-color: #FFFFFF");
                        ((Button) node).setDisable(false);
                    }
                });
                for (Order order : this.orders) {
                    String num = order.getNum();
                    seating.getChildren().forEach(node -> {
                        if (node instanceof Button) {
                            Button node1 = (Button) node;
                            if (node1.getText().equals(num)) {
                                node1.setStyle("-fx-background-color: #ff0900");
                                node1.setDisable(true);
                            }
                        }
                    });
                }
            }

        });

        //选择的座位号

        bookingGrid.add(lblSession, 0, 2);
        bookingGrid.add(sessionComboBox, 1, 2);


        bookingGrid.add(lblDate, 0, 0);
        bookingGrid.add(datePicker, 1, 0);
        bookingGrid.add(lblFilm, 0, 1);
        bookingGrid.add(lblFilmName, 1, 1);

        Label lblSeat = new Label("Selected Seat:");
        TextField seatTextField = new TextField();

        bookingGrid.add(lblSeat, 2, 0);
        bookingGrid.add(seatTextField, 2, 1);

        //price总和

        Label lblPrice = new Label("Total Price:");
        TextField priceTextField = new TextField();
        priceTextField.setEditable(false);
        priceTextField.setText("0");

        bookingGrid.add(lblPrice, 2, 2);
        bookingGrid.add(priceTextField, 2, 3);


        seating.setAlignment(Pos.CENTER);
        seating.setHgap(10);
        seating.setVgap(10);
        seating.setPadding(new Insets(10));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                Button seatButton = new Button(String.format("%d-%d", i + 1, j + 1));
                seatButton.setPrefSize(50, 50);
                seatButton.setStyle("-fx-background-color: #FFFFFF");
                seatButton.setOnAction(e -> {
                    if (!seatsLocked) {
                        seatButton.setStyle(seatButton.getStyle().equals("-fx-background-color: #FFFFFF") ?
                                "-fx-background-color: #008000" : "-fx-background-color: #FFFFFF");
                        seatsSelected = true;
                        final String[] ss = {""};
                        final Integer[] priceTexts = {0};
                        seating.getChildren().forEach(node -> {
                            if (node instanceof Button) {
                                if (node.getStyle().equals("-fx-background-color: #008000")) {
                                    String text = ((Button) node).getText();
                                    ss[0] = ss[0] + text + ",";
                                    priceTexts[0] += Integer.parseInt(film.getPrice());
                                }
                            }
                        });
                        seatTextField.setText(ss[0]);
                        priceTextField.setText(priceTexts[0] + "");
                    }
                });
                seating.add(seatButton, j, i);
            }
        }

        Button confirmButton = new Button("Confirm Selection");
        Button resetButton = new Button("Back");
        Button checkButton = new Button("Check Availability and Price");
        HBox buttonBox = new HBox(10, confirmButton, resetButton, checkButton);
        buttonBox.setAlignment(Pos.CENTER);

        confirmButton.setOnAction(e -> {
            if (datePicker.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please select a date");
                alert.showAndWait();
            } else if (sessionComboBox.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please select a session");
                alert.showAndWait();
            } else if (seatTextField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please select a seat");
                alert.showAndWait();
            } else {
                String[] split = seatTextField.getText().split(",");
                LocalDate value = datePicker.getValue();
                //将value转成string xxxx/xx/xx
                String date = value.getYear() + "/" + value.getMonthValue() + "/" + value.getDayOfMonth();
                String session = sessionComboBox.getValue();
                String[] sessionTime = session.split("-");
                String startTime = sessionTime[0];
                String endTime = sessionTime[1];
                for (String s : split) {
                    Order order = new Order();
                    order.setFilmsId(film.getFilmID());
                    order.setTime(date);
                    order.setStart(startTime);
                    order.setEnd(endTime);
                    order.setNum(s);
                    order.setUserid(userName);
                    try {
                        orderDao.insertOrder(order.getUserid(), order.getFilmsId() + "", "", s, film.getPrice() + "", order.getStart(), order.getEnd(), order.getTime());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                this.orders = orderDao.loadOrder(startTime, endTime, date, film.getFilmID() + "");

                for (Order order : this.orders) {
                    String num = order.getNum();
                    seating.getChildren().forEach(node -> {
                        if (node instanceof Button) {
                            Button node1 = (Button) node;
                            if (node1.getText().equals(num)) {
                                node1.setStyle("-fx-background-color: #FF0000");
                                node1.setDisable(true);
                            }
                        }
                    });
                }
                seatTextField.setText("");
                priceTextField.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Your order has been successfully placed");
                alert.showAndWait();
            }

        });

        checkButton.setOnAction(e -> openPaymentInterface(primaryStage));

        resetButton.setOnAction(e -> {
            primaryStage.close();
            CinemaBookingSystemGUI cinemaBookingSystemGUI = new CinemaBookingSystemGUI();
            cinemaBookingSystemGUI.setUser(userName);
            cinemaBookingSystemGUI.start(new Stage());
        });

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(bookingGrid);
        mainLayout.setCenter(seating);
        mainLayout.setBottom(buttonBox);

        Scene scene = new Scene(mainLayout, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openPaymentInterface(Stage primaryStage) {
        primaryStage.close();
        PaymentInterface paymentInterface = new PaymentInterface();
        paymentInterface.start(new Stage());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
