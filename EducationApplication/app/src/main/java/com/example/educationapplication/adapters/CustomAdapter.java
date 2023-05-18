package com.example.educationapplication.adapters;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educationapplication.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

private String[] localDataSet;

/**
 * Provide a reference to the type of views that you are using
 * (custom ViewHolder)
 */


public static class ViewHolder extends RecyclerView.ViewHolder {
    public View view;

    public ViewHolder(View v) {
        super(v);
        view = v;
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                System.out.println("print");
            }
        });
    }
}

    public CustomAdapter() {

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_messages, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("print");
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}

