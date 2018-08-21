package com.assist.assistteachme.Models;

public class MyAccountMenuModel {

    private String text1;
    private String text2;
    private String points;

    public MyAccountMenuModel() {
    }

    public MyAccountMenuModel(String text1, String text2, String points) {
        this.text1 = text1;
        this.text2 = text2;
        this.points = points;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
