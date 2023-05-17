package com.example.educationapplication.observer;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyAllObservers(String courseName);

    void detachAll();
}
