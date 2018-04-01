package com.personal.dominykasviecas.popularmovies.feature.landing;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.personal.dominykasviecas.popularmovies.App;
import com.personal.dominykasviecas.popularmovies.R;
import com.personal.dominykasviecas.popularmovies.data.movie.Movie;
import com.personal.dominykasviecas.popularmovies.di.ViewModelFactory;
import com.personal.dominykasviecas.popularmovies.feature.moviedetail.MovieDetailActivity;
import com.personal.dominykasviecas.popularmovies.util.ItemClickSupport;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final int GRID_COLUMN_COUNT = 2;
    private static final MovieFilter DEFAULT_MOVIE_FILTER = MovieFilter.TOP_RATED;
    public static final String MOVIE_ID = "MOVIE_ID";

    @BindView(R.id.grid_main)
    RecyclerView gridMain;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private MovieAdapter movieAdapter;
    private MovieListViewModel movieListViewModel;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getApplicationComponent().inject(this);
        movieListViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel.class);
        ButterKnife.bind(this);

        initGridMain();
        observeViewModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_grid, menu);
        initMovieFilterSpinner(menu);
        return true;
    }

    private void initGridMain() {
        movieAdapter = new MovieAdapter();
        gridMain.setAdapter(movieAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, GRID_COLUMN_COUNT);
        gridMain.setLayoutManager(gridLayoutManager);
        ItemClickSupport.addTo(gridMain).setOnItemClickListener((recyclerView, position, v) ->
                openMovieDetails(((MovieAdapter) recyclerView.getAdapter()).getMovies().get(position)));
    }

    private void initMovieFilterSpinner(@NonNull Menu menu) {
        Spinner spinner = (Spinner) menu.findItem(R.id.movie_filter).getActionView();
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MovieFilter.values()));
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                movieListViewModel.loadMovies((MovieFilter) adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void observeViewModel() {
        movieListViewModel.getMovies().observe(this, movieAdapter::setMovies);
        movieListViewModel.getInProgress().observe(this, this::toggleProgress);
        movieListViewModel.loadMovies(DEFAULT_MOVIE_FILTER);
    }

    private void toggleProgress(boolean inProgress) {
        progressBar.setVisibility(inProgress ? View.VISIBLE : View.GONE);
        gridMain.setVisibility(inProgress ? View.GONE : View.VISIBLE);
    }

    private void openMovieDetails(@NonNull Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MOVIE_ID, movie.getId());
        startActivity(intent);
    }
}
