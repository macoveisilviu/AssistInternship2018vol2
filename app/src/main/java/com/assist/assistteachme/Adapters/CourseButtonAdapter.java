package com.assist.assistteachme.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.ActvityFinishCourseDrawer;
import com.assist.assistteachme.BrowseThroughCourses;
import com.assist.assistteachme.DrawerTestActivity;
import com.assist.assistteachme.Models.ChapterQuestion;
import com.assist.assistteachme.Models.CourseButton;
import com.assist.assistteachme.R;

import java.util.ArrayList;

public class CourseButtonAdapter  extends RecyclerView.Adapter<CourseButtonAdapter.ViewHolder>{

    private static final String TAG = "RecycleViewAdaptersC";

    private ArrayList<CourseButton> courseButtonsList = new ArrayList<>();
    Context mContext;

    public CourseButtonAdapter(ArrayList<CourseButton> courseButtonsList, Context mContext) {
        this.courseButtonsList = courseButtonsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_recycle_layout, parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseButtonAdapter.ViewHolder holder, final int position) {
        Log.d(TAG,"on bind ViewHolder");

        final CourseButton listQ = courseButtonsList.get(position);
        Log.d("CourseDebugg", listQ.getCourseName().toString());
        holder.courseTitle.setText(listQ.getCourseName());
        holder.courseTitle.setBackground(listQ.getBackground());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "Curs "+position ,Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext, BrowseThroughCourses.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseButtonsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView courseTitle;
        CardView parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.courseNameTextView);
            parentLayout = itemView.findViewById(R.id.cardview);
        }
    }
}
