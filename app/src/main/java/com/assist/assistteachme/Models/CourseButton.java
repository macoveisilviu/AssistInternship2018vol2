package com.assist.assistteachme.Models;

import android.graphics.drawable.Drawable;
import android.text.style.BackgroundColorSpan;

public class CourseButton {
    private String courseName;
    private Drawable background;

    public CourseButton(String courseName,Drawable background) {
        this.courseName = courseName;
        this.background = background;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Drawable getBackground() {
        return background;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }
}
