package id.dicoding.expertcourse.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

import id.dicoding.expertcourse.model.TvShow;
import id.dicoding.expertcourse.repository.tvshow.TvShowDataSource;
import id.dicoding.expertcourse.repository.tvshow.TvShowOnlineDBDataSource;
import id.dicoding.expertcourse.repository.tvshow.TvShowRepository;

public class DetailTvShowViewModel extends ViewModel implements TvShowDataSource.GetDetailDataCallback {
    private final MutableLiveData<TvShow> loadedTvShow = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isFailure = new MutableLiveData<>();
    private final TvShowRepository movieRepository;
    private String lang;
    private int tvShowId;

    public DetailTvShowViewModel() {
        movieRepository = new TvShowRepository(new TvShowOnlineDBDataSource());
    }

    public boolean hasInitiate() {
        return loadedTvShow.getValue() != null;
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

    public void loadTvShow() {
        isLoading.postValue(true);
        isFailure.postValue(false);
        movieRepository.getDetailTvShow(tvShowId, lang, this);
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
