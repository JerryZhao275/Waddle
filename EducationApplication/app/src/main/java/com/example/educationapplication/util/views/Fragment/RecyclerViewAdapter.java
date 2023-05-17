package com.example.educationapplication.util.views.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapplication.R;

import java.util.ArrayList;
import java.util.List;

import dataObjects.CourseDto;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private LayoutInflater layoutInflater;

    Context mContext;
    List<CourseDto> mData;

    public RecyclerViewAdapter(Context mContext, ArrayList<CourseDto> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
}

