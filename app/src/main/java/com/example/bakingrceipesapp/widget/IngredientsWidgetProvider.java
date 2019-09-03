package com.example.bakingrceipesapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.RemoteViews;

import com.example.bakingrceipesapp.R;
import com.example.bakingrceipesapp.recipeAPI.Ingredient;
import com.example.bakingrceipesapp.recipeAPI.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidgetProvider extends AppWidgetProvider {

    private static List<Ingredient> ingredients = new ArrayList<>();
    private static Recipe r;

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int[] appWidgetIds, Recipe recipe) {

        r = recipe;
        ingredients = r.getIngredients();

        for (int appWidgetId : appWidgetIds) {
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget_provider);
            Intent intent = new Intent(context, IngredientsListAdapter.class);
            views.setTextViewText(R.id.recipe_name, r.getName());
            views.setRemoteAdapter(R.id.ingredients_list, intent);
            ComponentName component = new ComponentName(context, IngredientsWidgetProvider.class);

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.ingredients_list);
            appWidgetManager.updateAppWidget(component, views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
       //DisplayIngredientsService.startActionUpdateWidgets(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static List<Ingredient> getIngredients(){
        return ingredients;
    }
}

