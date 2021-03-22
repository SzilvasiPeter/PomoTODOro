package com.example.todo.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.entities.Task;
import com.example.todo.adapters.TaskListAdapter;
import com.example.todo.viewmodels.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);

        /*myTasks.add(new Task("1st Task", 1, new Date(System.currentTimeMillis()), false));
        myTasks.add(new Task("2st Task", 2, new Date(System.currentTimeMillis()), false));
        myTasks.add(new Task("3st Task", 3, new Date(System.currentTimeMillis()), false));*/

        RecyclerView recyclerView = findViewById(R.id.task_recycleViewer);
        myAdapter = new TaskListAdapter(this, myTasks);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        // Get a new or existing ViewModel from the ViewModelProvider.
        TaskViewModel myTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);

        myTaskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> tasks) {
                // Update the cached copy of the tasks in the adapter.
                myAdapter.setTasks(tasks);
            }
        });

        myTaskEditText = findViewById(R.id.task_editText);

        final Button addButton = findViewById(R.id.save_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Task word = new Task(0, myTaskEditText.getText().toString(), 0, false);
                myTaskViewModel.insert(word);
            }
        });

        final Button clearAllButton = findViewById(R.id.clearAll_button);
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Clearing the data...", Toast.LENGTH_SHORT).show();

                // Delete the existing data
                myTaskViewModel.deleteAll();
            }
        });

        // Add the functionality to swipe items in the
        // recycler view to delete that item
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    // We are not implementing onMove() in this app
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    // When the use swipes a word,
                    // delete that word from the database.
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Task myWord = myAdapter.getTaskAtPosition(position);
                        Toast.makeText(TaskActivity.this,
                                 "Task is deleted on swipe:  " +
                                        myWord.getMyTaskName(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        myTaskViewModel.deleteTask(myWord);
                    }
                });
        // Attach the item touch helper to the recycler view
        helper.attachToRecyclerView(recyclerView);

        //EditText addEditText = (EditText) findViewById(R.id.addtask_editText);
        //addEditText.setOnKeyListener(this);
    }

    /*@Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {

        TextView responseText = findViewById(R.id.simpleTask_textview);
        EditText myEditText = (EditText) view;

        if (keyCode == EditorInfo.IME_ACTION_SEARCH ||
                keyCode == EditorInfo.IME_ACTION_DONE ||
                event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

            responseText.setText(myEditText.getText());

            myTasks.add(new Task(myEditText.getText().toString(), 0, new Date(System.currentTimeMillis()), false));
            myAdapter.notifyItemInserted(myTasks.size() - 1);
            myEditText.setText("");
            return true;
        }
        return false;
    }*/

    private final List<Task> myTasks = new ArrayList<>();
    private TaskListAdapter myAdapter;

    private  EditText myTaskEditText;

}
