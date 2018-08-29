package com.assist.assistteachme.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class QuizesRecive {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("content")
    @Expose
    public String content ;
    @SerializedName("createdAt")
    @Expose
    public Date createdAt ;
    @SerializedName("updatedAt")
    @Expose
    public Date updatedAt ;
    @SerializedName("chapterId")
    @Expose
    public int chapterId ;

    public QuizesRecive() {
    }

    public QuizesRecive(int id, String content, Date createdAt, Date updatedAt, int chapterId) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.chapterId = chapterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }
}
