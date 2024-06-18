package com.example.demo1.view;

import com.example.demo1.controller.FilmController;
import com.example.demo1.dao.SessionDao;
import com.example.demo1.model.Film;
import com.example.demo1.model.SessionZ;
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

public class Films extends Application {
    private TableView<Film> table = new TableView<>();
    private TableView<SessionZ> Session = new TableView<>();
    private SessionDao sessionDao = new SessionDao();
    private ObservableList<Film> data = FXCollections.observableArrayList();
    private ObservableList<SessionZ> data2 = FXCollections.observableArrayList();
    private FilmController filmController = new FilmController();

    @Override
    public void start(Stage primaryStage) {
        loadDataFromDatabase();

        TextField titleInput = new TextField();
        titleInput.setPromptText("Title");
        TextField genreInput = new TextField();
        genreInput.setPromptText("Genre");
        TextField descInput = new TextField();
        descInput.setPromptText("Description");
        TextField image = new TextField();
        image.setPromptText("Image");
        image.setText("/images/");
        TextField duration = new TextField();
        duration.setPromptText("Duration");
        TextField director = new TextField();
        director.setPromptText("Director");
        TextField actor = new TextField();
        actor.setPromptText("Actor");
        TextField price = new TextField();
        price.setPromptText("Price");

        Button back = new Button("Back");
        back.setOnAction(e -> {
            AdminPage adminDashboardUI = new AdminPage();
            adminDashboardUI.start(new Stage());
            primaryStage.close();
        });

        // 创建并设置电影列
        TableColumn<Film, Integer> filmIDCol = new TableColumn<>("Film ID");
        filmIDCol.setCellValueFactory(cellData -> cellData.getValue().filmIDProperty().asObject());
        table.getColumns().add(filmIDCol);

        TableColumn<Film, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        table.getColumns().add(titleCol);

        TableColumn<Film, String> genreCol = new TableColumn<>("Genre");
        genreCol.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        table.getColumns().add(genreCol);

        TableColumn<Film, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        table.getColumns().add(descriptionCol);

        TableColumn<Film, String> imageCol = new TableColumn<>("Image");
        imageCol.setCellValueFactory(cellData -> cellData.getValue().imageProperty());
        table.getColumns().add(imageCol);

        TableColumn<Film, String> directorCol = new TableColumn<>("Director");
        directorCol.setCellValueFactory(cellData -> cellData.getValue().directorProperty());
        table.getColumns().add(directorCol);

        TableColumn<Film, String> durationCol = new TableColumn<>("Duration");
        durationCol.setCellValueFactory(cellData -> cellData.getValue().durationProperty());
        table.getColumns().add(durationCol);

        TableColumn<Film, String> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        table.getColumns().add(priceCol);

        Button addButton = new Button("Add Film");
        addButton.setOnAction(e -> {
            filmController.addFilm(titleInput.getText(), genreInput.getText(), descInput.getText(), image.getText(), duration.getText(), director.getText(), price.getText());
            loadDataFromDatabase();
            table.setItems(data);
        });

        // 创建并设置场次列
        TableColumn<SessionZ, Integer> id = new TableColumn<>("Session ID");
        id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        Session.getColumns().add(id);

        TableColumn<SessionZ, String> film = new TableColumn<>("Film Id");
        film.setCellValueFactory(cellData -> cellData.getValue().filmsIdProperty());
        Session.getColumns().add(film);

        TableColumn<SessionZ, String> time = new TableColumn<>("Time");
        time.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        Session.getColumns().add(time);

        TableColumn<SessionZ, String> startCol = new TableColumn<>("Start");
        startCol.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        Session.getColumns().add(startCol);

        TableColumn<SessionZ, String> end = new TableColumn<>("End");
        end.setCellValueFactory(cellData -> cellData.getValue().endProperty());
        Session.getColumns().add(end);

        TextField filmId = new TextField();
        filmId.setPromptText("Film ID");
        TextField timeInput = new TextField();
        timeInput.setPromptText("Time");
        TextField start = new TextField();
        start.setPromptText("Start");
        TextField endF = new TextField();
        endF.setPromptText("End");

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                titleInput.setText(newValue.getTitle());
                genreInput.setText(newValue.getGenre());
                descInput.setText(newValue.getDescription());
                image.setText(newValue.getImage());
                filmId.setText(newValue.getFilmID() + "");
                duration.setText(newValue.getDuration());
                director.setText(newValue.getDirector());
                price.setText(newValue.getPrice());
            }
        });

        // 添加删除按钮
        Button deleteButton = new Button("Delete Film");
        deleteButton.setOnAction(e -> {
            Film selectedFilm = table.getSelectionModel().getSelectedItem();
            if (selectedFilm != null) {
                filmController.deleteFilm(selectedFilm.getFilmID());
                loadDataFromDatabase();
            }
        });

        Button addSession = new Button("Add Session");
        addSession.setOnAction(e -> {
            sessionDao.saveSession(Integer.parseInt(filmId.getText()), start.getText(), endF.getText(), timeInput.getText());
            loadDataFromDatabase();
        });

        // 创建删除场次按钮
        Button deleteSessionButton = new Button("Delete Session");
        deleteSessionButton.setOnAction(e -> {
            SessionZ selectedSession = Session.getSelectionModel().getSelectedItem();
            if (selectedSession != null) {
                // 假设SessionDao有一个deleteSession方法用于删除场次
                sessionDao.deleteSession(selectedSession.getId());
                loadDataFromDatabase(); // 重新加载数据以更新界面显示
            }
        });

        Session.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filmId.setText(newValue.getFilmsId());
                timeInput.setText(newValue.getTime());
                start.setText(newValue.getStart());
                endF.setText(newValue.getEnd());
            }
        });

        VBox vbox = new VBox();
vbox.getChildren().addAll(table, titleInput, genreInput, image, director, duration, descInput, price, addButton, deleteButton, back, Session, filmId, timeInput, start, endF, addSession, deleteSessionButton);

        Scene scene = new Scene(vbox, 800, 800);

        primaryStage.setTitle("Admin Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadDataFromDatabase() {
        data.setAll(filmController.loadFilms());
        table.setItems(data);
        data2.setAll(sessionDao.loadSession());
        Session.setItems(data2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}