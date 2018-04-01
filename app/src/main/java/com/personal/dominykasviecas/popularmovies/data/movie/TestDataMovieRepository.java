package com.personal.dominykasviecas.popularmovies.data.movie;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Single;

public class TestDataMovieRepository implements MovieRepository {

    @NonNull
    @Override
    public Single<List<Movie>> getPopularMovies() {
        return Single.just(popularMovies);
    }

    @NonNull
    @Override
    public Single<List<Movie>> getTopRatedMovies() {
        return Single.just(topRatedMovies);
    }

    @NonNull
    @Override
    public Single<Movie> getMovie(@NonNull String id) {
        return Single.just(movie);
    }

    public static final List<Movie> popularMovies = new ArrayList<Movie>() {{
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
    }};

    public static final List<Movie> topRatedMovies = new ArrayList<Movie>() {{
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
       new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
    }};

    public static final Movie movie = new Movie(0, "Star Wars: Episode IV - A New Hope", new Date(1521851694482L), "1.jpg", 8.6, "Star Wars");
}
