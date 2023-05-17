package com.example.educationapplication.util.views.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.educationapplication.R;
import com.example.educationapplication.adapters.CustomAdapter;
import com.example.educationapplication.databinding.FragmentMessagesBinding;
import com.example.educationapplication.util.views.DirectMessage;
import com.example.educationapplication.viewmodels.MessagesViewModel;

import java.util.List;

public class MessagesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentMessagesBinding messagesBinding = DataBindingUtil.inflate
                (inflater, R.layout.fragment_messages, container,false);
        //ListView view = findViewById(R.id.cardView);
        messagesBinding.setViewModel(new MessagesViewModel());
        View view = messagesBinding.getRoot();

        ListView list = (ListView) view.findViewById(R.id.messagelist);

        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, messagesBinding.getViewModel().getMessages());
        list.setAdapter(adapter);
        messagesBinding.getViewModel().setAdapter(adapter);

        messagesBinding.executePendingBindings();
        return view;
    }
}