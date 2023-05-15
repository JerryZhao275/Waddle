package com.example.educationapplication.views.Fragment;
import android.app.Activity;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;
import com.example.educationapplication.R;
import com.example.educationapplication.databinding.FragmentDashboardBinding;
import com.example.educationapplication.databinding.LoginBinding;
import com.example.educationapplication.viewmodels.LoginViewModel;
import com.example.educationapplication.viewmodels.RecyclerViewAdapter;
import com.example.educationapplication.viewmodels.UserViewModel;
import com.example.educationapplication.views.CreateClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import dataObjects.CourseDto;
import dataObjects.CustomOnCompleteListener;
import dataObjects.CustomOnCompleteListener;
import dataObjects.TeacherUserDto;

public class DashboardFragment extends Fragment implements View.OnClickListener{
    private Animation rotateOpen, rotateClose, toBottom, fromBottom;
    private RecyclerView myRecyclerView;
    Boolean isOpen = false;
    Toolbar bg;
    Button join;
    EditText codeEntered;

    List<CourseDto> mData = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    View view;
    FloatingActionButton addClass, createClass, joinClass;

    UserViewModel viewModel = new UserViewModel();

    boolean removeCreate = true;
    FragmentDashboardBinding fragBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        fragBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container,false);
        fragBinding.setViewModel(viewModel);

        view = fragBinding.getRoot();
        myRecyclerView = view.findViewById(R.id.courseList);
        fragBinding.getViewModel().fetchUserCourseDetails(new CustomOnCompleteListener() {
            @Override
            public void onComplete() {
                mData = fragBinding.getViewModel().getUserCourseDetails();
                System.out.println(mData.get(0).getCourseName());
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), mData);
                myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                myRecyclerView.setAdapter(adapter);
            }
        });
        addClass = view.findViewById(R.id.addClassButton);
        createClass = view.findViewById(R.id.createBtn);
        joinClass = view.findViewById(R.id.joinBtn);
        addClass.setOnClickListener(this);
        createClass.setOnClickListener(this);
        joinClass.setOnClickListener(this);
        bg = view.findViewById(R.id.dimbackground);
        join = view.findViewById(R.id.joinClassByCode);
        codeEntered = view.findViewById(R.id.classCodeTextBox);
        join.setOnClickListener(this);

        fragBinding.setExpandButton((c)-> {
            View.OnClickListener onClick = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rotateOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_open_anim);
                    rotateClose = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_close_anim);
                    fromBottom = AnimationUtils.loadAnimation(getActivity(), R.anim.from_bottom_anim);
                    toBottom = AnimationUtils.loadAnimation(getActivity(), R.anim.to_bottom_anim);
                    if(fragBinding.getViewModel().isStudent()) {removeCreate = true;}
                    else {removeCreate = false;}

                    if (removeCreate) {
                        if (view.getId() == R.id.addClassButton && bg.getVisibility() == View.VISIBLE) {
                            bg.setVisibility(View.INVISIBLE);
                            join.setVisibility(View.INVISIBLE);
                            codeEntered.setVisibility(View.INVISIBLE);
                            addClass.startAnimation(rotateOpen);
                            isOpen = false;
                        } else if (view.getId() == R.id.addClassButton && isOpen) {
                            joinClass.startAnimation(fromBottom);
                            addClass.startAnimation(rotateOpen);
                            joinClass.setClickable(false);
                            isOpen = false;
                        } else if (view.getId() == R.id.addClassButton && !isOpen) {
                            joinClass.startAnimation(toBottom);
                            addClass.startAnimation(rotateClose);
                            joinClass.setClickable(true);
                            isOpen = true;
                        } else if (view.getId() == R.id.joinBtn) {
                            joinClass.startAnimation(fromBottom);
                            joinClass.setClickable(false);
                            bg.setVisibility(View.VISIBLE);
                            join.setVisibility(View.VISIBLE);
                            codeEntered.setVisibility(View.VISIBLE);
                        }
                    }
                    else {
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
                            createClass.setClickable(false);
                            joinClass.startAnimation(fromBottom);
                            joinClass.setClickable(false);
                            bg.setVisibility(View.VISIBLE);
                            join.setVisibility(View.VISIBLE);
                            codeEntered.setVisibility(View.VISIBLE);
                        }
                        else if (view.getId() == R.id.createBtn) {
                            Intent intent = new Intent(getActivity(), CreateClass.class);
                            TeacherUserDto user = fragBinding.getViewModel().returnUserIfTeacher();
                            intent.putExtra("teacher", user);
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
            };
            addClass.setOnClickListener(onClick);
            onClick.onClick(addClass);
        });

        fragBinding.setJoinClass((code)-> {
            System.out.println("||||||||||||||");
            System.out.println(fragBinding.getViewModel().getFirstName());
            System.out.println(fragBinding.getViewModel().getCourses());
            fragBinding.getViewModel().setCourses(code);
            System.out.println(fragBinding.getViewModel().getCourses());
            System.out.println("||||||||||||||");
            codeEntered.setText("");
            bg.setVisibility(View.INVISIBLE);
            join.setVisibility(View.INVISIBLE);
            codeEntered.setVisibility(View.INVISIBLE);
            addClass.startAnimation(rotateOpen);
            isOpen = false;
            hideKeyboard(this);
        });
        fragBinding.executePendingBindings();
        return view;
    }

    @Override
    public void onClick(View view) {
        rotateOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(getActivity(), R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(getActivity(), R.anim.to_bottom_anim);

        if (!removeCreate) {
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
                createClass.setClickable(false);
                joinClass.startAnimation(fromBottom);
                joinClass.setClickable(false);
                bg.setVisibility(View.VISIBLE);
                join.setVisibility(View.VISIBLE);
                codeEntered.setVisibility(View.VISIBLE);
            }
            else if (view.getId() == R.id.createBtn) {
                Intent intent = new Intent(getActivity(), CreateClass.class);
                intent.putExtra("teacher", fragBinding.getViewModel().returnUserIfTeacher());
                startActivity(intent);
                createClass.startAnimation(fromBottom);
                joinClass.startAnimation(fromBottom);
                createClass.setClickable(false);
                joinClass.setClickable(false);
                addClass.startAnimation(rotateOpen);
                isOpen = false;
            }
        }
        else {
            if (view.getId() == R.id.addClassButton && bg.getVisibility() == View.VISIBLE) {
                bg.setVisibility(View.INVISIBLE);
                join.setVisibility(View.INVISIBLE);
                codeEntered.setVisibility(View.INVISIBLE);
                addClass.startAnimation(rotateOpen);
                isOpen = false;
            }
            else if (view.getId() == R.id.addClassButton && isOpen) {
                joinClass.startAnimation(fromBottom);
                addClass.startAnimation(rotateOpen);
                joinClass.setClickable(false);
                isOpen = false;
            }
            else if (view.getId() == R.id.addClassButton && !isOpen) {
                joinClass.startAnimation(toBottom);
                addClass.startAnimation(rotateClose);
                joinClass.setClickable(true);
                isOpen = true;
            }
            else if (view.getId() == R.id.joinBtn) {
                joinClass.startAnimation(fromBottom);
                joinClass.setClickable(false);
                bg.setVisibility(View.VISIBLE);
                join.setVisibility(View.VISIBLE);
                codeEntered.setVisibility(View.VISIBLE);
            }
        }
    }

    public static void hideKeyboard(Fragment fragment) {
        InputMethodManager imm = (InputMethodManager) fragment.requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = fragment.getView();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(fragment.requireContext());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}