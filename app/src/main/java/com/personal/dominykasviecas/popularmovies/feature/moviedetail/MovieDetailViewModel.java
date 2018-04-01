package com.personal.dominykasviecas.popularmovies.feature.moviedetail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.personal.dominykasviecas.popularmovies.data.movie.MovieRepository;
import com.personal.dominykasviecas.popularmovies.data.movie.Movie;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MovieDetailViewModel extends ViewModel {

    @NonNull
    private MovieRepository movieRepository;
    @NonNull
    private CompositeDisposable disposables = new CompositeDisposable();

    @NonNull
    final private MutableLiveData<Movie> movie = new MutableLiveData<>();
    @NonNull
    final private MutableLiveData<Boolean> inProgress = new MutableLiveData<>();

    @Inject MovieDetailViewModel(@NonNull MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        disposables.dispose();
    }

    @NonNull
    public MutableLiveData<Movie> getMovie() {
        return movie;
    }

    @NonNull
    public MutableLiveData<Boolean> getInProgress() {
        return inProgress;
    }

    void loadMovie(@NonNull String id) {
        disposables.add(movieRepository.getMovie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> inProgress.postValue(true))
                .doOnSuccess(movie -> inProgress.setValue(false))
                .subscribe(movie::postValue, Timber::e));
    }
}
