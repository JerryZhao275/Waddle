package com.example.educationapplication.viewmodels;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.educationapplication.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private ArrayList<String> arraylist;

    //REPLACE WITH DB LISTS
    String[] namesList = {"Jerry Zhao", "Karthik Vemireddy", "Matthew Richards",
            "Ryan Yoon", "Michael Ostapenko", "Bernado Nunes"};
    String[] classesList = {"COMP2100", "COMP2420", "COMP2620", "COMP2300", "COMP1140"};
    String[] quizzesList = {"COMP2100 quiz 1", "COMP2420 quiz 3", "COMP2620 quiz 2", "COMP2300 quiz 1", "COMP1140 quiz 2"};

    private List<String> displayList = List.of(namesList);

    public ListViewAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(displayList);
    }

    public String[] getDisplayList() {
        return displayList.toArray(new String[0]);
    }

    public static class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return displayList.size();
    }

    @Override
    public String getItem(int position) {
        return displayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            //Change list_view_items to some other .xml resource for classes and quizzes

            holder.name = view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(displayList.get(position));
        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        List<String> filteredList = new ArrayList<>();
        if (charText.length() == 0) {
            filteredList.addAll(arraylist);
        }
        else {
            for (String listNames : arraylist) {
                if (listNames.toLowerCase(Locale.getDefault()).contains(charText)) {
                    filteredList.add(listNames);
                }
            }
        }
        displayList = filteredList;
        notifyDataSetChanged();
    }

    public void updateData(String[] newData) {
        arraylist = new ArrayList<>(Arrays.asList(newData));
        notifyDataSetChanged();
    }

    public void displayPeople() {
        displayList = List.of(namesList);

    }
    public void displayClasses() {
        displayList = List.of(classesList);
    }
    public void displayQuizzes() {
        displayList = List.of(quizzesList);
    }
}