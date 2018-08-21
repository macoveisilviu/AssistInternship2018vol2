package com.assist.assistteachme.QuestionActivityDoc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assist.assistteachme.ChapterActivityDoc.ChapterModel;
import com.assist.assistteachme.ChapterActivityDoc.RecyclerViewAdapterChapter;
import com.assist.assistteachme.R;

import java.util.List;

/**
 * Created by anairda on 8/21/2018.
 */

public class RecyclerViewAdapterQuestion extends RecyclerView.Adapter<RecyclerViewAdapterQuestion.ViewHolder> {


    public interface OnItemClickListener {
        void onQuestionrClick(QuestionModel question);
    }

    private final List<QuestionModel > question;
    private final RecyclerViewAdapterQuestion.OnItemClickListener listener;


    public RecyclerViewAdapterQuestion(List<QuestionModel > question, RecyclerViewAdapterQuestion.OnItemClickListener listener) {

        this.question = question;
        this.listener = listener;

    }


    @Override
    public RecyclerViewAdapterQuestion.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.element_adapter_question_activity, parent, false);

        RecyclerViewAdapterQuestion.ViewHolder viewHolder = new RecyclerViewAdapterQuestion.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterQuestion.ViewHolder holder, int position) {
        holder.bind(question.get(position), listener);


    }

    @Override
    public int getItemCount() {
        return question.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewQuestion;
        private TextView txtViewAnswerOne;
        private TextView txtViewAnswerTwo;
        private TextView txtViewAnswerThree;

        public ViewHolder(View itemView) {
            super(itemView);
            txtViewQuestion = (TextView) itemView.findViewById(R.id.questionTxt);
            txtViewAnswerOne = (TextView) itemView.findViewById(R.id.rb_one);
            txtViewAnswerTwo = (TextView) itemView.findViewById(R.id.rb_two);
            txtViewAnswerThree = (TextView) itemView.findViewById(R.id.rb_three);

        }

        public void bind(final QuestionModel  question, final RecyclerViewAdapterQuestion.OnItemClickListener listener) {
            txtViewQuestion.setText(question.getQuestion());
            txtViewAnswerOne.setText(question.getAnswer_one());
            txtViewAnswerTwo.setText(question.getAnswer_two());
            txtViewAnswerThree.setText(question.getAnswer_three());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onQuestionrClick(question);
                }
            });
        }
    }


}
