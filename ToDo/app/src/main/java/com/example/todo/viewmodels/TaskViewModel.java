package com.example.todo.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todo.entities.Task;
import com.example.todo.repositories.TaskRepository;

import java.util.Date;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    public TaskViewModel (Application application) {
        super(application);
        myRepository = new TaskRepository(application);
        myAllTasks = myRepository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() { return myAllTasks; }

    public LiveData<List<Task>> getAllTasksFromDate(Date fromDate){ return myRepository.getTaskFromDate(fromDate); }

    public void insert(Task task) { myRepository.insert(task); }

    public void deleteAll() {myRepository.deleteAll();}

    public void deleteTask(Task task) {myRepository.deleteTask(task);}

    public void updateTask(Task task){ myRepository.updateTask(task); }

    private final TaskRepository myRepository;
    private final LiveData<List<Task>> myAllTasks;

}
