package com.assist.assistteachme.Models;

import android.graphics.drawable.Drawable;

public class MyAccountMenuModel {

    private String text1;
    private String text2;
    private String points;
    private Drawable backgroundSeekBar;

    public MyAccountMenuModel() {
    }

    public MyAccountMenuModel(String text1, String text2, String points, Drawable backgroundSeekBar) {
        this.text1 = text1;
        this.text2 = text2;
        this.points = points;
        this.backgroundSeekBar = backgroundSeekBar;
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

    public Drawable getBackgroundSeekBar() {
        return backgroundSeekBar;
    }

    public void setBackgroundSeekBar(Drawable backgroundSeekBar) {
        this.backgroundSeekBar = backgroundSeekBar;
    }
}
