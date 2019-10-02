package id.dicoding.expertcourse.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.dicoding.expertcourse.constant.MovieConst;
import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.repository.movie.MovieDataSource;
import id.dicoding.expertcourse.repository.movie.MovieOnlineDBDataSource;
import id.dicoding.expertcourse.repository.movie.MovieRepository;
import id.dicoding.expertcourse.repository.tvshow.TvShowDataSource;
import id.dicoding.expertcourse.repository.tvshow.TvShowOnlineDBDataSource;
import id.dicoding.expertcourse.repository.tvshow.TvShowRepository;

public class SearchViewModel extends ViewModel implements MovieDataSource.GetMoviesDataCallback, TvShowDataSource.GetTvShowsLoadDataCallback {
    private final MutableLiveData<List<BaseMovie>> baseMovieList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isFailure = new MutableLiveData<>();
    private final MovieRepository movieRepository;
    private final TvShowRepository tvShowRepository;
    private int movieType;
    private String lang;

    public SearchViewModel() {
        movieRepository = new MovieRepository(new MovieOnlineDBDataSource());
        tvShowRepository = new TvShowRepository(new TvShowOnlineDBDataSource());
    }

    public void setMovieType(int movieType) {
        this.movieType = movieType;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void searchMovie(String query) {
        isLoading.postValue(true);
        isFailure.postValue(false);

        if(movieType == MovieConst.TYPE_MOVIES) {
            movieRepository.searchMovies(lang, query, this);
        } else {
            tvShowRepository.searchTvShows(lang, query,this);
        }
    }

    @Override
    public void onDataLoaded(List<BaseMovie> result) {
        isLoading.postValue(false);
        baseMovieList.postValue(result);
    }

    @Override
    public void onFailure() {
        isFailure.postValue(true);
        isLoading.postValue(false);
    }

    public BaseMovie getBaseMovieByPosition(int position) {
        if(baseMovieList.getValue() == null) {
            return null;
        }

        return baseMovieList.getValue().get(position);
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public LiveData<Boolean> isFailure() {
        return isFailure;
    }

    public LiveData<List<BaseMovie>> getBaseMovieList() {
        return baseMovieList;
    }
}
