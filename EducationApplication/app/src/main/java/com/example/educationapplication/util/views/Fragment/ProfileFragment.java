package com.example.educationapplication.util.views.Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;
import com.example.educationapplication.R;
import com.example.educationapplication.databinding.FragmentProfileBinding;
import com.example.educationapplication.util.views.LoginView;
import com.example.educationapplication.viewmodels.UserViewModel;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    Toolbar bg;
    Toolbar whitebox;
    Button yes;
    Button no;
    TextView logoutText;

    ImageButton logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentProfileBinding fragBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container,false);
        fragBinding.setViewModel(new UserViewModel());
        view = fragBinding.getRoot();

        // Get references to UI elements
        bg = view.findViewById(R.id.logoutDim);
        whitebox = view.findViewById(R.id.logoutWhiteBox);
        yes = view.findViewById(R.id.yesLogout);
        no = view.findViewById(R.id.noLogout);
        logoutText = view.findViewById(R.id.logoutconfirmationText);
        logout = view.findViewById(R.id.logoutbutton);

        // Set click listeners for buttons
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        logout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        // Handle button clicks
        if (view.getId() == R.id.logoutbutton) {
            // Show logout confirmation dialog
            yes.setVisibility(View.VISIBLE);
            no.setVisibility(View.VISIBLE);
            logoutText.setVisibility(View.VISIBLE);
            whitebox.setVisibility(View.VISIBLE);
            bg.setVisibility(View.VISIBLE);
        }
        if (view.getId() == R.id.yesLogout) {
            // Perform logout
            requireActivity().finish();
            Intent intent = new Intent(getActivity(), LoginView.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.noLogout ) {
            // Hide logout confirmation dialog
            yes.setVisibility(View.INVISIBLE);
            no.setVisibility(View.INVISIBLE);
            logoutText.setVisibility(View.INVISIBLE);
            whitebox.setVisibility(View.INVISIBLE);
            bg.setVisibility(View.INVISIBLE);
        }
    }
}