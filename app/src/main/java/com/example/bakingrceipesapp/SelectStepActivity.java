package com.example.bakingrceipesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.bakingrceipesapp.fragments.SelectStepFragment;

public class SelectStepActivity extends AppCompatActivity {

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
}
