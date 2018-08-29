package com.assist.assistteachme.QuestionActivityDoc;

/**
 * Created by anairda on 8/28/2018.
 */

public class QuestionResponseModel {
    int id;
    String Question;
    String createdAt;
    String updatedAt;
    int chapterId;

    public QuestionResponseModel(int id, String question, String createdAt, String updatedAt, int chapterId) {
        this.id = id;
        Question = question;
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

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
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

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }
}
