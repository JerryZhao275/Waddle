package com.example.educationapplication.model;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapterModel {
    private List<String> namesList;
    private ArrayList<String> arraylist;

    public ListViewAdapterModel(List<String> namesList, ArrayList<String> arraylist) {
        this.namesList = namesList;
        this.arraylist.addAll(namesList);
    }

    public List<String> getNamesList() {
        return namesList;
    }

    public void setNamesList(List<String> namesList) {
        this.namesList = namesList;
    }

    public ArrayList<String> getArraylist() {
        return arraylist;
    }

    public void setArraylist(ArrayList<String> arraylist) {
        this.arraylist = arraylist;
    }
}
