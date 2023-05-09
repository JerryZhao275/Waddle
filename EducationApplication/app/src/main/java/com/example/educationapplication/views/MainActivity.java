package com.example.educationapplication.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.educationapplication.views.Fragment.DashboardFragment;
import com.example.educationapplication.views.Fragment.MessagesFragment;
import com.example.educationapplication.views.Fragment.ProfileFragment;
import com.example.educationapplication.views.Fragment.SearchFragment;
import com.example.educationapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DashboardFragment newFragment = new DashboardFragment();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    private final NavigationBarView.OnItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.dashboard) {
            selectedFragment = new DashboardFragment();
        } else if (itemId == R.id.search) {
            selectedFragment = new SearchFragment();
        } else if (itemId == R.id.messages) {
            selectedFragment = new MessagesFragment();
        } else if (itemId == R.id.profile) {
            selectedFragment = new ProfileFragment();
        }
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        }
        return true;
    };

}