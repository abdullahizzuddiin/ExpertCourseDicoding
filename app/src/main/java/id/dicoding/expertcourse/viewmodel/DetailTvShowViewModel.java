package id.dicoding.expertcourse.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

import id.dicoding.expertcourse.constant.MovieConst;
import id.dicoding.expertcourse.model.FavoriteBaseMovie;
import id.dicoding.expertcourse.model.TvShow;
import id.dicoding.expertcourse.repository.favorite_base_movie.FavoriteBaseMovieLocalDBDataSource;
import id.dicoding.expertcourse.repository.favorite_base_movie.FavoriteBaseMovieRepository;
import id.dicoding.expertcourse.repository.tvshow.TvShowDataSource;
import id.dicoding.expertcourse.repository.tvshow.TvShowOnlineDBDataSource;
import id.dicoding.expertcourse.repository.tvshow.TvShowRepository;

public class DetailTvShowViewModel extends ViewModel implements TvShowDataSource.GetDetailDataCallback {
    private final TvShowRepository movieRepository;
    private final FavoriteBaseMovieRepository favoriteBaseMovieRepository;

    private final MutableLiveData<TvShow> loadedTvShow = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isDataLoadFailure = new MutableLiveData<>();

    private String lang;
    private int tvShowId;

    private Function<TvShow, LiveData<FavoriteBaseMovie>> getFavoriteBaseMovieCallback = new Function<TvShow, LiveData<FavoriteBaseMovie>>() {
        @Override
        public LiveData<FavoriteBaseMovie> apply(TvShow input) {
            return favoriteBaseMovieRepository.getFavoriteBaseMovieByIdAndType(input.getId(), MovieConst.TYPE_TV_SHOWS);
        }
    };

    private LiveData<FavoriteBaseMovie> favoriteBaseMovie = Transformations.switchMap(loadedTvShow, getFavoriteBaseMovieCallback);


    private Function<FavoriteBaseMovie, Boolean> onFavoriteBaseMovieChanged = new Function<FavoriteBaseMovie, Boolean>() {
        @Override
        public Boolean apply(FavoriteBaseMovie input) {
            return input != null;
        }
    };

    private final LiveData<Boolean> isFavorite = Transformations.map(favoriteBaseMovie, onFavoriteBaseMovieChanged);

    public DetailTvShowViewModel() {
        movieRepository = new TvShowRepository(new TvShowOnlineDBDataSource());
        favoriteBaseMovieRepository = new FavoriteBaseMovieRepository(new FavoriteBaseMovieLocalDBDataSource());
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
        isDataLoading.postValue(true);
        isDataLoadFailure.postValue(false);
        movieRepository.getDetailTvShow(tvShowId, lang, this);
    }

    @Override
    public void onDataLoaded(TvShow tvShow) {
        loadedTvShow.postValue(tvShow);
        isDataLoading.postValue(false);
    }

    @Override
    public void onFailure() {
        isDataLoadFailure.postValue(true);
    }

    public void setTvShowId(int tvShowId) {
        this.tvShowId = tvShowId;
    }

    public void onFavoriteClick() {
        if(isFavorite.getValue()) {
            removeFromFavorite();
        } else {
            addToFavorite();
        }
    }

    private void addToFavorite() {
        FavoriteBaseMovie favoriteBaseMovie = new FavoriteBaseMovie();
        favoriteBaseMovie.setFromBaseMovie(getLoadedTvShow().getValue());
        favoriteBaseMovie.setType(MovieConst.TYPE_TV_SHOWS);
        favoriteBaseMovieRepository.insertNewFavoriteBaseMovie(favoriteBaseMovie);
    }

    private void removeFromFavorite() {
        favoriteBaseMovieRepository.deleteFavoriteBaseMovieByIdAndType(tvShowId, MovieConst.TYPE_TV_SHOWS);
    }

    public LiveData<Boolean> isFavorite() {
        return isFavorite;
    }

    public LiveData<TvShow> getLoadedTvShow() {
        return loadedTvShow;
    }

    public LiveData<Boolean> isDataLoading() {
        return isDataLoading;
    }

    public LiveData<Boolean> isDataLoadFailure() {
        return isDataLoadFailure;
    }
}
