package com.personal.dominykasviecas.popularmovies.feature.landing;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.personal.dominykasviecas.popularmovies.data.movie.Movie;
import com.personal.dominykasviecas.popularmovies.data.movie.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MovieListViewModel extends ViewModel {

    @NonNull
    private MovieRepository movieRepository;
    @NonNull
    private CompositeDisposable disposables = new CompositeDisposable();

    @NonNull
    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Boolean> inProgress = new MutableLiveData<>();

    @Inject MovieListViewModel(@NonNull MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        disposables.dispose();
    }

    @NonNull
    LiveData<List<Movie>> getMovies() {
        return movies;
    }

    @NonNull
    LiveData<Boolean> getInProgress() {
        return inProgress;
    }

    void loadMovies(@NonNull MovieFilter movieFilter) {
        switch (movieFilter) {
            case POPULAR:
                disposables.add(movieRepository.getPopularMovies()
                        .doOnSubscribe(disposable -> inProgress.postValue(true))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess(movies -> inProgress.setValue(false))
                        .subscribe(movies::postValue, Timber::e));
                break;
            case TOP_RATED:
                disposables.add(movieRepository.getTopRatedMovies()
                        .doOnSubscribe(disposable -> inProgress.postValue(true))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess(movies -> inProgress.setValue(false))
                        .subscribe(movies::postValue, Timber::e));
                break;

        }
    }
}
