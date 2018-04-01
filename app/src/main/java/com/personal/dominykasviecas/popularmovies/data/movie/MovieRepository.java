package com.personal.dominykasviecas.popularmovies.data.movie;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;

public interface MovieRepository {
    @NonNull Single<List<Movie>> getPopularMovies();
    @NonNull Single<List<Movie>> getTopRatedMovies();
    @NonNull Single<Movie> getMovie(@NonNull String id);
}
