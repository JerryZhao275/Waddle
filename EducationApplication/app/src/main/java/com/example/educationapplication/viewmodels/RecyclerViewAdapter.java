package com.example.educationapplication.viewmodels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapplication.BR;
import com.example.educationapplication.R;

import java.util.ArrayList;
import java.util.List;

import dataObjects.CourseDto;
import dataObjects.TeacherUserDto;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private LayoutInflater layoutInflater;

    Context mContext;
    List<CourseDto> mData = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;

        //TESTING PURPOSES
        mData.add(new CourseDto(1100, "COMP1100", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
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
        mData.add(new CourseDto(3310, "COMP3310", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating layout (giving layout the look)
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_course,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // assigning values to each rows as they come onto screen
        // based on position of recycler view

        CourseDto contact = mData.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.tv_name;
        textView.setText(contact.getCourseName());
    }

    @Override
    public int getItemCount() {
        //number of items displayed
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // grabbing views from recycler view layout
        // basically onCreate method

        private TextView tv_name;
        private Button messageButton;
        public MyViewHolder(View item) {
            super(item);

            tv_name = itemView.findViewById(R.id.contact_name);
            messageButton = itemView.findViewById(R.id.message_button);
            messageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(messageButton.getContext(), "Clicked button at position: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

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

