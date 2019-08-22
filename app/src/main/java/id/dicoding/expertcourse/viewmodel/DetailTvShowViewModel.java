package id.dicoding.expertcourse.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.dicoding.expertcourse.model.TvShow;
import id.dicoding.expertcourse.repository.tvshow.TvShowDataSource;
import id.dicoding.expertcourse.repository.tvshow.TvShowOnlineDBDataSource;
import id.dicoding.expertcourse.repository.tvshow.TvShowRepository;

public class DetailTvShowViewModel extends ViewModel implements TvShowDataSource.GetDetailDataCallback {
    private MutableLiveData<TvShow> loadedTvShow = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFailure = new MutableLiveData<>();
    private TvShowRepository movieRepository;
    private int tvShowId;

    public DetailTvShowViewModel() {
        movieRepository = new TvShowRepository(new TvShowOnlineDBDataSource());
    }

    public void loadTvShow() {
        isLoading.postValue(true);
        isFailure.postValue(false);
        movieRepository.getDetailTvShow(tvShowId, this);
    }

    @Override
    public void onDataLoaded(TvShow movie) {
        loadedTvShow.postValue(movie);
        isLoading.postValue(false);
    }

    @Override
    public void onFailure() {
        isFailure.postValue(true);
    }

    public boolean hasInitiate() {
        return loadedTvShow.getValue() != null;
    }

    public void setTvShowId(int tvShowId) {
        this.tvShowId = tvShowId;
    }

    public LiveData<TvShow> getLoadedTvShow() {
        return loadedTvShow;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public LiveData<Boolean> isFailure() {
        return isFailure;
    }
}
