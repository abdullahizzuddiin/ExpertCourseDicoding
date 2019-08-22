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

public class BaseMovieListViewModel extends ViewModel implements MovieDataSource.GetMoviesDataCallback, TvShowDataSource.GetTvShowsLoadDataCallback
{
    private MutableLiveData<List<BaseMovie>> baseMovieList = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFailure = new MutableLiveData<>();
    private MovieRepository movieRepository;
    private TvShowRepository tvShowRepository;
    private int movieType;

    public BaseMovieListViewModel() {
        movieRepository = new MovieRepository(new MovieOnlineDBDataSource());
        tvShowRepository = new TvShowRepository(new TvShowOnlineDBDataSource());
    }

    public boolean hasInitiate() {
        return baseMovieList.getValue() != null;
    }

    public void setBaseMovieType(int movieType) {
        this.movieType = movieType;
        getBaseMovie();
    }

    private void getBaseMovie() {
        isLoading.postValue(true);
        isFailure.postValue(false);

        if(movieType == MovieConst.TYPE_MOVIES) {
            movieRepository.getMovies(this);
        } else {
            tvShowRepository.getTvShows(this);
        }
    }

    @Override
    public void onDataLoaded(List<BaseMovie> movieList) {
        baseMovieList.postValue(movieList);
        isLoading.postValue(false);
    }

    @Override
    public void onFailure() {
        isFailure.postValue(true);
    }

    public BaseMovie getBaseMovieByPosition(int position) {
        return baseMovieList.getValue().get(position);
    }

    public int getMovieType() {
        return this.movieType;
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
