package com.example.todo.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todo.R;
import com.example.todo.entities.TaskFilter;
import com.example.todo.adapters.TaskFilterAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.todo.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        myTaskFilters.add(new TaskFilter("Tasks", 1.5, 2));
        myTaskFilters.add(new TaskFilter("Today", 3, 4));
        myTaskFilters.add(new TaskFilter("Tomorrow", 1, 1));
        myTaskFilters.add(new TaskFilter("Upcoming", 2.75, 2));

        RecyclerView recyclerView = findViewById(R.id.taskFilter_recycleView);
        myFilterAdapter = new TaskFilterAdapter(myTaskFilters);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myFilterAdapter);

        myMessageEditText = findViewById(R.id.editText_main);
        myReplyHeadTextView = findViewById(R.id.text_header_reply);
        myReplyTextView = findViewById(R.id.text_message_reply);
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        String message = myMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply =
                        data.getStringExtra(SecondActivity.EXTRA_REPLY);
                myReplyHeadTextView.setVisibility(View.VISIBLE);
                myReplyTextView.setText(reply);
                myReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void launchTaskActivity(View view) {
        Log.d(LOG_TAG, "Task list button clicked!");
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }

    private final List<TaskFilter> myTaskFilters = new ArrayList<>();
    private TaskFilterAdapter myFilterAdapter;

    private EditText myMessageEditText;
    private TextView myReplyHeadTextView;
    private TextView myReplyTextView;

}