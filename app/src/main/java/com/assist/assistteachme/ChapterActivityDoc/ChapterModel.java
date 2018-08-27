package com.assist.assistteachme.ChapterActivityDoc;

/**
 * Created by anairda on 8/21/2018.
 */

public class ChapterModel {
    String chapterNo;
    String chapterName;
    String chapterDescription;

    public String getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(String chapeterNo) {
        this.chapterNo = chapeterNo;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterDescription() {
        return chapterDescription;
    }

    public void setChapterDescription(String chapterDescription) {
        this.chapterDescription = chapterDescription;
    }


    public ChapterModel(String chapterNo, String chapterName, String chapterDescription) {
        this.chapterNo = chapterNo;
        this.chapterName = chapterName;
        this.chapterDescription = chapterDescription;
    }


}
