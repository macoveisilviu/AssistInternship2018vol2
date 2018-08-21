package com.assist.assistteachme.QuestionActivityDoc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.assist.assistteachme.MyAccount.AccountActivity;
import com.assist.assistteachme.MyAccount.Courses;
import com.assist.assistteachme.MyAccount.RecyclerViewAdapterDoc;
import com.assist.assistteachme.R;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity implements RecyclerViewAdapterQuestion.OnItemClickListener{
    RecyclerView recyclerView;
    ArrayList<QuestionModel> list = new ArrayList<QuestionModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        populateDummyData();
        
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(QuestionActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapterQuestion(list, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void populateDummyData() {

        QuestionModel one = new QuestionModel("Where Can You Find Unique Myspace Layouts Nowadays?",
                "From Wetlands To Canals And Dams Amsterdam Is Alive",
                "From Wetlands To Canals And Dams Amsterdam Is Alive",
                "From Wetlands To Canals And Dams Amsterdam Is Alive");
        QuestionModel two = new QuestionModel("Where Can You Find Unique Myspace Layouts Nowadays?",
                "From Wetlands To Canals And Dams Amsterdam Is Alive",
                "From Wetlands To Canals And Dams Amsterdam Is Alive",
                "From Wetlands To Canals And Dams Amsterdam Is Alive");
        QuestionModel three = new QuestionModel("Where Can You Find Unique Myspace Layouts Nowadays?",
                "From Wetlands To Canals And Dams Amsterdam Is Alive",
                "From Wetlands To Canals And Dams Amsterdam Is Alive",
                "From Wetlands To Canals And Dams Amsterdam Is Alive");

        list.add(one);
        list.add(two);
        list.add(three);
    }

    @Override
    public void onQuestionrClick(QuestionModel question) {
        
    }
}
