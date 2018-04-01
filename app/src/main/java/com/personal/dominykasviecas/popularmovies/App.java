package com.personal.dominykasviecas.popularmovies;

import android.app.Application;
import android.support.annotation.NonNull;

import com.personal.dominykasviecas.popularmovies.di.ApplicationComponent;
import com.personal.dominykasviecas.popularmovies.di.ApplicationModule;
import com.personal.dominykasviecas.popularmovies.di.DaggerApplicationComponent;

import timber.log.Timber;


public class App extends Application {
    
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }


    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
