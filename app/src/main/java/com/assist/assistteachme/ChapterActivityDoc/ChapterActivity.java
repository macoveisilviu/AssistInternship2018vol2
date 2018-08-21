package com.assist.assistteachme.ChapterActivityDoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.assist.assistteachme.QuestionActivityDoc.QuestionActivity;
import com.assist.assistteachme.R;

import java.util.ArrayList;

/**
 * Created by anairda on 8/21/2018.
 */

public class ChapterActivity extends AppCompatActivity  implements RecyclerViewAdapterChapter.OnItemClickListener{
    RecyclerView recyclerView;

    ArrayList<ChapterModel> list = new ArrayList<ChapterModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_activity);

        populateDummyData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChapterActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapterChapter(list, this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    private void populateDummyData() {
        ChapterModel one = new ChapterModel("CHAPTER I",
                "Welcome To Desiclassifieds Free Classifieds Free Ads",
                "It’s hard to say when in our lives each of us become aware of this thing called “astronomy”. " +
                        " But it is safe to say that at some point on our lives, each and every one of us has that moment when " +
                        "we are suddenly stunned when we come face to face with the enormity of the universe that we see in the" +
                        " night sky.");
        ChapterModel two = new ChapterModel("CHAPTER II",
                "Welcome To Desiclassifieds Free Classifieds Free Ads",
                "It’s hard to say when in our lives each of us become aware of this thing called “astronomy”. " +
                        " But it is safe to say that at some point on our lives, each and every one of us has that moment when " +
                        "we are suddenly stunned when we come face to face with the enormity of the universe that we see in the" +
                        " night sky.");
        ChapterModel thre = new ChapterModel("CHAPTER III",
                "Welcome To Desiclassifieds Free Classifieds Free Ads",
                "It’s hard to say when in our lives each of us become aware of this thing called “astronomy”. " +
                        " But it is safe to say that at some point on our lives, each and every one of us has that moment when " +
                        "we are suddenly stunned when we come face to face with the enormity of the universe that we see in the" +
                        " night sky.");
        list.add(one);
        list.add(two);
        list.add(thre);
    }


    @Override
    public void onChapterClick(ChapterModel chapterModel) {
        startActivity(new Intent(ChapterActivity.this, QuestionActivity.class));

    }
}
