package com.example.bakingrceipesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingrceipesapp.R;
import com.example.bakingrceipesapp.recipeAPI.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends
        RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.step_id)
        TextView id;
        @BindView(R.id.step_name)
        TextView name;

        private ViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(onStepClickListener);
            ButterKnife.bind(this, itemView);
        }
    }

    private List<Step> steps;
    private View.OnClickListener onStepClickListener;
    private Context context;

    // Pass in the tasks array into the constructor
    public StepsAdapter(List<Step> steps ) {
        this.steps = steps;
    }

    public void setItemClickListener(View.OnClickListener clickListener) {
        onStepClickListener = clickListener;
    }

    @NonNull
    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View TaskView = inflater.inflate(R.layout.step_card, parent, false);

        // Return a new holder instance
        return new StepsAdapter.ViewHolder(TaskView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.ViewHolder holder, int position) {
        Step s = steps.get(position);
        holder.id.setText(s.getId()+"");
        holder.name.setText(s.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }
}
