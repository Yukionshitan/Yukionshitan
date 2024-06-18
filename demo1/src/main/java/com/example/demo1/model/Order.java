package com.example.demo1.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Administrator
 * @description TODO
 * @date 2024/5/23 0023 10:01
 */
public class Order {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty userid = new SimpleStringProperty(this, "userid");
    private final IntegerProperty filmsId = new SimpleIntegerProperty(this, "filmsId");
    private final IntegerProperty sessionid = new SimpleIntegerProperty(this, "sessionid");
    private final StringProperty num = new SimpleStringProperty(this, "num");
    private final IntegerProperty price = new SimpleIntegerProperty(this, "price");
    private final StringProperty start = new SimpleStringProperty(this, "start");
    private final StringProperty end = new SimpleStringProperty(this, "end");
    private final StringProperty time = new SimpleStringProperty(this, "time");


    public Order(int id, String userid, int filmsId, int sessionid, String num, int price, String start, String end, String time) {
        this.id.set(id);
        this.userid.set(userid);
        this.filmsId.set(filmsId);
        this.sessionid.set(sessionid);
        this.num.set(num);
        this.price.set(price);
        this.start.set(start);
        this.end.set(end);
        this.time.set(time);
    }
    public Order() {
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

    public String getUserid() {
        return userid.get();
    }

    public StringProperty useridProperty() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid.set(userid);
    }

    public int getFilmsId() {
        return filmsId.get();
    }

    public IntegerProperty filmsIdProperty() {
        return filmsId;
    }

    public void setFilmsId(int filmsId) {
        this.filmsId.set(filmsId);
    }

    public int getSessionid() {
        return sessionid.get();
    }

    public IntegerProperty sessionidProperty() {
        return sessionid;
    }

    public void setSessionid(int sessionid) {
        this.sessionid.set(sessionid);
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

    public int getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
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
