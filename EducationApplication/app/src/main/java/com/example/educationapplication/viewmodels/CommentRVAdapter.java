package com.example.educationapplication.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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

import dataObjects.CommentDto;
import dataObjects.CustomOnCompleteListener;
import dataObjects.DiscussionDto;
import dataObjects.QuizDto;

public class CommentRVAdapter extends RecyclerView.Adapter<CommentRVAdapter.CommentViewHolder>{
    private static Context mContext;
    private static List<CommentDto> mData = new ArrayList<>();
    private CommentViewModel commentViewModel;
    public CommentRVAdapter(Context mContext, List<CommentDto> mData, DiscussionDto discussion) {
        this.mContext = mContext;
        this.mData = mData;
        commentViewModel = new CommentViewModel(discussion, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                setComments(commentViewModel.getComments());
            }
        });


        //TESTING PURPOSES
        /*mData.add(new CommentDto("nice", "Peer 1", new Date()));
        mData.add(new CommentDto("nice", "Peer 2", new Date()));
        mData.add(new CommentDto("nice", "Peer 3", new Date()));
        mData.add(new CommentDto("nice", "Peer 4", new Date()));
        mData.add(new CommentDto("nice", "Teacher 1", new Date()));*/
    }
    public void addComment(CommentDto comment){
        commentViewModel.addComment(comment, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                commentViewModel.syncComments(new CustomOnCompleteListener() {
                    @Override
                    public void onComplete() {
                        setComments(commentViewModel.getComments());
                    }
                });
            }
        });
    }

    // inflating layout (giving layout the look)
    public void setComments(List<CommentDto> comments){
        mData = comments;
        notifyDataSetChanged();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false);
        CommentViewHolder vHolder = new CommentViewHolder(v);
        return vHolder;
    }

    // assigning values to each rows as they come onto screen
    // based on position of recycler view
    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {

        CommentDto comment = mData.get(position);
        holder.bind(comment);

        // Set item views based on your views and data model

        holder.commentText.setText(comment.getComment());
        holder.authorCommentTextView.setText(comment.getAuthor());
        holder.timestampCommentTextView.setText(formatTimestamp(comment.getTimestamp()));
    }

    //number of items displayed
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // grabbing views from recycler view layout
    // Recycler view equivalent to onCreate method
    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        private TextView commentText, authorCommentTextView, timestampCommentTextView;

        public CommentViewHolder(View item) {
            super(item);
            commentText = item.findViewById(R.id.commentTextView);
            authorCommentTextView = item.findViewById(R.id.authorCommentTextView);
            timestampCommentTextView = item.findViewById(R.id.timestampCommentTextView);

        }

        public void bind(CommentDto comment) {
            commentText.setText(comment.getComment());
            authorCommentTextView.setText(comment.getAuthor());
            timestampCommentTextView.setText(comment.getComment());

        }
    }
    private String formatTimestamp(Date timestamp) {
        // Implement your desired timestamp formatting logic here
        return timestamp.toString();
    }

}
