package com.example.todo.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
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

    @Query("SELECT * from task_table ORDER BY dueDate DESC")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * from task_table WHERE dueDate >= :fromDate")
    LiveData<List<Task>> getTaskFromDate(Date fromDate);

    @Update
    void updateTask(Task task);
}
