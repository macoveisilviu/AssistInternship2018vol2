package com.assist.assistteachme.Models;

import java.util.ArrayList;

public enum  User {
    INSTANCE;

    User(){}

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    ArrayList<ChapterRecive> chapterReciveArrayList;

    public ArrayList<ChapterRecive> getChapterReciveArrayList() {
        return chapterReciveArrayList;
    }

    public void setChapterReciveArrayList(ArrayList<ChapterRecive> chapterReciveArrayList) {
        this.chapterReciveArrayList = chapterReciveArrayList;
    }
    private int courseId;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    private int chapterId;

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

}
