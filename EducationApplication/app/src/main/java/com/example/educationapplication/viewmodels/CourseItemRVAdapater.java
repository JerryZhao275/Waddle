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
import com.example.educationapplication.views.CoursePage;

import java.util.ArrayList;
import java.util.List;

import dataObjects.CourseDto;
import dataObjects.QuizDto;

public class CourseItemRVAdapater extends RecyclerView.Adapter<CourseItemRVAdapater.ViewHolder>  {
    private static Context mContext;
    private static List<QuizDto> mData = new ArrayList<QuizDto>();

    public CourseItemRVAdapater(Context mContext, List<QuizDto> mData) {
        this.mContext = mContext;
        this.mData = mData;

        //TESTING PURPOSES
        mData.add(new QuizDto(1, "Test Quiz 1", 1100));
        mData.add(new QuizDto(2, "Test Quiz 1", 1110));
        mData.add(new QuizDto(3, "Test Quiz 1", 2100));
        mData.add(new QuizDto(4, "Test Quiz 1", 2100));
        mData.add(new QuizDto(5, "Test Quiz 1", 2100));
        mData.add(new QuizDto(6, "Test Quiz 1", 2420));
    }

    // inflating layout (giving layout the look)

    @Override
    public CourseItemRVAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_within_course,parent,false);
        CourseItemRVAdapater.ViewHolder vHolder = new CourseItemRVAdapater.ViewHolder(v);

        return vHolder;
    }

    // assigning values to each rows as they come onto screen
    // based on position of recycler view
    @Override
    public void onBindViewHolder(CourseItemRVAdapater.ViewHolder holder, int position) {

        QuizDto quiz = mData.get(position);
      //  holder.bind(quiz);

        // Set item views based on your views and data model
        TextView textView = holder.courseName;
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

       /* public void bind(QuizDto course) {
            courseName.setText(course.getCourseName());
        }
*/
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            QuizDto selectedQuiz = mData.get(position);
            Intent intent = new Intent(mContext, CoursePage.class);
            intent.putExtra("course", (Parcelable) selectedQuiz);
            mContext.startActivity(intent);
        }
    }

}
