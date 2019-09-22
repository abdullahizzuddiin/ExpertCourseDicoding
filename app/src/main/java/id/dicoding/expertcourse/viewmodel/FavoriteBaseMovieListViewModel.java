package id.dicoding.expertcourse.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.dicoding.expertcourse.model.FavoriteBaseMovie;
import id.dicoding.expertcourse.repository.favorite_base_movie.FavoriteBaseMovieLocalDBDataSource;
import id.dicoding.expertcourse.repository.favorite_base_movie.FavoriteBaseMovieRepository;

public class FavoriteBaseMovieListViewModel extends ViewModel {
    private FavoriteBaseMovieRepository repository;
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isFailure = new MutableLiveData<>();
    private LiveData<List<FavoriteBaseMovie>> favoriteBaseMovieList;

    public FavoriteBaseMovieListViewModel() {
        repository = new FavoriteBaseMovieRepository(new FavoriteBaseMovieLocalDBDataSource());
        getFavoriteBaseMovies();
    }

    public void getFavoriteBaseMovies() {
        isLoading.postValue(true);
        favoriteBaseMovieList = repository.getFavoriteBaseMovies();
        isLoading.postValue(false);
    }

    public FavoriteBaseMovie getFavoriteBaseMovieByPosition(int position) {
        return favoriteBaseMovieList.getValue().get(position);
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public LiveData<Boolean> isFailure() {
        return isFailure;
    }

    public LiveData<List<FavoriteBaseMovie>> getFavoriteBaseMovieList() {
        return favoriteBaseMovieList;
    }
}