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
import com.example.educationapplication.search.dataObjects.UserDto;
import com.example.educationapplication.util.views.UserPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Recycler View Adapter for user search
 */
public class UsersRecyclerViewAdapter extends RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder> {
    private static Context mContext;
    private static List<UserDto> mData = new ArrayList<>();

    public UsersRecyclerViewAdapter(Context mContext, List<UserDto> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    /**
     * Creates and returns a new ViewHolder by inflating the layout.
     *
     * @param parent   The parent ViewGroup.
     * @param viewType The view type of the new ViewHolder.
     * @return A new ViewHolder that holds the inflated layout.
     */
    @Override
    public UsersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_people, parent, false);
        UsersRecyclerViewAdapter.ViewHolder vHolder = new UsersRecyclerViewAdapter.ViewHolder(v);

        return vHolder;
    }

    /**
     * Binds the data to the views in each row.
     *
     * @param holder   The ViewHolder to bind data to.
     * @param position The position of the item in the RecyclerView.
     */
    @Override
    public void onBindViewHolder(UsersRecyclerViewAdapter.ViewHolder holder, int position) {
        UserDto user = mData.get(position);
        holder.bind(user);
    }

    /**
     * Returns the number of items in the RecyclerView.
     *
     * @return The number of items.
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * ViewHolder class representing each item in the RecyclerView.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView userName;

        /**
         * Constructor for the ViewHolder.
         *
         * @param item The item view for the ViewHolder.
         */
        public ViewHolder(View item) {
            super(item);
            userName = item.findViewById(R.id.name);
            item.setOnClickListener(this);
        }

        /**
         * Binds the data to the views in the ViewHolder.
         *
         * @param user The UserDto object containing the user information.
         */
        public void bind(UserDto user) {
            userName.setText(user.getUserFirstName() + " " + user.getUserLastName());
        }

        /**
         * Handles the click event when an item is clicked.
         *
         * @param view The clicked view.
         */
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
