package com.example.demo1.view;

import com.example.demo1.controller.UserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Administrator
 * @description TODO
 * @date 2024/5/22 0022 15:19
 */
public class Register extends Application {

    private UserController userController = new UserController();

    @Override
    public void start(Stage primaryStage) {
        // 创建文本输入框
        TextField nameInput = new TextField();
        nameInput.setPromptText("Username"); // 通常注册时使用Username而不是Name


        // 假设还需要一个密码输入框（这里只是示例，实际应用中应使用PasswordField）
        TextField passwordInput = new TextField(); // 应使用PasswordField
        passwordInput.setPromptText("Password");

        // 注册按钮
        Button registerButton = new Button("Register User");
        registerButton.setOnAction(e -> {
            // 调用userController的注册方法（假设存在这样的方法）
            Boolean user = userController.registerUser("Customer", passwordInput.getText(), nameInput.getText());
            if (!user) {
                // 注册失败，可能需要一些UI反馈，比如显示一个消息框
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration");
                alert.setHeaderText("Registration Failed");
                alert.setContentText("User registration failed!");
                alert.showAndWait();
                return;
            }

            // 可能还需要一些UI反馈，比如显示一个消息框或清空输入框
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration");
            alert.setHeaderText("Registration Successful");
            alert.setContentText("User registered successfully!");
            alert.showAndWait();

            // 清空输入框以便进行下一次注册
            nameInput.clear();
            passwordInput.clear();
        });


        // 注册按钮
        Button back = new Button("go to login");
        back.setOnAction(e -> {
            primaryStage.close();
            UserRegistrationUI userRegistrationUI = new UserRegistrationUI();
            userRegistrationUI.start(new Stage());
        });

        // 假设这里不再需要"Back to User Registration"按钮，因为这是注册页面

        VBox vbox = new VBox(10); // 添加垂直间距
        vbox.getChildren().addAll(nameInput, passwordInput, registerButton, back);

        Scene scene = new Scene(vbox, 300, 200); // 设定场景的大小
        primaryStage.setTitle("User Registration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
