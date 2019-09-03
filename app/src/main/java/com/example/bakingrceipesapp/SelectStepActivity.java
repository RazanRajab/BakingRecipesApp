package com.example.bakingrceipesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.bakingrceipesapp.fragments.SelectStepFragment;
import com.example.bakingrceipesapp.recipeAPI.Recipe;
import com.google.gson.Gson;

public class SelectStepActivity extends AppCompatActivity implements SelectStepFragment.OnStepClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_step);

        SelectStepFragment f = new SelectStepFragment();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.steps_container, f)
                .commit();
    }

    @Override
    public void onStepSelected(int position) {
        Intent n;
        n = new Intent(this, StepDetailsActivity.class);
        n.putExtra(Recipe.class.getName(), getIntent().getStringExtra(Recipe.class.getName()));
        n.putExtra("position", position);
        startActivity(n);
    }
}
