package com.example.educationapplication.views.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educationapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.Objects;

public class DashboardFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;
    TextView visibleText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        FloatingActionButton addClass = view.findViewById(R.id.addClassButton);
        addClass.setOnClickListener(this);
        visibleText = view.findViewById(R.id.visibilityTest);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addClassButton && visibleText.getVisibility() == View.INVISIBLE) {
            System.out.println("Set to visible");
            visibleText.setVisibility(View.VISIBLE);
        }
        else if (view.getId() == R.id.addClassButton && visibleText.getVisibility() == View.VISIBLE) {
            System.out.println("Set to invisible");
            visibleText.setVisibility(View.INVISIBLE);
        }
    }
}