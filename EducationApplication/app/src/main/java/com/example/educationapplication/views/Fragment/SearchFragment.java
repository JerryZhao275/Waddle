package com.example.educationapplication.views.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.educationapplication.R;
import com.example.educationapplication.databinding.FragmentSearchBinding;
import com.example.educationapplication.search.Exp;
import com.example.educationapplication.search.SearchBarParser;
import com.example.educationapplication.search.SearchBarTokenizer;
import com.example.educationapplication.viewmodels.CourseRecyclerViewAdapter;
import com.example.educationapplication.viewmodels.ListViewAdapter;
import com.example.educationapplication.viewmodels.UserViewModel;
import com.example.educationapplication.viewmodels.UsersRecyclerViewAdapter;

import dataObjects.CustomOnCompleteListener;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener, View.OnClickListener {

    private RecyclerView myRecyclerView;
    ListViewAdapter adapter;
    SearchView editsearch;
    Button people;
    Button classes;
    View view;
    View subView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for the fragment
        FragmentSearchBinding fragBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container,false);
        fragBinding.setViewModel(new UserViewModel());
        view = fragBinding.getRoot();

        // Get references to UI elements
        people = view.findViewById(R.id.peopleTab);
        subView = people;
        people.setOnClickListener(this);
        classes = view.findViewById(R.id.classesTab);
        classes.setOnClickListener(this);
        adapter = new ListViewAdapter(getContext());
        myRecyclerView = view.findViewById(R.id.listview);

        editsearch = view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        SearchBarTokenizer tokenizer;
        Exp expression;

        if(subView.getId() == R.id.peopleTab) {
            // Parse the search query for user search
            tokenizer = new SearchBarTokenizer(query, 'u');
            SearchBarParser parser = new SearchBarParser(tokenizer);
            expression = parser.parseName();

            // Filter the user list based on the search expression
            adapter.filterUserList(expression, new CustomOnCompleteListener() {
                @Override
                public void onComplete() {
                    // Set up the user adapter and update the RecyclerView
                    UsersRecyclerViewAdapter userAdapter = new UsersRecyclerViewAdapter(getContext(), adapter.getUsers());
                    myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    myRecyclerView.setAdapter(userAdapter);
                }
            });
        }
        else {
            // Parse the search query for course search
            tokenizer = new SearchBarTokenizer(query, 'c');
            SearchBarParser parser = new SearchBarParser(tokenizer);
            expression = parser.parseCourse();

            // Filter the course list based on the search expression
            adapter.filterCourseList(expression, new CustomOnCompleteListener() {
                @Override
                public void onComplete() {
                    // Set up the course adapter and update the RecyclerView
                    CourseRecyclerViewAdapter userAdapter = new CourseRecyclerViewAdapter(getContext(), adapter.getCourses());
                    myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    myRecyclerView.setAdapter(userAdapter);
                }
            });
        }

        // Traverse the expression to reach the end
        while(expression.getCurrentValue()!=null){
            expression = expression.getNext();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // Filter the adapter based on the new search text
        adapter.filter(newText);
        return false;
    }

    @Override
    public void onClick(View view) {
        subView = view;
        if (view.getId() == R.id.peopleTab) {
            // Display people search results
            adapter.displayPeople();
        }
        else if (view.getId() == R.id.classesTab) {
            // Display classes search results
            adapter.displayClasses();
        }

        // Reset the search bar and update the adapter data
        editsearch.setQuery("", false);
        editsearch.clearFocus();
        adapter.updateData(adapter.getDisplayList());
        adapter.notifyDataSetChanged();
    }
}