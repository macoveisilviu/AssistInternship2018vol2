package com.assist.assistteachme.CoursesActivityDoc;

/**
 * Created by anairda on 8/21/2018.
 */

public class AllCourses {
    int img;
    String title;
    String course_description;



    public AllCourses(int img, String title, String course_description) {
        this.img = img;
        this.title = title;
        this.course_description = course_description;

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





}
