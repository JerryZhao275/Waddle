package com.example.educationapplication.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapplication.R;
import com.example.educationapplication.views.Fragment.DiscussionPage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataObjects.DiscussionDto;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.DiscussionViewHolder> {
    private static Context mContext;

    private static List<DiscussionDto> discussions = new ArrayList<>();

    public DiscussionAdapter(Context mContext, List<DiscussionDto> discussions) {
        this.mContext = mContext;
        this.discussions = discussions;

        discussions.add(new DiscussionDto("Title 1", "Content 1", "Author 1", new Date()));
        discussions.add(new DiscussionDto("Title 2", "Content 2", "Author 2", new Date()));
    }

    // Constructor and methods

    @Override
    public DiscussionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discussion, parent, false);
        DiscussionViewHolder vHolder = new DiscussionViewHolder(v);
        return vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionViewHolder holder, int position) {
        DiscussionDto discussion = discussions.get(position);

        // Set item views based on your views and data model
        TextView title = holder.titleTextView;
        title.setText(discussion.getTitle());

        holder.titleTextView.setText(discussion.getTitle());
        holder.contentTextView.setText(discussion.getContent());
        holder.authorTextView.setText(discussion.getAuthor());
        holder.timestampTextView.setText(formatTimestamp(discussion.getTimestamp()));
        holder.bind(discussion);

    }

    @Override
    public int getItemCount() {
        return discussions.size();
    }

    // ViewHolder class
    static class DiscussionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView;
        TextView contentTextView;
        TextView authorTextView;
        TextView timestampTextView;

        DiscussionViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            timestampTextView = itemView.findViewById(R.id.timestampTextView);
            itemView.setOnClickListener(this);
        }
        public void bind(DiscussionDto course) {
            titleTextView.setText(course.getTitle());
            contentTextView.setText(course.getTitle());
            authorTextView.setText(course.getTitle());
            timestampTextView.setText(course.getTitle());}
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            DiscussionDto selectedItem = discussions.get(position);
            Intent intent = new Intent(mContext, DiscussionPage.class);
            mContext.startActivity(intent);
        }
    }


    // Utility method to format timestamp
    private String formatTimestamp(Date timestamp) {
        // Implement your desired timestamp formatting logic here
        return timestamp.toString();
    }
}
