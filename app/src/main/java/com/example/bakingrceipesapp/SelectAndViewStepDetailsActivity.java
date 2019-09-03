package com.example.bakingrceipesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.bakingrceipesapp.fragments.SelectStepFragment;
import com.example.bakingrceipesapp.fragments.StepDetailsFragment;

public class SelectAndViewStepDetailsActivity extends AppCompatActivity implements SelectStepFragment.OnStepClickListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_and_view_step_details);

        SelectStepFragment f = new SelectStepFragment();
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.steps_container, f)
                .commit();

        StepDetailsFragment f2 = new StepDetailsFragment();
        fragmentManager.beginTransaction()
                .add(R.id.step_details_container, f2)
                .commit();
    }

    @Override
    public void onStepSelected(int position) {
        StepDetailsFragment f2 = new StepDetailsFragment();
        f2.setStepID(position);
        fragmentManager.beginTransaction()
                .replace(R.id.step_details_container, f2)
                .commit();
    }
}
