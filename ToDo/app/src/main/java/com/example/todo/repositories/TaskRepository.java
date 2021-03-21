package com.example.todo.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todo.entities.Task;
import com.example.todo.entities.TaskDao;

import java.util.List;

public class TaskRepository {

    public TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        myTaskDao = db.taskDao();
        myAllTasks = myTaskDao.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return myAllTasks;
    }

    public void insert (Task task) {
        new insertAsyncTask(myTaskDao).execute(task);
    }

    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {

        private final TaskDao myAsyncTaskDao;

        public insertAsyncTask(TaskDao dao) {
            myAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            myAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private final TaskDao myTaskDao;
    private final LiveData<List<Task>> myAllTasks;
}
