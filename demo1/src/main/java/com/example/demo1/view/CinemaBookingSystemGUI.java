package com.example.demo1.view;

import com.example.demo1.controller.FilmController;
import com.example.demo1.model.Film;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class CinemaBookingSystemGUI extends Application {

    FilmController filmController = new FilmController();

    private IntegratedBookingSystem integratedBookingSystem;

    private String userName;

    public void setUser(String user) {
        this.userName = user;
    }

    private class MovieListing extends VBox {
        private Integer fId;
        private String title;
        private String imagePath;
        private String Genre;
        private String description;
        private String actorCol;
        private String director;

        private String price;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Integer getfId() {
            return fId;
        }

        public void setId(Integer id) {
            this.fId = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getGenre() {
            return Genre;
        }

        public void setGenre(String genre) {
            Genre = genre;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getActorCol() {
            return actorCol;
        }

        public void setActorCol(String actorCol) {
            this.actorCol = actorCol;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public MovieListing(Integer id, String title, String imagePath, String Genre, String description, String actorCol, String director, String price) {
            super(5);
            this.price = price;
            this.fId = id;
            this.title = title;
            this.imagePath = imagePath;
            this.Genre = Genre;
            this.description = description;
            this.actorCol = actorCol;
            this.director = director;
            this.setAlignment(Pos.TOP_LEFT);
            this.setPadding(new Insets(10));
            Label titleLabel = new Label(title);
            titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

            String property = System.getProperty("user.dir");
            String fullPath = new File(property + File.separator + imagePath).toURI().toString();
            System.out.println("Attempting to load image from path: " + fullPath);

            try {
                Image image = new Image(fullPath);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100); // 设置图片的宽度，根据需要调整
                imageView.setPreserveRatio(true); // 保持图片的原始比例

                this.getChildren().add(imageView); // 确保将 imageView 添加到布局中
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
                e.printStackTrace();
            }

            Label descriptionLabel = new Label("Film introduction：" + description);
            descriptionLabel.setWrapText(true);

            Label genresLabel = new Label("Genres：" + Genre);
            genresLabel.setWrapText(true);
            genresLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            genresLabel.setPadding(new Insets(10));

            Label actorLabel = new Label("Actor：" + actorCol);
            actorLabel.setWrapText(true);
            actorLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            actorLabel.setPadding(new Insets(10));

            Label directorLabel = new Label("Director：" + director);
            directorLabel.setWrapText(true);
            directorLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            directorLabel.setPadding(new Insets(10));

            Label priceLabel = new Label("Price：" + price);
            priceLabel.setWrapText(true);
            priceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            priceLabel.setPadding(new Insets(10));

            this.getChildren().addAll(titleLabel, descriptionLabel, genresLabel, actorLabel, directorLabel, priceLabel);
        }
    }

        @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Horizon Cinemas");

        // Main layout is a VBox
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));

        // Header
        Label cinemaLabel = new Label("Horizon Cinemas");
        cinemaLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label locationLabel = new Label("Bristol Cabot Circus");
        Label staffLabel = new Label("James Carter [Staff]");
        HBox headerBox = new HBox(20, cinemaLabel, locationLabel, staffLabel);
        headerBox.setAlignment(Pos.CENTER);

        // Movie listings
        ObservableList<MovieListing> movies = FXCollections.observableArrayList();
        ObservableList<Film> films = filmController.loadFilms();
        for (Film film : films) {
            MovieListing movieListing = new MovieListing(film.getFilmID(), film.getTitle(), film.getImage(), film.getGenre(), film.getDescription(), film.getDuration(), film.getDirector(), film.getPrice());
            movies.add(movieListing);
        }

        ListView<MovieListing> movieListView = new ListView<>(movies);
        // 设置ListView的首选宽度和高度
        movieListView.setPrefSize(600, 300);
        // 使ListView在垂直方向上随着窗口大小的变化而拉伸
        VBox.setVgrow(movieListView, Priority.ALWAYS);


        movieListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.integratedBookingSystem = new IntegratedBookingSystem();
                MovieListing newValue1 = newValue;
                Film newValue2 = new Film(newValue1.getfId(), newValue1.getTitle(), newValue1.getImagePath(), newValue1.getGenre(), newValue1.getDescription(), newValue1.getActorCol(), newValue1.getDirector(), newValue1.getPrice());
                integratedBookingSystem.setFilem(newValue2);
            }
        });

        // Buttons for navigation
        Button mainMenuButton = new Button("Book the ticket");
        mainMenuButton.setOnAction(e -> {
            try {
                primaryStage.close();
                integratedBookingSystem.setUserName(userName);
                integratedBookingSystem.start(new Stage());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        Button goout = new Button("Go out");
        goout.setOnAction(e -> {
            primaryStage.close();
            UserRegistrationUI userRegistrationUI = new UserRegistrationUI();
            userRegistrationUI.start(new Stage());
        });

        Button myOrderButton = new Button("My Order");
        myOrderButton.setOnAction(e -> {
            primaryStage.close();
            MyOrder myOrder = new MyOrder();
            myOrder.setUserName(userName);
            myOrder.start(primaryStage);
        });

        HBox buttonBox = new HBox(10, mainMenuButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Add all components to main layout
        mainLayout.getChildren().addAll(headerBox, movieListView, buttonBox, goout,myOrderButton);

        // Set the scene and show stage
        Scene scene = new Scene(mainLayout, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}