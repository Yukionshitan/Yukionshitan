package com.example.demo1.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Film {
    private final IntegerProperty filmID = new SimpleIntegerProperty(this, "FilmID");
    private final StringProperty title = new SimpleStringProperty(this, "Title");
    private final StringProperty description = new SimpleStringProperty(this, "Description");
    private final StringProperty genre = new SimpleStringProperty(this, "genre");
    private final StringProperty image = new SimpleStringProperty(this, "image");


    private final StringProperty director = new SimpleStringProperty(this, "director");
    private final StringProperty duration = new SimpleStringProperty(this, "duration");
    private final StringProperty price = new SimpleStringProperty(this, "price");



    public Film(int filmID, String title, String genre, String image, String description, String director, String duration,String price) {
        this.filmID.set(filmID);
        this.title.set(title);
        this.description.set(description);

        this.genre.set(genre);
        this.image.set(image);
        this.price.set(price);

        this.director.set(director);
        this.duration.set(duration);
    }

    public int getFilmID() {
        return filmID.get();
    }

    public IntegerProperty filmIDProperty() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID.set(filmID);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getImage() {
        return image.get();
    }

    public StringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    public String getDirector() {
        return director.get();
    }

    public StringProperty directorProperty() {
        return director;
    }

    public void setDirector(String director) {
        this.director.set(director);
    }

    public String getDuration() {
        return duration.get();
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

}
