package com.example.bakingrceipesapp.fragments;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingrceipesapp.widget.IngredientsWidgetProvider;
import com.example.bakingrceipesapp.R;
import com.example.bakingrceipesapp.adapters.IngredientsAdapter;
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
    @BindView(R.id.ingredients_add_widget)
    TextView addRecipeToWidget;
    private StepsAdapter stepsAdapter;
    private IngredientsAdapter ingredientsAdapter;
    private ArrayList<Step> steps = new ArrayList<>();
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private Recipe r;
    OnStepClickListener mCallback;

    public interface OnStepClickListener {
        void onStepSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepClickListener");
        }
    }

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
                mCallback.onStepSelected(position);
            }
        });

        addRecipeToWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getContext());
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                        new ComponentName(getContext(), IngredientsWidgetProvider.class));
                IngredientsWidgetProvider.updateAppWidget(getContext(), appWidgetManager, appWidgetIds, r);
                Toast.makeText(getContext(),"Added to Widget",Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }
}
