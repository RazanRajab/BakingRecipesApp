package com.example.bakingrceipesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingrceipesapp.R;
import com.example.bakingrceipesapp.recipeAPI.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsAdapter extends
        RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ing_textV)
        TextView text;

        private ViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(this);
            ButterKnife.bind(this, itemView);
        }
    }

    private List<Ingredient> ingredients;
    private Context context;

    // Pass in the tasks array into the constructor
    public IngredientsAdapter(List<Ingredient> steps ) {
        this.ingredients = steps;
    }

    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View TaskView = inflater.inflate(R.layout.ingredient_card, parent, false);

        // Return a new holder instance
        return new IngredientsAdapter.ViewHolder(TaskView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {
        Ingredient i = ingredients.get(position);
        holder.text.setText(++position+". "+i.getIngredient()+" ("+i.getQuantity()+" "+i.getMeasure()+")");
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}
