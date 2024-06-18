package com.example.demo1;

import com.example.demo1.view.IntegratedBookingSystem;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaymentInterface extends Application {

    private Label selectedSeatLabel;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Payment Interface");

        ComboBox<String> paymentMethodDropdown = new ComboBox<>();
        paymentMethodDropdown.getItems().addAll("WeChat Pay", "Alipay Pay", "Bank Card");

        this.selectedSeatLabel = new Label();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> choosePaymentMethod(paymentMethodDropdown.getValue()));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> backToBookingPage());

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(this.selectedSeatLabel, paymentMethodDropdown, submitButton, backButton);

        Scene scene = new Scene(vBox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void choosePaymentMethod(String paymentMethod) {
        switch (paymentMethod) {
            case "WeChat Pay":
                this.makePaymentWithWeChatPay();
                break;
            case "Alipay Pay":
                this.makePaymentWithAlipayPay();
                break;
            case "Bank Card":
                this.makePaymentWithBankCard();
                break;
            default:
                System.out.println("Invalid payment method. Please choose a valid method.");
        }
    }

    private void makePaymentWithWeChatPay() {
        System.out.println("Payment has been made with WeChat Pay.");
    }

    private void makePaymentWithAlipayPay() {
        System.out.println("Payment has been made with Alipay Pay.");
    }

    private void makePaymentWithBankCard() {
        System.out.println("Payment has been made with Bank Card.");
    }

    private void backToBookingPage() {
        IntegratedBookingSystem integratedBookingSystem = new IntegratedBookingSystem();
        integratedBookingSystem.start(this.primaryStage);
    }

    public void setSelectedSeat(String seat) {
        this.selectedSeatLabel.setText("Selected seat: " + seat);
    }
}
