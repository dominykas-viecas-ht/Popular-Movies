package com.personal.dominykasviecas.popularmovies.data.movie;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {

    @GET("movie/popular")
    Single<List<Movie>> getPopularMovies();

    @GET("movie/top_rated")
    Single<List<Movie>> getTopRatedMovies();

    @GET("movie/{id}")
    Single<Movie> getMovie(@NonNull @Path("id") String id);
}
