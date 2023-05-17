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
import com.example.educationapplication.views.ConfirmJoinCourse;
import com.example.educationapplication.views.UserPage;

import java.util.ArrayList;
import java.util.List;

import dataObjects.CourseDto;
import dataObjects.UserDto;

public class CourseRecyclerViewAdapter  extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ViewHolder>{
    private static Context mContext;
    private static List<CourseDto> mData = new ArrayList<CourseDto>();

    public CourseRecyclerViewAdapter(Context mContext, List<CourseDto> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    // inflating layout (giving layout the look)

    @Override
    public CourseRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_courses,parent,false);
        CourseRecyclerViewAdapter.ViewHolder vHolder = new CourseRecyclerViewAdapter.ViewHolder(v);

        return vHolder;
    }

    // assigning values to each rows as they come onto screen
    // based on position of recycler view
    @Override
    public void onBindViewHolder(CourseRecyclerViewAdapter.ViewHolder holder, int position) {
        CourseDto course = mData.get(position);
        holder.bind(course);

        // Set item views based on your views and data model
//        TextView textView = holder.courseName;
//        textView.setText(course.getCourseName());
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
            courseName = item.findViewById(R.id.name);
            item.setOnClickListener(this);
        }

        public void bind(CourseDto course) {
            courseName.setText(course.getCourseName());
        }

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
