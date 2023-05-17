package com.example.educationapplication.util.views.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.example.educationapplication.R;
import com.example.educationapplication.databinding.FragmentProfileBinding;
import com.example.educationapplication.databinding.FragmentSearchBinding;
import com.example.educationapplication.search.Exp;
import com.example.educationapplication.search.SearchBarParser;
import com.example.educationapplication.search.SearchBarTokenizer;
import com.example.educationapplication.viewmodels.ListViewAdapter;
import com.example.educationapplication.viewmodels.UserViewModel;
import com.example.educationapplication.util.views.LoginView;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener, View.OnClickListener {
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    Button people;
    Button classes;
    View view;
    View subView;
    Toolbar bg;
    Toolbar whitebox;
    Button yes;
    Button no;
    TextView confirmText;

    ImageButton test;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentSearchBinding fragBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container,false);
        fragBinding.setViewModel(new UserViewModel());
        view = fragBinding.getRoot();

        people = view.findViewById(R.id.peopleTab);
        subView = people;
        people.setOnClickListener(this);
        classes = view.findViewById(R.id.classesTab);
        classes.setOnClickListener(this);
        list = view.findViewById(R.id.listview);
        adapter = new ListViewAdapter(getContext());

        bg = view.findViewById(R.id.searchLogoutDim);
        whitebox = view.findViewById(R.id.confirmWhiteBox);
        yes = view.findViewById(R.id.searchConfirm);
        no = view.findViewById(R.id.searchCancel);
        confirmText = view.findViewById(R.id.confirmText);
        test = view.findViewById(R.id.temp);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        test.setOnClickListener(this);

        list.setAdapter(adapter);
        editsearch = view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
        return view;
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
        if (view.getId() == R.id.temp) {
            people.setVisibility(View.INVISIBLE);
            classes.setVisibility(View.INVISIBLE);
            yes.setVisibility(View.VISIBLE);
            no.setVisibility(View.VISIBLE);
            confirmText.setVisibility(View.VISIBLE);
            whitebox.setVisibility(View.VISIBLE);
            bg.setVisibility(View.VISIBLE);
        }
        if (view.getId() == R.id.searchConfirm) {
            //Do something to add class
        }
        else if (view.getId() == R.id.searchCancel ) {
            people.setVisibility(View.VISIBLE);
            classes.setVisibility(View.VISIBLE);
            yes.setVisibility(View.INVISIBLE);
            no.setVisibility(View.INVISIBLE);
            confirmText.setVisibility(View.INVISIBLE);
            whitebox.setVisibility(View.INVISIBLE);
            bg.setVisibility(View.INVISIBLE);
        }

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
