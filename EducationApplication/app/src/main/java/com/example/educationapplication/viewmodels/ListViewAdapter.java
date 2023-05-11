package com.example.educationapplication.viewmodels;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.educationapplication.R;
import com.example.educationapplication.integration.database.FirebaseWaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import com.example.educationapplication.search.Exp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import dataObjects.CustomOnCompleteListener;
import dataObjects.UserDto;

public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;

    //REPLACE WITH DB LISTS
    private final WaddleDatabaseConfiguration config;
    private final WaddleDatabaseServiceClient databaseServiceClient;
    List<UserDto> users = new ArrayList<>();
    String[] namesList = {"Jerry Zhao", "Karthik Vemireddy", "Matthew Richards",
            "Ryan Yoon", "Michael Ostapenko", "Bernado Nunes"};
    String[] classesList = {"COMP2100", "COMP2420", "COMP2620", "COMP2300", "COMP1140"};
    private List<String> displayList = List.of(namesList);

    public ListViewAdapter(Context context) {
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        mContext = context;
        inflater = LayoutInflater.from(mContext);

    }

    public String[] getDisplayList() {
        return displayList.toArray(new String[0]);
    }

    public void filterUserList(Exp expression) {
        databaseServiceClient.fetchAllUsersForSearch(expression, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                users = databaseServiceClient.getQueryUsers();
                List<String> userNames = new ArrayList<>();
                for(UserDto user: users){
                    userNames.add(user.getUserFirstName()+" "+user.getUserLastName());
                }
                System.out.println(userNames);
                displayList = userNames;
                notifyDataSetChanged();
            }
        });
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
            filteredList.addAll(displayList);
        }
        else {
            for (String listNames : displayList) {
                if (listNames.toLowerCase(Locale.getDefault()).contains(charText)) {
                    filteredList.add(listNames);
                }
            }
        }
        displayList = filteredList;
        notifyDataSetChanged();
    }

    public void updateData(String[] newData) {
        displayList = Arrays.asList(newData);
        notifyDataSetChanged();
    }

    public void displayPeople() {
        displayList = List.of(namesList);
        
    }
    public void displayClasses() {
        displayList = List.of(classesList);
    }
}