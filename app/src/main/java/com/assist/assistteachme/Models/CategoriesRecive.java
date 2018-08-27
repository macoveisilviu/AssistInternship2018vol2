package com.assist.assistteachme.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoriesRecive {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("createdAt")
    @Expose
    private Date createdAt;
    @SerializedName("updatedAt")
    @Expose
    private  Date updatedAt;
    @SerializedName("courses")
    @Expose
    private ArrayList<CoursesRecive> courses;

    public CategoriesRecive(int id, String name, Date createdAt, Date updatedAt, ArrayList<CoursesRecive> courses) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.courses = courses;
    }

    public ArrayList<CoursesRecive> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<CoursesRecive> courses) {
        this.courses = courses;
    }

    public CategoriesRecive() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
