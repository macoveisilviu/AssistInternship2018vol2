package com.assist.assistteachme.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.BrowseThroughCourses;
import com.assist.assistteachme.ChapterScreenActivity;
import com.assist.assistteachme.Models.Course;
import com.assist.assistteachme.R;

import java.util.ArrayList;

public class RecycleViewAdaptersC extends RecyclerView.Adapter<RecycleViewAdaptersC.ViewHolder>{

    private static final String TAG = "RecycleViewAdaptersC";

    private ArrayList<Course> course = new ArrayList<>();
    Context mContext;

    public RecycleViewAdaptersC(ArrayList<Course> course, Context mContext) {
        this.course = course;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_detail_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"on bind ViewHolder");

        Course listC = course.get(position);

        holder.chapter.setText(listC.getTitle());
        holder.firstParagraph.setText(listC.getSubTitle());
        holder.secondParagraph.setText(listC.getParagraph());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "asda",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return course.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView chapter;
        TextView firstParagraph;
        TextView secondParagraph;
        Button startButton;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            chapter = itemView.findViewById(R.id.firstTextView);
            firstParagraph = itemView.findViewById(R.id.secondTextView);
            secondParagraph = itemView.findViewById(R.id.thirdTextView);
            startButton = itemView.findViewById(R.id.startButton);
            parentLayout = itemView.findViewById(R.id.relativeLayoutRecycle);

            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // myAccountMenuList.remove(getAdapterPosition());
                    Toast.makeText(mContext, "asda "+getAdapterPosition(),Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext, ChapterScreenActivity.class));
                }
            });
        }


    }
}
