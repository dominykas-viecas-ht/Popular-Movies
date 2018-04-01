package com.personal.dominykasviecas.popularmovies.di;

import com.personal.dominykasviecas.popularmovies.data.movie.MovieRepository;
import com.personal.dominykasviecas.popularmovies.data.movie.MovieService;
import com.personal.dominykasviecas.popularmovies.data.movie.TMDBMovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class TMDBModule {

    @Provides
    @Singleton
    MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Provides
    @Singleton
    MovieRepository provideMovieRepository(MovieService movieService) {
        return new TMDBMovieRepository(movieService);
    }
}
