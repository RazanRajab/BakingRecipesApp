package com.example.bakingrceipesapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingrceipesapp.MainActivity;
import com.example.bakingrceipesapp.R;
import com.example.bakingrceipesapp.StepDetailsActivity;
import com.example.bakingrceipesapp.adapters.IngredientsAdapter;
import com.example.bakingrceipesapp.adapters.RecipesAdapter;
import com.example.bakingrceipesapp.adapters.StepsAdapter;
import com.example.bakingrceipesapp.recipeAPI.Ingredient;
import com.example.bakingrceipesapp.recipeAPI.Recipe;
import com.example.bakingrceipesapp.recipeAPI.Step;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectStepFragment extends Fragment {

    @BindView(R.id.ing_reycler_view)
    androidx.recyclerview.widget.RecyclerView ingredientsRecyclerView;
    @BindView(R.id.steps_recycler_view)
    androidx.recyclerview.widget.RecyclerView stepsRecyclerView;
    private StepsAdapter stepsAdapter;
    private IngredientsAdapter ingredientsAdapter;
    private ArrayList<Step> steps = new ArrayList<>();
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private Recipe r;

    public SelectStepFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.select_step_fragment,container,false);
        ButterKnife.bind(this, rootView);

        String Extra = getActivity().getIntent().getStringExtra(Recipe.class.getName());
        Gson gson = new Gson();
        r = gson.fromJson(Extra, Recipe.class);
        getActivity().setTitle(r.getName());

        ingredients = (ArrayList<Ingredient>) r.getIngredients();
        steps = (ArrayList<Step>) r.getSteps();

        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientsAdapter = new IngredientsAdapter(ingredients);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stepsAdapter = new StepsAdapter(steps);
        stepsRecyclerView.setAdapter(stepsAdapter);
        stepsAdapter.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int position = viewHolder.getAdapterPosition();
                Gson gson = new Gson();
                Intent n;
                n = new Intent(getContext(), StepDetailsActivity.class);
                n.putExtra(Recipe.class.getName(), gson.toJson(r));
                n.putExtra("position",position);
                startActivity(n);
            }
        });

        return rootView;
    }
}
