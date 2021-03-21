package com.example.todo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.entities.TaskFilter;

import java.util.List;

public class TaskFilterAdapter extends RecyclerView.Adapter<TaskFilterAdapter.TaskFilterViewHolder> {

    public TaskFilterAdapter(List<TaskFilter> taskFilters){
        myTaskFilters = taskFilters;
    }

    @NonNull
    @Override
    public TaskFilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.taskfilter_item, parent, false);
        return new TaskFilterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskFilterViewHolder holder, int position) {
        TaskFilter model = myTaskFilters.get(position);

        holder.taskFilterName.setText(model.getTaskFilterName());
        holder.taskFilterHour.setText(String.format("%sh", model.getNumberOfHour()));
        holder.taskFilterPomodoro.setText(String.valueOf(model.getNumberOfPomodoro()));
        holder.linearLayout.setOnClickListener(view ->
                Toast.makeText(view.getContext(),"click on item: "+ model.getTaskFilterName(),Toast.LENGTH_LONG).show());
    }

    @Override
    public int getItemCount() {
        return myTaskFilters.size();
    }

    public final static class TaskFilterViewHolder extends RecyclerView.ViewHolder {
        //ImageView taskFilterImage;
        public TextView taskFilterName;
        TextView taskFilterHour;
        TextView taskFilterPomodoro;
        LinearLayout linearLayout;

        public TaskFilterViewHolder(View itemView) {
            super(itemView);
            taskFilterName = itemView.findViewById(R.id.taskFilter_textView);
            taskFilterHour = itemView.findViewById(R.id.filterHours_textView);
            taskFilterPomodoro = itemView.findViewById(R.id.filterPomodoro_textView);
            linearLayout = itemView.findViewById(R.id.taskFilter_linearLayout);
        }
    }

    private final List<TaskFilter> myTaskFilters;
}
