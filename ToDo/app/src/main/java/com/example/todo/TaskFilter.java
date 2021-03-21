package com.example.todo;

public class TaskFilter {

    public TaskFilter(String taskFilterName) {
        this.myTaskFilterName = taskFilterName;
        this.myNumberOfHour = 0;
        this.myNumberOfPomodoro = 0;
    }

    public TaskFilter(String taskFilterName, double numberOfHour, int numberOfPomodoro) {
        this.myTaskFilterName = taskFilterName;
        this.myNumberOfHour = numberOfHour;
        this.myNumberOfPomodoro = numberOfPomodoro;
    }

    public String getTaskFilterName() {
        return myTaskFilterName;
    }

    public void setTaskFilterName(String myTaskFilterName) {
        this.myTaskFilterName = myTaskFilterName;
    }

    public double getNumberOfHour() {
        return myNumberOfHour;
    }

    public void setNumberOfHour(double myNumberOfHour) {
        this.myNumberOfHour = myNumberOfHour;
    }

    public int getNumberOfPomodoro() {
        return myNumberOfPomodoro;
    }

    public void setNumberOfPomodoro(int myNumberOfPomodoro) {
        this.myNumberOfPomodoro = myNumberOfPomodoro;
    }

    private String myTaskFilterName;
    private double myNumberOfHour;
    private int myNumberOfPomodoro;
}
