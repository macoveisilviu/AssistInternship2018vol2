
package com.assist.assistteachme.CoursesActivityDoc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assist.assistteachme.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


/**
 * Created by anairda on 8/20/2018.
 *//*
*/





public class RecyclerViewAdapterCourses extends RecyclerView.Adapter<RecyclerViewAdapterCourses.ViewHolder> {


    public interface OnItemClickListener {
        void onCourseClick(CoursesResponseModel courses);
    }

    private final ArrayList<CoursesResponseModel> courses;
    private final OnItemClickListener listener;


    public RecyclerViewAdapterCourses(ArrayList<CoursesResponseModel> courses, OnItemClickListener listener) {

        this.courses = courses;
        this.listener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_adapter_allcourses_activity, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
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
       // private TextView txtCategory;


        public ViewHolder(View itemView) {
            super(itemView);
           // txtCategory= (TextView) itemView.findViewById(R.id.courseTitle);
            txtViewTitle = (TextView) itemView.findViewById(R.id.courseTitle);
            txtViewDescription = (TextView) itemView.findViewById(R.id.courseDescription);
            imgViewCourse = (ImageView) itemView.findViewById(R.id.imgViewCourse);

        }

        public void bind(final CoursesResponseModel courses, final OnItemClickListener listener) {
            txtViewDescription.setText(courses.getDescription());
            txtViewTitle.setText(courses.getTitle());


          // Glide.with(itemView.getContext()).load(courses.getPath().into(imgViewCourse));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCourseClick(courses);
                }
            });
        }
    }


}
