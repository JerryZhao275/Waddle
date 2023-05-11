package com.example.educationapplication.views.Fragment;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.educationapplication.R;
import com.example.educationapplication.databinding.FragmentDashboardBinding;
import com.example.educationapplication.databinding.LoginBinding;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;
import com.example.educationapplication.viewmodels.ListViewAdapter;
import com.example.educationapplication.viewmodels.LoginViewModel;
import com.example.educationapplication.viewmodels.UserViewModel;
import com.example.educationapplication.views.CreateClass;
import com.example.educationapplication.views.LoginView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

import dataObjects.CourseDto;
import dataObjects.CustomOnCompleteListener;
import dataObjects.TeacherUserDto;
import dataObjects.UserDto;

public class DashboardFragment extends Fragment implements View.OnClickListener{
    private Animation rotateOpen, rotateClose, toBottom, fromBottom;
    private ArrayList<CourseDto> courses;
    private RecyclerView myRecyclerView;

    Boolean isOpen = false;

    Toolbar bg;
    Button join;
    EditText codeEntered;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courses = new ArrayList<>();
        courses.add(new CourseDto(1100, "COMP1100", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(1110, "COMP1110", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(2100, "COMP2100", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(2300, "COMP2300", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(1710, "COMP1710", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(2420, "COMP2420", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(1600, "COMP1600", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(1730, "COMP1730", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(1620, "COMP2620", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(1005, "MATH1005", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(3620, "COMP3620", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(3300, "COMP3300", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(2310, "COMP2310", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
        courses.add(new CourseDto(3310, "COMP3310", new TeacherUserDto("u1", "A", "B", "teach", "example@anu.edu.au", "n/a", 30, "ANU")));
    }

    View view;
    FloatingActionButton addClass, createClass, joinClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        FragmentDashboardBinding fragBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container,false);
        fragBinding.setViewModel(new UserViewModel());
        view = fragBinding.getRoot();
        myRecyclerView = (RecyclerView) view.findViewById(R.id.courseList);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(),courses);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(adapter);
        addClass = view.findViewById(R.id.addClassButton);
        createClass = view.findViewById(R.id.createBtn);
        joinClass = view.findViewById(R.id.joinBtn);
        addClass.setOnClickListener(this);
        createClass.setOnClickListener(this);
        joinClass.setOnClickListener(this);

        bg = view.findViewById(R.id.dimbackground);
        join = view.findViewById(R.id.joinClassByCode);
        codeEntered = view.findViewById(R.id.classCodeTextBox);

        return view;
    }

    @Override
    public void onClick(View view) {
        rotateOpen = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.to_bottom_anim);

        if (view.getId() == R.id.addClassButton && bg.getVisibility() == View.VISIBLE) {
            bg.setVisibility(View.INVISIBLE);
            join.setVisibility(View.INVISIBLE);
            codeEntered.setVisibility(View.INVISIBLE);
            addClass.startAnimation(rotateOpen);
            isOpen = false;
        }
        else if (view.getId() == R.id.addClassButton && isOpen) {
            createClass.startAnimation(fromBottom);
            joinClass.startAnimation(fromBottom);
            addClass.startAnimation(rotateOpen);
            createClass.setClickable(false);
            joinClass.setClickable(false);
            isOpen = false;
        }
        else if (view.getId() == R.id.addClassButton && !isOpen) {
            createClass.startAnimation(toBottom);
            joinClass.startAnimation(toBottom);
            addClass.startAnimation(rotateClose);
            createClass.setClickable(true);
            joinClass.setClickable(true);
            isOpen = true;
        }
        else if (view.getId() == R.id.joinBtn) {
            createClass.startAnimation(fromBottom);
            joinClass.startAnimation(fromBottom);
            createClass.setClickable(false);
            joinClass.setClickable(false);
            bg.setVisibility(View.VISIBLE);
            join.setVisibility(View.VISIBLE);
            codeEntered.setVisibility(View.VISIBLE);
        }
        else if (view.getId() == R.id.createBtn) {
            Intent intent = new Intent(getActivity(), CreateClass.class);
            startActivity(intent);
            createClass.startAnimation(fromBottom);
            joinClass.startAnimation(fromBottom);
            createClass.setClickable(false);
            joinClass.setClickable(false);
            addClass.startAnimation(rotateOpen);
            isOpen = false;
        }
    }
}