package com.example.bakingrceipesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingrceipesapp.R;
import com.example.bakingrceipesapp.recipeAPI.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesAdapter extends
        RecyclerView.Adapter<RecipesAdapter.ViewHolder>{

     class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image) ImageView imageView;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.serving) TextView serving;

         private ViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(onRecipeClickListener);
            ButterKnife.bind(this, itemView);
        }
    }

    private List<Recipe> recipes;
    private View.OnClickListener onRecipeClickListener;
    private Context context;

    // Pass in the tasks array into the constructor
    public RecipesAdapter(List<Recipe> recipes ) {
        this.recipes = recipes;
    }

    public void setItemClickListener(View.OnClickListener clickListener) {
        onRecipeClickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View TaskView = inflater.inflate(R.layout.recipe_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(TaskView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe r = recipes.get(position);
        holder.name.setText(r.getName());
        holder.serving.setText(" "+r.getServings()+" sreving");
        if(!r.getImage().equals("")) {
            Picasso.with(context)
                    .load(r.getImage())
                    .placeholder(R.drawable.ic_cake).error(R.drawable.ic_broken_image)
                    .into(holder.imageView);
        }
        else {
            holder.imageView.setImageResource(R.drawable.ic_cake);
        }
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
