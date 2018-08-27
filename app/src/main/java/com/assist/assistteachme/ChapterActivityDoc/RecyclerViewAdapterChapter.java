package com.assist.assistteachme.ChapterActivityDoc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assist.assistteachme.R;

import java.util.List;

/**
 * Created by anairda on 8/21/2018.
 */

public class RecyclerViewAdapterChapter extends RecyclerView.Adapter<RecyclerViewAdapterChapter.ViewHolder> {


    public interface OnItemClickListener {
        void onChapterClick(ChapterModel chapter);
    }

    private final List<ChapterModel> chapter;
    private final RecyclerViewAdapterChapter.OnItemClickListener listener;


    public RecyclerViewAdapterChapter(List<ChapterModel> chapter, OnItemClickListener listener) {

        this.chapter = chapter;
        this.listener = listener;

    }


    @Override
    public RecyclerViewAdapterChapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.element_adapter_chapter_activity, parent, false);

        RecyclerViewAdapterChapter.ViewHolder viewHolder = new RecyclerViewAdapterChapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterChapter.ViewHolder holder, int position) {
        holder.bind(chapter.get(position), listener);


    }

    @Override
    public int getItemCount() {
        return chapter.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewTitle;
        private TextView txtViewDescription;
        private TextView txtViewChapterNo;


        public ViewHolder(View itemView) {
            super(itemView);
            txtViewChapterNo = (TextView) itemView.findViewById(R.id.chapterTxt);
            txtViewTitle = (TextView) itemView.findViewById(R.id.chapterTitle);
            txtViewDescription = (TextView) itemView.findViewById(R.id.chapterDescription);
        }

        public void bind(final ChapterModel chapter,
                         final RecyclerViewAdapterChapter.OnItemClickListener listener) {
            txtViewDescription.setText(chapter.getChapterDescription());
            txtViewChapterNo.setText(chapter.getChapterNo());
            txtViewTitle.setText(chapter.getChapterName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onChapterClick(chapter);
                }
            });
        }
    }


}


