package com.assist.assistteachme.MainViewDoc;

/**
 * Created by anairda on 8/27/2018.
 */

public class SearchCategoryModel {
    String CategoryName;


    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public SearchCategoryModel(String categoryName) {
        CategoryName = categoryName;
    }
}
