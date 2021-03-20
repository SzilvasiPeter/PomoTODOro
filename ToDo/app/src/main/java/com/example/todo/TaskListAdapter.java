package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    public TaskListAdapter(Context context, List<Task> taskList){
        myContext = context;
        myTaskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        View listItem= layoutInflater.inflate(R.layout.tasklist_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Task taskList = myTaskList.get(position);
        DateFormat dateFormat = new SimpleDateFormat("MM dd", Locale.getDefault());
        holder.taskName.setText(myTaskList.get(position).getTaskName());
        holder.dueDate.setText(dateFormat.format(myTaskList.get(position).getDueDate()));
        holder.linearLayout.setOnClickListener(view ->
                Toast.makeText(view.getContext(),"click on item: "+taskList.getTaskName(),Toast.LENGTH_LONG).show());
    }

    @Override
    public int getItemCount() {
        return myTaskList.size();
    }

    private List<Task> myTaskList;
    private Context myContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView taskName;
        public TextView dueDate;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.taskName = itemView.findViewById(R.id.taskname_textview);
            this.dueDate = itemView.findViewById(R.id.duedata_textview);
            linearLayout = itemView.findViewById(R.id.tasklist_linearlayout);
        }
    }
}
