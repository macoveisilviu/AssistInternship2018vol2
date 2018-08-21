package com.assist.assistteachme.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.Models.ChapterQuestion;
import com.assist.assistteachme.R;

import java.util.ArrayList;

public class ChapterQuestionsAdapter  extends RecyclerView.Adapter<ChapterQuestionsAdapter.ViewHolder>{

    private static final String TAG = "RecycleViewAdaptersC";

    private ArrayList<ChapterQuestion> chapterQuestionsList = new ArrayList<>();
    Context mContext;

    public ChapterQuestionsAdapter(ArrayList<ChapterQuestion> chapterQuestionsList, Context mContext) {
        this.chapterQuestionsList = chapterQuestionsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_recycle_layout, parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterQuestionsAdapter.ViewHolder holder, final int position) {
        Log.d(TAG,"on bind ViewHolder");

        ChapterQuestion listQ = chapterQuestionsList.get(position);

        holder.question.setText(listQ.getQuestion());
        holder.answer1.setText(listQ.getAnswer1());
        holder.answer2.setText(listQ.getAnswer2());
        holder.answer3.setText(listQ.getAnswer3());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "asda",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterQuestionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView question;
        TextView answer1;
        TextView answer2;
        TextView answer3;
        CheckBox checkBox1;
        CheckBox checkBox2;
        CheckBox checkBox3;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.questionTextView);
            answer1 = itemView.findViewById(R.id.answer1TextView);
            answer2 =itemView.findViewById(R.id.answer2TextView);
            answer3 = itemView.findViewById(R.id.answer3TextView);
            checkBox1 = itemView.findViewById(R.id.checkb1);
            checkBox2 = itemView.findViewById(R.id.checkb2);
            checkBox3 = itemView.findViewById(R.id.checkb3);
            parentLayout = itemView.findViewById(R.id.recycleLayoutChapter);
        }
    }
}
