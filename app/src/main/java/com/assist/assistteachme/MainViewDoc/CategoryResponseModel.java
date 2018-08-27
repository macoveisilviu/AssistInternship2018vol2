package com.assist.assistteachme.MainViewDoc;

import java.util.ArrayList;

/**
 * Created by anairda on 8/27/2018.
 */

public class CategoryResponseModel {
    int id;
    String CategoryName;
    String Background;
    String createdAt;
    String updatedAt;



    public CategoryResponseModel(int id, String categoryName, String background, String createdAt, String updatedAt) {
        this.id = id;
        this.CategoryName = categoryName;
        this.Background = background;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CategoryResponseModel(String categoryName) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
    public String getBackground() {
        return Background;
    }

    public void setBackground(String background) {
        Background = background;
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
}
