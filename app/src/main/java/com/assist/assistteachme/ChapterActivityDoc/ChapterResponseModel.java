package com.assist.assistteachme.ChapterActivityDoc;

/**
 * Created by anairda on 8/28/2018.
 */

public class ChapterResponseModel {
    int id;
    String Title;
    String Content;
    String createdAt;
    String updatedAt;
    int courseId;

    public ChapterResponseModel(int id, String title, String content, String createdAt, String updatedAt, int courseId) {
        this.id = id;
        Title = title;
        Content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
