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
import com.example.educationapplication.search.Exp;
import com.example.educationapplication.search.SearchBarParser;
import com.example.educationapplication.search.SearchBarTokenizer;
import com.example.educationapplication.viewmodels.ListViewAdapter;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener, View.OnClickListener {
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    Button people;
    Button classes;
    View mView;
    View subView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search, container, false);
        people = mView.findViewById(R.id.peopleTab);
        subView = people;
        people.setOnClickListener(this);
        classes = mView.findViewById(R.id.classesTab);
        classes.setOnClickListener(this);
        list = mView.findViewById(R.id.listview);
        adapter = new ListViewAdapter(getContext());

        list.setAdapter(adapter);
        editsearch = mView.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
        return mView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        SearchBarTokenizer tokenizer;
        Exp expression;
        if(subView.getId() == R.id.peopleTab) {
            tokenizer = new SearchBarTokenizer(query, 'u');
            SearchBarParser parser = new SearchBarParser(tokenizer);
            expression = parser.parseName();
            adapter.filterUserList(expression);
        }
        else{
            tokenizer = new SearchBarTokenizer(query, 'c');
            SearchBarParser parser = new SearchBarParser(tokenizer);
            expression = parser.parseCourse();
            adapter.filterCourseList(expression);

        }
        while(expression.getCurrentValue()!=null){
            expression = expression.getNext();
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }

    @Override
    public void onClick(View view) {
        subView = view;
        if (view.getId() == R.id.peopleTab) {
            adapter.displayPeople();
        }
        else if (view.getId() == R.id.classesTab) {
            adapter.displayClasses();
        }
        editsearch.setQuery("", false);
        editsearch.clearFocus();
        adapter.updateData(adapter.getDisplayList());
        adapter.notifyDataSetChanged();
    }
}
