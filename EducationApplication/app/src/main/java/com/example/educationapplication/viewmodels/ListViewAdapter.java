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
    private List<String> namesList;
    private ArrayList<String> arraylist;

    public ListViewAdapter(Context context, List<String> namesList) {
        mContext = context;
        this.namesList = namesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(namesList);
    }

    public static class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return namesList.size();
    }

    @Override
    public String getItem(int position) {
        return namesList.get(position);
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
        holder.name.setText(namesList.get(position));
        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        namesList.clear();
        if (charText.length() == 0) {
            namesList.addAll(arraylist);
        }
        else {
            for (String listNames : arraylist) {
                if (listNames.toLowerCase(Locale.getDefault()).contains(charText)) {
                    namesList.add(listNames);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void updateData(String[] newData) {
        arraylist = new ArrayList<>(Arrays.asList(newData));
        notifyDataSetChanged();
    }

    public void displaySearch() {
        //TODO function will be implemented once merge performed and database accessible.
    }

}