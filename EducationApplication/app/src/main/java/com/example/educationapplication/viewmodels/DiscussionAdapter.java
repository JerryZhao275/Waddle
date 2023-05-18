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
import com.example.educationapplication.util.views.DiscussionPage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.educationapplication.search.dataObjects.*;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.DiscussionViewHolder> {
    private static Context mContext;
    private static List<DiscussionDto> discussions = new ArrayList<>();
    static DiscussionViewModel discussionViewModel;

    public DiscussionAdapter(Context mContext, List<DiscussionDto> discussions, CourseDto course) {
        this.mContext = mContext;
        this.discussions = discussions;
        discussionViewModel = new DiscussionViewModel(course, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                setDiscussions(discussionViewModel.getDiscussions());
            }
        });
    }

    /**
     * Set the list of discussions in the adapter.
     *
     * @param discussions The list of discussions.
     */
    public void setDiscussions(List<DiscussionDto> discussions) {
        this.discussions = discussions;
        notifyDataSetChanged();
    }

    /**
     * Add a new discussion to the adapter and database.
     *
     * @param discussion The discussion to be added.
     */
    public void addDiscussion(DiscussionDto discussion) {
        discussionViewModel.addDiscussion(discussion, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                discussionViewModel.syncDiscussions(new CustomOnCompleteListener() {
                    @Override
                    public void onComplete() {
                        setDiscussions(discussionViewModel.getDiscussions());
                    }
                });
            }
        });
    }

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
        holder.bind(discussion);

        // Set item views based on your views and data model
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
    static class DiscussionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTextView, contentTextView, authorTextView, timestampTextView;

        DiscussionViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            timestampTextView = itemView.findViewById(R.id.timestampTextView);
            itemView.setOnClickListener(this);
        }

        /**
         * Bind the discussion data to the views.
         *
         * @param discussion The discussion data to be bound.
         */
        public void bind(DiscussionDto discussion) {
            titleTextView.setText(discussion.getTitle());
            contentTextView.setText(discussion.getContent());
            authorTextView.setText(discussion.getAuthor());
            timestampTextView.setText(discussion.getTimestamp().toString());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            DiscussionDto selectedItem = discussions.get(position);
            Intent intent = new Intent(mContext, DiscussionPage.class);
            intent.putExtra("user", discussionViewModel.getUserDetails());
            intent.putExtra("discussion", selectedItem);
            mContext.startActivity(intent);
        }
    }

    /**
     * Utility method to format the discussion timestamp.
     *
     * @param timestamp The timestamp to be formatted.
     * @return The formatted timestamp as a string.
     */
    private String formatTimestamp(Date timestamp) {
        // Implement your desired timestamp formatting logic here
        return timestamp.toString();
    }
}