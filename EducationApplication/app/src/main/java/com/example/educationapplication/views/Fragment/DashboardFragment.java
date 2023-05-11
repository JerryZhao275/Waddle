package com.example.educationapplication.views.Fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educationapplication.R;
import com.example.educationapplication.viewmodels.ListViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import dataObjects.CourseDto;
import dataObjects.TeacherUserDto;

public class DashboardFragment extends Fragment implements View.OnClickListener{
    private Animation rotateOpen, rotateClose, toBottom, fromBottom;
    private ArrayList<CourseDto> courses;
    private RecyclerView myRecyclerView;

    Boolean isOpen = false;

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
    TextView visibleText;
    FloatingActionButton addClass, createClass, joinClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
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

        visibleText = view.findViewById(R.id.visibilityTest);
        return view;
    }

    @Override
    public void onClick(View view) {
        rotateOpen = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.to_bottom_anim);

        if (view.getId() == R.id.addClassButton && visibleText.getVisibility() == View.INVISIBLE) {
            visibleText.setVisibility(View.VISIBLE);
            createClass.startAnimation(toBottom);
            joinClass.startAnimation(toBottom);
            addClass.startAnimation(rotateClose);
            createClass.setClickable(true);
            joinClass.setClickable(true);
            isOpen = false;
        }
        else if (view.getId() == R.id.addClassButton && visibleText.getVisibility() == View.VISIBLE) {
            visibleText.setVisibility(View.INVISIBLE);
            createClass.startAnimation(fromBottom);
            joinClass.startAnimation(fromBottom);
            addClass.startAnimation(rotateOpen);
            createClass.setClickable(false);
            joinClass.setClickable(false);
            isOpen = true;

        }
        else if (view.getId() == R.id.joinBtn && visibleText.getVisibility() == View.VISIBLE) {
            Toast.makeText(getActivity().getApplicationContext(), "Test Join", Toast.LENGTH_SHORT).show();
        }
        else if (view.getId() == R.id.createBtn && visibleText.getVisibility() == View.VISIBLE) {
            Toast.makeText(getActivity().getApplicationContext(), "Test Create", Toast.LENGTH_SHORT).show();

        }
    }
}