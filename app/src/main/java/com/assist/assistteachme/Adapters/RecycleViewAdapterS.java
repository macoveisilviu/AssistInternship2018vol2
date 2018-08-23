package com.assist.assistteachme.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.BrowseThroughCourses;
import com.assist.assistteachme.CourseDetailScreenActivity;
import com.assist.assistteachme.Models.CourseDetails;
import com.assist.assistteachme.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecycleViewAdapterS extends RecyclerView.Adapter<RecycleViewAdapterS.ViewHolder> {

    private static final String TAG = "RecycleViewAdapterS";

    private ArrayList<CourseDetails> coursedetails = new ArrayList<>();
    Context mContext;

    public RecycleViewAdapterS() {
    }

    public RecycleViewAdapterS(ArrayList<CourseDetails> coursedetails, Context mContext) {
        this.coursedetails = coursedetails;
        this.mContext = mContext;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_browse_through_courses, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "on Bind ViewHolder");

        CourseDetails listS = coursedetails.get(position);

        holder.textCourse.setText(listS.getTitle());
        holder.textSecond.setText(listS.getSubtitle());
        holder.financeGradient.setText(listS.getCategory());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "asda",Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext, CourseDetailScreenActivity.class));
            }
        });

        }

    @Override
    public int getItemCount() {

        return coursedetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView textCourse;
        TextView textSecond;
        TextView financeGradient;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.course_photo);
            textCourse = itemView.findViewById(R.id.course_textt);
            textSecond = itemView.findViewById(R.id.course_secondtextt);
            financeGradient = itemView.findViewById(R.id.finance_gradient);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
