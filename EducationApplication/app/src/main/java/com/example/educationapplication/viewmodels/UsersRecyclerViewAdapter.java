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
import com.example.educationapplication.views.UserPage;
import java.util.ArrayList;
import java.util.List;
import dataObjects.UserDto;

public class UsersRecyclerViewAdapter extends RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>{
    private static Context mContext;
    private static List<UserDto> mData = new ArrayList<UserDto>();

    public UsersRecyclerViewAdapter(Context mContext, List<UserDto> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    // inflating layout (giving layout the look)

    @Override
    public UsersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_people,parent,false);
        UsersRecyclerViewAdapter.ViewHolder vHolder = new UsersRecyclerViewAdapter.ViewHolder(v);

        return vHolder;
    }

    // assigning values to each rows as they come onto screen
    // based on position of recycler view
    @Override
    public void onBindViewHolder(UsersRecyclerViewAdapter.ViewHolder holder, int position) {

        UserDto user = mData.get(position);
        holder.bind(user);

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
        //private TextView userName;
        private TextView userName;
        public ViewHolder(View item) {
            super(item);
            userName = item.findViewById(R.id.name);
            item.setOnClickListener(this);

        }

        public void bind(UserDto user) {
            userName.setText(user.getUserFirstName() + " " + user.getUserLastName());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            UserDto selectedUser = mData.get(position);
            Intent intent = new Intent(mContext, UserPage.class);
            intent.putExtra("user", selectedUser);
            mContext.startActivity(intent);
        }
    }
}
