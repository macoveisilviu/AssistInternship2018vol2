package com.assist.assistteachme.CoursesActivityDoc;

/**
 * Created by anairda on 8/27/2018.
 */

public class CoursesResponseModel {
    int id;
    String Title;
    String Summary;
    String Description;
    String Tags;
    String Path;
    String createdAt;
    String updateAt;
    int categoryId;

    public CoursesResponseModel(int id, String title, String summary, String description, String tags, String path, String createdAt, String updateAt, int categoryId) {
        this.id = id;
        Title = title;
        Summary = summary;
        Description = description;
        Tags = tags;
        Path = path;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.categoryId = categoryId;
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

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
