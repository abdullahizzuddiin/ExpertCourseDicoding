package id.dicoding.expertcourse.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.repository.movie.MovieDataSource;
import id.dicoding.expertcourse.repository.movie.MovieOnlineDBDataSource;
import id.dicoding.expertcourse.repository.movie.MovieRepository;

public class DetailMovieViewModel extends ViewModel implements MovieDataSource.GetDetailDataCallback {
    private final MutableLiveData<Movie> loadedMovie = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isFailure = new MutableLiveData<>();
    private final MovieRepository movieRepository;
    private String lang;
    private int movieId;

    public DetailMovieViewModel() {
        movieRepository = new MovieRepository(new MovieOnlineDBDataSource());
    }

    public boolean hasInitiate() {
        return loadedMovie.getValue() != null;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public boolean isLangChanged(Locale newLocale) {
        if(lang == null) {
            return false;
        }

        return !newLocale.getLanguage().equals(new Locale(lang).getLanguage());
    }

    public void loadMovie() {
        isLoading.postValue(true);
        isFailure.postValue(false);
        movieRepository.getDetailMovie(movieId, lang,this);
    }

    @Override
    public void onDataLoaded(Movie movie) {
        loadedMovie.postValue(movie);
        isLoading.postValue(false);
    }

    @Override
    public void onFailure() {
        isFailure.postValue(true);
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public LiveData<Movie> getLoadedMovie() {
        return loadedMovie;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public LiveData<Boolean> isFailure() {
        return isFailure;
    }
}
