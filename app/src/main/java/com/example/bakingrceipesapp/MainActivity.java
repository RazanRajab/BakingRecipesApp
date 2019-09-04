package com.example.bakingrceipesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bakingrceipesapp.adapters.RecipesAdapter;
import com.example.bakingrceipesapp.recipeAPI.Recipe;
import com.example.bakingrceipesapp.recipeAPI.recipeService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainRecyclerView)
    androidx.recyclerview.widget.RecyclerView recyclerView;
    private RecipesAdapter recipesAdapter;
    private ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTitle("Recipes");
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recipesAdapter = new RecipesAdapter(recipes);
        recyclerView.setAdapter(recipesAdapter);
        recipesAdapter.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int position = viewHolder.getAdapterPosition();

                boolean tablet = getResources().getBoolean(R.bool.isTablet);

                Gson gson = new Gson();
                Intent n;

                if (tablet) {
                    n = new Intent(getApplicationContext(), SelectAndViewStepDetailsActivity.class);
                } else {
                    n = new Intent(getApplicationContext(), SelectStepActivity.class);
                }
                n.putExtra(Recipe.class.getName(), gson.toJson(recipes.get(position)));
                startActivity(n);
            }
        });
        if (savedInstanceState == null) {
            getRecipes();
        }
    }

    public void getRecipes(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recipeService service = retrofit.create(recipeService.class);
        //Run the Request
        service.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> r = response.body();

                for (int i = 0; i < r.size(); i++) {
                    recipes.add(new Recipe(r.get(i).getId(), r.get(i).getName(),
                            r.get(i).getIngredients(), r.get(i).getSteps(),
                            r.get(i).getServings(), r.get(i).getImage()));
                }
                recipesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d("Log",t.getMessage());
            }
        });
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putParcelableArrayList("recipes", recipes);
        super.onSaveInstanceState(outState, outPersistentState);
    }

}
