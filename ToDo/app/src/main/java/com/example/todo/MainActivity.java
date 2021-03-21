package com.example.todo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
    }

    private final List<TaskFilter> myTaskFilters = new ArrayList<>();
    private TaskFilterAdapter myFilterAdapter;
}