package com.assist.assistteachme.MainViewDoc;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assist.assistteachme.R;

import java.util.ArrayList;

/**
 * Created by anairda on 8/25/2018.
 */

public class RecyclerViewAdapterMainView extends RecyclerView.Adapter<RecyclerViewAdapterMainView.ViewHolder> {


    public interface OnItemClickListener {
        void onCategoryClick(CategoryResponseModel category);

    }

    private final ArrayList<CategoryResponseModel> categoryTitleList;
    private final OnItemClickListener listener;
    private Context context;

    public RecyclerViewAdapterMainView(ArrayList<CategoryResponseModel> categoryTitleList, OnItemClickListener listener, Context context) {

        this.categoryTitleList = categoryTitleList;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.element_adapter_mainview_activity, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(categoryTitleList.get(position), listener);

    }

    private int randomImage() {
        int image = 0;

        return image;
    }


    @Override
    public int getItemCount() {
        return categoryTitleList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        private TextView txtViewTitle;
        private LinearLayout mLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            txtViewTitle = (TextView) itemView.findViewById(R.id.categoryTitle);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.ll);


        }

        public void bind(final CategoryResponseModel category, final OnItemClickListener listener) {

            txtViewTitle.setText(category.getCategoryName());

            Drawable draw;
            for (int i = 0; i < 4; i++) {
                if (i % 3 == 0)
                    draw = context.getResources().getDrawable(R.drawable.rectangle3);
                else if (i % 3 == 1)
                    draw = context.getResources().getDrawable(R.drawable.rectangle2);
                else
                    draw = context.getResources().getDrawable(R.drawable.rectangle1);

                mLinearLayout.setBackground(draw);
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCategoryClick(category);


                }


                //onCourseClick
            });
        }


    }


}