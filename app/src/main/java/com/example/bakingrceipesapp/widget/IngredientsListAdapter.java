package com.example.bakingrceipesapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class IngredientsListAdapter extends RemoteViewsService {

    @Override
    public MyRemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyRemoteViewsFactory(this.getApplicationContext());
    }

}
