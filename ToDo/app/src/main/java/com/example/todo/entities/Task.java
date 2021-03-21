package com.example.todo.entities;

import java.util.Date;

public class Task {

    public Task(String taskName){
        this.myTaskName = taskName;
        this.myPomodoroQuantity = 0;
        this.myDueDate = new Date(System.currentTimeMillis());
        this.myIsCompleted = false;
    }

    public Task(String taskName, int myPomodoroQuantity, Date myDueDate, boolean myIsCompleted) {
        this.myTaskName = taskName;
        this.myPomodoroQuantity = myPomodoroQuantity;
        this.myDueDate = myDueDate;
        this.myIsCompleted = myIsCompleted;
    }

    public String getTaskName() {
        return myTaskName;
    }

    public void setTaskName(String myTaskName) {
        this.myTaskName = myTaskName;
    }

    public int getPomodoroQuantity() {
        return myPomodoroQuantity;
    }

    public void setPomodoroQuantity(int myPomodoroQuantity) {
        this.myPomodoroQuantity = myPomodoroQuantity;
    }

    public Date getDueDate() {
        return myDueDate;
    }

    public void setDueDate(Date myDueDate) {
        this.myDueDate = myDueDate;
    }

    public boolean isCompleted() {
        return myIsCompleted;
    }

    public void setIsCompleted(boolean myIsCompleted) {
        this.myIsCompleted = myIsCompleted;
    }

    private String myTaskName;
    private int myPomodoroQuantity;
    private Date myDueDate;
    private boolean myIsCompleted;
}
