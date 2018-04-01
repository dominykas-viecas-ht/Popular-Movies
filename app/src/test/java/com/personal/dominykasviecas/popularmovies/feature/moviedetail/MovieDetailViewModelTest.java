package com.personal.dominykasviecas.popularmovies.feature.moviedetail;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.personal.dominykasviecas.popularmovies.data.movie.Movie;
import com.personal.dominykasviecas.popularmovies.data.movie.MovieRepository;
import com.personal.dominykasviecas.popularmovies.data.movie.TestDataMovieRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class MovieDetailViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutor = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;
    private MovieDetailViewModel movieDetailViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

        when(movieRepository.getMovie(any())).thenReturn(Single.just(TestDataMovieRepository.movie));

        movieDetailViewModel = new MovieDetailViewModel(movieRepository);
    }

    @Test
    public void loadMovie() {
        Observer<Movie> movieObserver = mock(Observer.class);
        Observer<Boolean> progressObserver = mock(Observer.class);
        movieDetailViewModel.getMovie().observeForever(movieObserver);
        movieDetailViewModel.getInProgress().observeForever(progressObserver);

        movieDetailViewModel.loadMovie("0");

        InOrder inOrder = Mockito.inOrder(movieObserver, progressObserver);
        inOrder.verify(progressObserver, times(1)).onChanged(true);
        inOrder.verify(progressObserver, times(1)).onChanged(false);
        inOrder.verify(movieObserver, times(1)).onChanged(TestDataMovieRepository.movie);
    }

}