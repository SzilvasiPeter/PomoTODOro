package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTasks.add(new Task("1st Task", 1, new Date(System.currentTimeMillis()), false));
        myTasks.add(new Task("2st Task", 2, new Date(System.currentTimeMillis()), false));
        myTasks.add(new Task("3st Task", 3, new Date(System.currentTimeMillis()), false));


        RecyclerView recyclerView = findViewById(R.id.tasks_recycleview);
        myAdapter = new TaskListAdapter(this, myTasks);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        EditText addEditText = (EditText) findViewById(R.id.addtask_editText);
        addEditText.setOnKeyListener(this);
    }

    @Override
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
    }

    private List<Task> myTasks = new ArrayList<>();
    private TaskListAdapter myAdapter;
}