package com.personal.dominykasviecas.popularmovies.data.movie;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;

public class TMDBMovieRepository implements MovieRepository {

    @NonNull
    private MovieService movieService;

    public TMDBMovieRepository(@NonNull MovieService movieService) {
        this.movieService = movieService;
    }

    @NonNull
    @Override
    public Single<List<Movie>> getPopularMovies() {
        return movieService.getPopularMovies();
    }

    @NonNull
    @Override
    public Single<List<Movie>> getTopRatedMovies() {
        return movieService.getTopRatedMovies();
    }

    @NonNull
    @Override
    public Single<Movie> getMovie(@NonNull String id) {
        return movieService.getMovie(id);
    }
}
