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
import com.example.educationapplication.util.views.ConfirmJoinCourse;
import java.util.ArrayList;
import java.util.List;
import com.example.educationapplication.search.dataObjects.*;

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ViewHolder> {
    private static Context mContext;
    private static List<CourseDto> mData = new ArrayList<CourseDto>();

    public CourseRecyclerViewAdapter(Context mContext, List<CourseDto> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    /**
     * Inflate the layout for each item in the RecyclerView.
     *
     * @param parent   The parent ViewGroup.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @Override
    public CourseRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_courses, parent, false);
        CourseRecyclerViewAdapter.ViewHolder vHolder = new CourseRecyclerViewAdapter.ViewHolder(v);
        return vHolder;
    }

    /**
     * Bind data to the views in each row of the RecyclerView.
     *
     * @param holder   The ViewHolder to bind data to.
     * @param position The position of the item in the data set.
     */
    @Override
    public void onBindViewHolder(CourseRecyclerViewAdapter.ViewHolder holder, int position) {
        CourseDto course = mData.get(position);
        holder.bind(course);

        // Set item views based on your views and data model
        // TextView textView = holder.courseName;
        // textView.setText(course.getCourseName());
    }

    /**
     * Get the number of items in the data set.
     *
     * @return The number of items in the data set.
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * ViewHolder class for holding the views of each item in the RecyclerView.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView courseName;

        public ViewHolder(View item) {
            super(item);
            courseName = item.findViewById(R.id.name);
            item.setOnClickListener(this);
        }

        /**
         * Bind data to the views in the ViewHolder.
         *
         * @param course The CourseDto object containing the data to bind.
         */
        public void bind(CourseDto course) {
            courseName.setText(course.getCourseName());
        }

        /**
         * Handle the click event on the item view.
         *
         * @param view The clicked view.
         */
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            CourseDto selectedCourse = mData.get(position);
            Intent intent = new Intent(mContext, ConfirmJoinCourse.class);
            intent.putExtra("course", selectedCourse);
            mContext.startActivity(intent);
        }
    }
}
