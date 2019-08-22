package id.dicoding.expertcourse.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.repository.movie.MovieDataSource;
import id.dicoding.expertcourse.repository.movie.MovieOnlineDBDataSource;
import id.dicoding.expertcourse.repository.movie.MovieRepository;

public class DetailMovieViewModel extends ViewModel implements MovieDataSource.GetDetailDataCallback {
    private MutableLiveData<Movie> loadedMovie = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFailure = new MutableLiveData<>();
    private MovieRepository movieRepository;
    private int movieId;

    public DetailMovieViewModel() {
        movieRepository = new MovieRepository(new MovieOnlineDBDataSource());
    }

    public void loadMovie() {
        isLoading.postValue(true);
        isFailure.postValue(false);
        movieRepository.getDetailMovie(movieId, this);
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

    public boolean hasInitiate() {
        return loadedMovie.getValue() != null;
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
