package com.tnninc.writgear.di;

import android.app.Application;

import com.tnninc.writgear.di.components.AppComponent;
import com.tnninc.writgear.di.components.DaggerAppComponent;
import com.tnninc.writgear.di.modules.ModelModule;


public class App extends Application {
    private static AppComponent component;

    public static AppComponent getAppComponent(){
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent(){
        return DaggerAppComponent.builder()
                .modelModule(new ModelModule(this))
                .build();
    }
}
