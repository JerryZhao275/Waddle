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

import java.util.ArrayList;
import java.util.List;

import com.example.educationapplication.search.dataObjects.CourseDto;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static Context mContext;
    private static List<CourseDto> mData = new ArrayList<CourseDto>();

    public RecyclerViewAdapter(Context mContext, List<CourseDto> mData) {
        this.mContext = mContext;
        this.mData = mData;

        //TESTING PURPOSES
        /*mData.add(new CourseDto(1100, "COMP1100", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(1110, "COMP1110", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(2100, "COMP2100", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(2300, "COMP2300", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(1710, "COMP1710", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(2420, "COMP2420", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(1600, "COMP1600", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(1730, "COMP1730", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(1620, "COMP2620", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(1005, "MATH1005", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(3620, "COMP3620", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(3300, "COMP3300", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(2310, "COMP2310", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        mData.add(new CourseDto(3310, "COMP3310", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));*/
    }

    // inflating layout (giving layout the look)

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course,parent,false);
        ViewHolder vHolder = new ViewHolder(v);

        return vHolder;
    }

    // assigning values to each rows as they come onto screen
    // based on position of recycler view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CourseDto course = mData.get(position);
        holder.bind(course);

        // Set item views based on your views and data model
        TextView textView = holder.courseName;
        textView.setText(course.getCourseName());
    }

    //number of items displayed
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // grabbing views from recycler view layout
    // Recycler view equivalent to onCreate method
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView courseName;

        public ViewHolder(View item) {
            super(item);
            courseName = item.findViewById(R.id.courseName);
            item.setOnClickListener(this);
        }

        public void bind(CourseDto course) {
            courseName.setText(course.getCourseName());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            CourseDto selectedCourse = mData.get(position);
            Intent intent = new Intent(mContext, com.example.educationapplication.views.CoursePage.class);
            intent.putExtra("course", selectedCourse);
            mContext.startActivity(intent);
        }
    }
    public static String listToString(List<CourseDto> list) {
        StringBuilder builder = new StringBuilder();
        for (CourseDto item : list) {
            builder.append(item.getCourseName()).append("\n");
        }
        return builder.toString();
    }
}

