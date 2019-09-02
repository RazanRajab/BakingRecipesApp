package com.example.bakingrceipesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.bakingrceipesapp.fragments.SelectStepFragment;
import com.example.bakingrceipesapp.fragments.StepDetailsFragment;

public class StepDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);

        StepDetailsFragment f = new StepDetailsFragment();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.step_details_container, f)
                .commit();
    }
}
