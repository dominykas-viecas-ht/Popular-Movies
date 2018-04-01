package com.personal.dominykasviecas.popularmovies.di;

import com.personal.dominykasviecas.popularmovies.feature.landing.MainActivity;
import com.personal.dominykasviecas.popularmovies.feature.moviedetail.MovieDetailActivity;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit retrofit();

    void inject(MainActivity mainActivity);
    void inject(MovieDetailActivity movieDetailActivity);
}
