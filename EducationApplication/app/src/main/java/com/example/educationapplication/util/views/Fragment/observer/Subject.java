package com.example.educationapplication.util.views.Fragment.observer;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyAllObservers(String courseName, String messageType);

    void detachAll();
}
