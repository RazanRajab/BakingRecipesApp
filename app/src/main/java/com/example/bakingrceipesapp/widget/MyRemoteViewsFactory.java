package com.example.bakingrceipesapp.widget;


import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.bakingrceipesapp.R;
import com.example.bakingrceipesapp.recipeAPI.Ingredient;

import java.util.List;

public class MyRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private List<Ingredient> ingredients;

    public MyRemoteViewsFactory(Context context){
        this.context = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        ingredients = IngredientsWidgetProvider.getIngredients();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredient_card);
        Ingredient ing = ingredients.get(i);
        views.setTextViewText(R.id.ing_textV, ++i+". "+ing.getIngredient()
                +" ("+ing.getQuantity()+" "+ing.getMeasure()+")");
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
