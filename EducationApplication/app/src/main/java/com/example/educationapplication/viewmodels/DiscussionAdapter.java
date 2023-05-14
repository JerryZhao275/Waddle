package com.example.educationapplication.viewmodels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapplication.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataObjects.DiscussionDto;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.DiscussionViewHolder> {
    private static List<DiscussionDto> discussions = new ArrayList<>();

    public DiscussionAdapter(List<DiscussionDto> discussions) {
        this.discussions = discussions;
    }

    // Constructor and methods

    @NonNull
    @Override
    public DiscussionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discussion, parent, false);
        return new DiscussionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionViewHolder holder, int position) {
        DiscussionDto discussion = discussions.get(position);
        holder.titleTextView.setText(discussion.getTitle());
        holder.contentTextView.setText(discussion.getContent());
        holder.authorTextView.setText(discussion.getAuthor());
        holder.timestampTextView.setText(formatTimestamp(discussion.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return discussions.size();
    }

    // ViewHolder class
    static class DiscussionViewHolder extends RecyclerView.ViewHolder {
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
        }
    }

    // Utility method to format timestamp
    private String formatTimestamp(Date timestamp) {
        // Implement your desired timestamp formatting logic here
        return timestamp.toString();
    }
}
