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
import com.example.educationapplication.util.views.CoursePage;
import java.util.ArrayList;
import java.util.List;
import com.example.educationapplication.search.dataObjects.*;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static Context mContext;
    private static List<CourseDto> mData = new ArrayList<CourseDto>();

    public RecyclerViewAdapter(Context mContext, List<CourseDto> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item_course layout for each item in the RecyclerView
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        ViewHolder vHolder = new ViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the course at the current position
        CourseDto course = mData.get(position);
        holder.bind(course);

        // Set the course name to the corresponding TextView
        TextView textView = holder.courseName;
        textView.setText(course.getCourseName());
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the RecyclerView
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView courseName;

        public ViewHolder(View item) {
            super(item);
            // Find the TextView for the course name in the item layout
            courseName = item.findViewById(R.id.courseName);
            item.setOnClickListener(this);
        }

        public void bind(CourseDto course) {
            // Bind the course data to the ViewHolder
            courseName.setText(course.getCourseName());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            // Get the selected course based on the position
            CourseDto selectedCourse = mData.get(position);
            // Create an intent to start the CoursePage activity
            Intent intent = new Intent(mContext, CoursePage.class);
            intent.putExtra("course", selectedCourse);
            mContext.startActivity(intent);
        }
    }

    /**
     * Converts a list of CourseDto objects to a string representation.
     * Each course name is separated by a newline character.
     *
     * @param list The list of CourseDto objects.
     * @return A string representation of the list.
     */
    public static String listToString(List<CourseDto> list) {
        StringBuilder builder = new StringBuilder();
        for (CourseDto item : list) {
            builder.append(item.getCourseName()).append("\n");
        }
        return builder.toString();
    }
}
