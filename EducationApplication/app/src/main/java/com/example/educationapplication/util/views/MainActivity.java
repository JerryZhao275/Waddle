package com.example.educationapplication.util.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.educationapplication.util.views.Fragment.ProfileFragment;
import com.example.educationapplication.util.views.Fragment.SearchFragment;
import com.example.educationapplication.util.views.Fragment.DashboardFragment;
import com.example.educationapplication.util.views.Fragment.MessagesFragment;
import com.example.educationapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout file as the content view for the activity
        setContentView(R.layout.activity_main);

        // Get a reference to the BottomNavigationView
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Set a listener for item selection in the bottom navigation view
        bottomNav.setOnItemSelectedListener(navListener);

        // Replace the fragment container with the initial DashboardFragment
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DashboardFragment newFragment = new DashboardFragment();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private final NavigationBarView.OnItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        int itemId = item.getItemId();

        // Determine which fragment to show based on the selected item in the bottom navigation view
        if (itemId == R.id.dashboard) {
            selectedFragment = new DashboardFragment();
        } else if (itemId == R.id.search) {
            selectedFragment = new SearchFragment();
        } else if (itemId == R.id.messages) {
            selectedFragment = new MessagesFragment();
        } else if (itemId == R.id.profile) {
            selectedFragment = new ProfileFragment();
        }

        // Replace the current fragment with the selected fragment
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        }

        return true;
    };
}
