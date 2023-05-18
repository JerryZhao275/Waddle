package com.example.educationapplication.viewmodels;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.educationapplication.R;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import com.example.educationapplication.search.Exp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import dataObjects.CourseDto;
import dataObjects.CustomOnCompleteListener;
import dataObjects.UserDto;

public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private final WaddleDatabaseConfiguration config;
    private final WaddleDatabaseServiceClient databaseServiceClient;
    List<UserDto> users = new ArrayList<>();
    List<CourseDto> courses = new ArrayList<>();
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

    /**
     * Get the display list of items.
     *
     * @return The display list as an array of strings.
     */
    public String[] getDisplayList() {
        return displayList.toArray(new String[0]);
    }

    /**
     * Filter the user list based on the provided expression.
     *
     * @param expression The expression to filter the user list.
     * @param listener   The listener to be called when the filtering operation is complete.
     */
    public void filterUserList(Exp expression, CustomOnCompleteListener listener) {
        databaseServiceClient.fetchAllUsersForSearch(expression, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                users = databaseServiceClient.getQueryUsers();
                List<String> userNames = new ArrayList<>();
                for (UserDto user : users) {
                    userNames.add(user.getUserFirstName() + " " + user.getUserLastName());
                }
                displayList = userNames;
                notifyDataSetChanged();
                listener.onComplete();
            }
        });
    }

    /**
     * Filter the course list based on the provided expression.
     *
     * @param expression The expression to filter the course list.
     * @param listener   The listener to be called when the filtering operation is complete.
     */
    public void filterCourseList(Exp expression, CustomOnCompleteListener listener) {
        databaseServiceClient.fetchAllCoursesForSearch(expression, new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                courses = databaseServiceClient.getQueryCourses();
                List<String> courseNames = new ArrayList<>();
                for (CourseDto course : courses) {
                    courseNames.add(course.getCourseDescription());
                }
                displayList = courseNames;
                notifyDataSetChanged();
                listener.onComplete();
            }
        });
    }

    public static class ViewHolder {
        TextView name;
    }

    //Return size of the list
    @Override
    public int getCount() {
        return displayList.size();
    }

    //Return the item at the position given in the list
    @Override
    public String getItem(int position) {
        return displayList.get(position);
    }

    //Return the item ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_people, null);

            holder.name = view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(displayList.get(position));
        return view;
    }

    /**
     * Filter the display list based on the provided search query.
     *
     * @param charText The search query to filter the display list.
     */
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        List<String> filteredList = new ArrayList<>();
        if (charText.length() == 0) {
            filteredList.addAll(displayList);
        } else {
            for (String listNames : displayList) {
                if (listNames.toLowerCase(Locale.getDefault()).contains(charText)) {
                    filteredList.add(listNames);
                }
            }
        }
        displayList = filteredList;
        notifyDataSetChanged();
    }

    /**
     * Update the data displayed in the adapter with new data.
     *
     * @param newData The new data to be displayed.
     */
    public void updateData(String[] newData) {
        displayList = Arrays.asList(newData);
        notifyDataSetChanged();
    }

    /**
     * Display the people in the list.
     */
    public void displayPeople() {
        displayList = List.of(namesList);
    }

    /**
     * Display the classes in the list.
     */
    public void displayClasses() {
        displayList = List.of(classesList);
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public List<CourseDto> getCourses() {
        return courses;
    }

}