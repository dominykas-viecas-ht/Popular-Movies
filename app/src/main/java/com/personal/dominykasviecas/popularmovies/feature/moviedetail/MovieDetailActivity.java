package com.personal.dominykasviecas.popularmovies.feature.moviedetail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.personal.dominykasviecas.popularmovies.App;
import com.personal.dominykasviecas.popularmovies.BuildConfig;
import com.personal.dominykasviecas.popularmovies.R;
import com.personal.dominykasviecas.popularmovies.data.movie.Movie;
import com.personal.dominykasviecas.popularmovies.di.ViewModelFactory;
import com.personal.dominykasviecas.popularmovies.feature.landing.MainActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

import static com.personal.dominykasviecas.popularmovies.util.DateUtils.getYearFromDate;
import static com.personal.dominykasviecas.popularmovies.util.ViewUtils.VISIBLE;
import static com.personal.dominykasviecas.popularmovies.util.ViewUtils.tintTextViewDrawable;

public class MovieDetailActivity extends AppCompatActivity {

    private MovieDetailViewModel movieDetailViewModel;

    @BindView(R.id.movie_title)
    TextView movieTitle;
    @BindView(R.id.movie_vote_average)
    TextView movieVoteAverage;
    @BindView(R.id.movie_release_date)
    TextView movieReleaseDate;
    @BindView(R.id.movie_overview)
    TextView movieOverview;
    @BindView(R.id.movie_poster)
    ImageView moviePoster;
    @BindViews({R.id.movie_title, R.id.movie_vote_average, R.id.movie_release_date, R.id.movie_overview, R.id.movie_poster})
    List<View> mainLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ((App) getApplication()).getApplicationComponent().inject(this);
        movieDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel.class);
        ButterKnife.bind(this);

        observeViewModel();
        loadMovieFromIntent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadMovieFromIntent() {
        int id = getIntent().getIntExtra(MainActivity.MOVIE_ID, -1);
        if (id != -1) {
            movieDetailViewModel.loadMovie(String.valueOf(id));
        }
    }

    private void observeViewModel() {
        movieDetailViewModel.getMovie().observe(this, this::displayMovie);
        movieDetailViewModel.getInProgress().observe(this, this::toggleProgress);
    }

    private void displayMovie(@NonNull Movie movie) {
        movieTitle.setText(movie.getTitle());
        movieVoteAverage.setText(new DecimalFormat("#.#").format(movie.getVoteAverage()));
        tintTextViewDrawable(getResources(), movieVoteAverage, R.color.colorAccent);
        movieReleaseDate.setText(getYearFromDate(movie.getReleaseDate()));
        movieOverview.setText(movie.getOverview());
        Picasso.with(this).load(BuildConfig.TmdbImageUrl + movie.getPosterPath()).into(moviePoster);
    }

    private void toggleProgress(boolean inProgress) {
        progressBar.setVisibility(inProgress ? View.VISIBLE : View.GONE);
        ButterKnife.apply(mainLayout, VISIBLE, !inProgress);
    }
}
