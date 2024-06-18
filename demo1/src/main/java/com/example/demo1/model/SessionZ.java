package com.example.demo1.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Administrator
 * @description TODO
 * @date 2024/5/22 0022 17:30
 */
public class SessionZ {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty filmsId = new SimpleStringProperty(this, "filmsId");
    private final StringProperty start = new SimpleStringProperty(this, "start");
    private final StringProperty end = new SimpleStringProperty(this, "end");
    private final StringProperty num = new SimpleStringProperty(this, "num");
    private final StringProperty time = new SimpleStringProperty(this, "time");

    public SessionZ() {
    }

    public SessionZ(int id, String filmsId, String start, String end, String num, String time) {
        this.id.set(id);
        this.filmsId.set(filmsId);
        this.start.set(start);
        this.end.set(end);
        this.num.set(num);
        this.time.set(time);
    }



    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFilmsId() {
        return filmsId.get();
    }

    public StringProperty filmsIdProperty() {
        return filmsId;
    }

    public void setFilmsId(String filmsId) {
        this.filmsId.set(filmsId);
    }

    public String getStart() {
        return start.get();
    }

    public StringProperty startProperty() {
        return start;
    }

    public void setStart(String start) {
        this.start.set(start);
    }

    public String getEnd() {
        return end.get();
    }

    public StringProperty endProperty() {
        return end;
    }

    public void setEnd(String end) {
        this.end.set(end);
    }

    public String getNum() {
        return num.get();
    }

    public StringProperty numProperty() {
        return num;
    }

    public void setNum(String num) {
        this.num.set(num);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }
}
