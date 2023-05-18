package com.example.educationapplication.viewmodels;

import android.content.Context;
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
import com.example.educationapplication.search.dataObjects.*;

/**
 * Comment Recycle View Adapter
 */
public class CommentRVAdapter extends RecyclerView.Adapter<CommentRVAdapter.CommentViewHolder> {
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
    }

    /**
     * Add a comment to the discussion.
     *
     * @param comment The comment to be added.
     */
    public void addComment(CommentDto comment) {
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

    /**
     * Set the comments for the adapter.
     *
     * @param comments The list of comments to be set.
     */
    public void setComments(List<CommentDto> comments) {
        mData = comments;
        notifyDataSetChanged();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        CommentViewHolder vHolder = new CommentViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        CommentDto comment = mData.get(position);
        holder.bind(comment);

        // Set item views based on your views and data model
        holder.commentText.setText(comment.getComment());
        holder.authorCommentTextView.setText(comment.getAuthor());
        holder.timestampCommentTextView.setText(formatTimestamp(comment.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

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

    /**
     * Format the timestamp for display.
     *
     * @param timestamp The timestamp to be formatted.
     * @return The formatted timestamp.
     */
    private String formatTimestamp(Date timestamp) {
        // Implement your desired timestamp formatting logic here
        return timestamp.toString();
    }
}
