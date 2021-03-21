package com.example.todo.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
public class Task {

    public Task(int myId, String myTaskName, int myPomodoroQuantity, boolean myIsCompleted) {
        this.myId = myId;
        this.myTaskName = myTaskName;
        this.myPomodoroQuantity = myPomodoroQuantity;
        //this.myDueDate = myDueDate.toString();
        this.myIsCompleted = myIsCompleted;
    }

    public int getMyId() {
        return myId;
    }

    public void setMyId(int myId) {
        this.myId = myId;
    }

    public String getMyTaskName() {
        return myTaskName;
    }

    public void setMyTaskName(String myTaskName) {
        this.myTaskName = myTaskName;
    }

    public int getMyPomodoroQuantity() {
        return myPomodoroQuantity;
    }

    public void setMyPomodoroQuantity(int myPomodoroQuantity) {
        this.myPomodoroQuantity = myPomodoroQuantity;
    }

    public boolean isMyIsCompleted() {
        return myIsCompleted;
    }

    public void setMyIsCompleted(boolean myIsCompleted) {
        this.myIsCompleted = myIsCompleted;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int myId;

    @ColumnInfo(name = "taskName")
    private String myTaskName;

    @ColumnInfo(name = "pomodoroQuantity")
    private int myPomodoroQuantity;

    //@ColumnInfo(name = "dueDate")
    //private Date myDueDate;

    //@ColumnInfo(name = "dueDate")
    //private String myDueDate;

    @ColumnInfo(name = "isCompleted")
    private boolean myIsCompleted;
}
