package com.example.todo.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Delete
    void deleteTask(Task word);

    @Query("SELECT * from task_table LIMIT 1")
    Task[] getAnyTask();

    @Query("SELECT * from task_table ORDER BY taskName DESC")
    LiveData<List<Task>> getAllTasks();
}
