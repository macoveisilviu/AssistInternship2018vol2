package com.assist.assistteachme.MyAccountDoc;

/**
 * Created by anairda on 8/20/2018.
 */

public class Courses {
    int img;
    String title;
    String course_description;
    int points;

    public Courses(int profile, String s, String s1) {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    public Courses(int img, String title, String course_description, int points) {
        this.img = img;
        this.title = title;
        this.course_description = course_description;
        this.points = points;
    }


}
