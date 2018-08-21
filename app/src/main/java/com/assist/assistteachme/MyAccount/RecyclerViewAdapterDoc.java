package com.assist.assistteachme.MyAccount;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.assist.assistteachme.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by anairda on 8/20/2018.
 */

public class RecyclerViewAdapterDoc extends RecyclerView.Adapter<RecyclerViewAdapterDoc.ViewHolder> {


    public interface OnItemClickListener {
        void onCourseClick(Courses courses);
    }

    private final List<Courses> courses;
    private final OnItemClickListener listener;


    public RecyclerViewAdapterDoc(List<Courses> courses, OnItemClickListener listener) {

        this.courses = courses;
        this.listener = listener;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_adapter_started_courses, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
        private TextView txtViewPoints;
        public Button btnDelete;


        public ViewHolder(View itemView) {
            super(itemView);
            txtViewPoints = (TextView) itemView.findViewById(R.id.coursePoints);
            txtViewTitle = (TextView) itemView.findViewById(R.id.courseTitle);
            txtViewDescription = (TextView) itemView.findViewById(R.id.courseDescription);
            imgViewCourse = (ImageView) itemView.findViewById(R.id.imgViewCourse);
            btnDelete=(Button) itemView.findViewById(R.id.btnDeleteCourse);
        }

        public void bind(final Courses courses, final OnItemClickListener listener) {
            txtViewDescription.setText(courses.getCourse_description());
            txtViewPoints.setText(Integer.toString(courses.getPoints()));
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



