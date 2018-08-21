package com.assist.assistteachme.Models;

public class Course {
    String title;
    String subTitle;
    String paragraph;

    public Course() {
    }

    public Course(String title, String subTitle, String paragraph) {
        this.title = title;
        this.subTitle = subTitle;
        this.paragraph = paragraph;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }
}
