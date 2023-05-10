package com.example.educationapplication.views.Fragment;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.educationapplication.R;
import com.example.educationapplication.databinding.FragmentProfileBinding;
import com.example.educationapplication.viewmodels.UserViewModel;

public class ProfileFragment extends Fragment {
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

        return view;
    }
}