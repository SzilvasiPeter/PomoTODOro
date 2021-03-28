package com.example.todo.repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todo.converters.DateConverter;
import com.example.todo.entities.Task;
import com.example.todo.entities.TaskDao;

import java.util.Date;

@Database(entities = {Task.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class TaskRoomDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    static TaskRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskRoomDatabase.class, "task_database")
                            // Wipes and rebuilds instead of migrating
                            .fallbackToDestructiveMigration()
                            .addCallback(myRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback myRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TaskDao myDao;
        String [] tasks = {"firstTask", "secondTask", "thirdTask"};

        public PopulateDbAsync(TaskRoomDatabase db) {
            myDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            myDao.deleteAll();

            if(myDao.getAnyTask().length < 1) {
                for (int i = 0; i <= tasks.length - 1; i++) {
                    // ID is 0, because it will be auto-generated
                    Task task = new Task(0, tasks[i], 0,  new Date(System.currentTimeMillis()), false);
                    myDao.insert(task);
                }
            }
            return null;
        }
    }

    private static TaskRoomDatabase INSTANCE;
}
