package com.assist.assistteachme;

/**
 * Created by anairda on 8/16/2018.
 */

public class AlbumCategory {
    private String category;
    private int categoryImage;


    public AlbumCategory(String math, String category, int categoryImage) {
        this.category = category;
        this.categoryImage = categoryImage;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }


}
