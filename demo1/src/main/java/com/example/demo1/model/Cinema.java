package com.example.demo1.model;

public class Cinema {
    private int cinemaID; // 可能在添加新记录时不会立即知道
    private String cinemaName;

    // 由于cinemaID是由数据库自动生成的，我们添加一个构造函数只需要cinemaName
    public Cinema(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    // 全参构造函数，用于从数据库加载记录时
    public Cinema(int cinemaID, String cinemaName) {
        this.cinemaID = cinemaID;
        this.cinemaName = cinemaName;
    }

    // cinemaID的getter
    public int getCinemaID() {
        return cinemaID;
    }

    // cinemaID的setter
    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    // cinemaName的getter
    public String getCinemaName() {
        return cinemaName;
    }

    // cinemaName的setter
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    // 为了方便显示在列表或控制台中，重写toString方法
    @Override
    public String toString() {
        return "Cinema{" +
                "cinemaID=" + cinemaID +
                ", cinemaName='" + cinemaName + '\'' +
                '}';
    }
}