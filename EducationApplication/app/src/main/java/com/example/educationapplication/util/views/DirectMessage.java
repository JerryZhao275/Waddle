package com.example.educationapplication.util.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.educationapplication.R;
import com.example.educationapplication.databinding.LoginBinding;
import com.example.educationapplication.viewmodels.LoginViewModel;

public class DirectMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direct_message_chat);

    }
}
