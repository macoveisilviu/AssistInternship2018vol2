package com.assist.assistteachme.CoursesActivityDoc;/*
package com.assist.assistteachme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assist.assistteachme.MyAccount.Courses;
import com.bumptech.glide.Glide;

import java.util.List;

*/

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.assist.assistteachme.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anairda on 8/20/2018.
 *//*


*/
public class RecyclerViewAdapterCourses extends RecyclerView.Adapter<RecyclerViewAdapterCourses.ViewHolder> {


    public interface OnItemClickListener {
        void onCourseClick(AllCourses courses);
    }

    private final List<AllCourses> courses;
    private final RecyclerViewAdapterCourses.OnItemClickListener listener;


    public RecyclerViewAdapterCourses(ArrayList<AllCourses> courses, OnItemClickListener listener) {

        this.courses = courses;
        this.listener = listener;

    }

    @Override
    public RecyclerViewAdapterCourses.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_adapter_allcourses_activity, parent, false);

        RecyclerViewAdapterCourses.ViewHolder viewHolder = new RecyclerViewAdapterCourses.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterCourses.ViewHolder holder, int position) {
        holder.bind(courses.get(position), listener);


    }

    @Override
    public int getItemCount() {
        return courses.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgViewCourse;
        private TextView txtViewTitle;
        private TextView txtViewDescription;


        public ViewHolder(View itemView) {
            super(itemView);

            txtViewTitle = (TextView) itemView.findViewById(R.id.courseTitle);
            txtViewDescription = (TextView) itemView.findViewById(R.id.courseDescription);
            imgViewCourse = (ImageView) itemView.findViewById(R.id.imgViewCourse);

        }

        public void bind(final AllCourses courses, final OnItemClickListener listener) {
            txtViewDescription.setText(courses.getCourse_description());
            txtViewTitle.setText(courses.getTitle());

            Glide.with(itemView.getContext()).load(courses.getImg()).into(imgViewCourse);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCourseClick(courses);
                }
            });
        }
    }


}
