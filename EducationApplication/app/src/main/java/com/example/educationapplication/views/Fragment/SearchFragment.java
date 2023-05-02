package com.example.educationapplication.views.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.educationapplication.R;
import com.example.educationapplication.viewmodels.ListViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener, View.OnClickListener {

    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    ArrayList<String> arraylist = new ArrayList<>();
    Button people;
    Button classes;
    Button quizzes;

    String[] namesList = {"Jerry Zhao", "Karthik Vemireddy", "Matthew Richards",
            "Ryan Yoon", "Michael Ostapenko", "Bernado Nunes", "Bernardo Nunes Jr",
            "Bernado Nunes Sr", "Kanye", "Drake"};
    String[] classesList = {"COMP2100", "COMP2420", "COMP2620", "COMP2300", "COMP1140"};
    String[] quizzesList = {"COMP2100 quiz 1", "COMP2420 quiz 3", "COMP2620 quiz 2", "COMP2300 quiz 1", "COMP1140 quiz 2"};

    String[] displayList = namesList;

    View mView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search, container, false);
        people = mView.findViewById(R.id.peopleTab);
        people.setOnClickListener(this);
        classes = mView.findViewById(R.id.classesTab);
        classes.setOnClickListener(this);
        quizzes = mView.findViewById(R.id.quizzesTab);
        quizzes.setOnClickListener(this);

        list = mView.findViewById(R.id.listview);

        arraylist.addAll(Arrays.asList(displayList));

        adapter = new ListViewAdapter(requireContext(), arraylist);

        list.setAdapter(adapter);

        editsearch = mView.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        return mView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.peopleTab) {
            displayList = namesList;
        }
        else if (view.getId() == R.id.classesTab) {
            displayList = classesList;
        }
        else if (view.getId() == R.id.quizzesTab) {
            displayList = quizzesList;
        }
        adapter.updateData(displayList);
        arraylist.clear();
        arraylist.addAll(Arrays.asList(displayList));
        adapter.notifyDataSetChanged();
    }
}
