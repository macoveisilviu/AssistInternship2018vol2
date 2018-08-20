package com.assist.assistteachme.Models;

public class CourseDetails {

    private String title;
    private String subtitle;
    private String category;

    public CourseDetails() {

    }

    public CourseDetails( String title, String subtitle, String category) {

        this.title = title;
        this.subtitle = subtitle;
        this.category = category;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
