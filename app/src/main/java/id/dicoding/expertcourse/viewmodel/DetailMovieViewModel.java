package id.dicoding.expertcourse.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

import id.dicoding.expertcourse.constant.MovieConst;
import id.dicoding.expertcourse.db.model.FavoriteBaseMovie;
import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.repository.favorite_base_movie.FavoriteBaseMovieLocalDBDataSource;
import id.dicoding.expertcourse.repository.favorite_base_movie.FavoriteBaseMovieRepository;
import id.dicoding.expertcourse.repository.movie.MovieDataSource;
import id.dicoding.expertcourse.repository.movie.MovieOnlineDBDataSource;
import id.dicoding.expertcourse.repository.movie.MovieRepository;

public class DetailMovieViewModel extends ViewModel implements MovieDataSource.GetDetailDataCallback {
    private final MovieRepository movieRepository;
    private final FavoriteBaseMovieRepository favoriteBaseMovieRepository;

    private final MutableLiveData<Movie> loadedMovie = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isDataLoadFailure = new MutableLiveData<>();

    private String lang;
    private int movieId;

    private Function<Movie, LiveData<FavoriteBaseMovie>> getFavoriteBaseMovieCallback = new Function<Movie, LiveData<FavoriteBaseMovie>>() {
        @Override
        public LiveData<FavoriteBaseMovie> apply(Movie input) {
            return favoriteBaseMovieRepository.getFavoriteBaseMovieByIdAndType(input.getId(), MovieConst.TYPE_MOVIES);
        }
    };

    private LiveData<FavoriteBaseMovie> favoriteBaseMovie = Transformations.switchMap(loadedMovie, getFavoriteBaseMovieCallback);


    private Function<FavoriteBaseMovie, Boolean> onFavoriteBaseMovieChanged = new Function<FavoriteBaseMovie, Boolean>() {
        @Override
        public Boolean apply(FavoriteBaseMovie input) {
            return input != null;
        }
    };

    private final LiveData<Boolean> isFavorite = Transformations.map(favoriteBaseMovie, onFavoriteBaseMovieChanged);

    public DetailMovieViewModel() {
        movieRepository = new MovieRepository(new MovieOnlineDBDataSource());
        favoriteBaseMovieRepository = new FavoriteBaseMovieRepository(new FavoriteBaseMovieLocalDBDataSource());
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
        isDataLoading.postValue(true);
        isDataLoadFailure.postValue(false);
        movieRepository.getDetailMovie(movieId, lang,this);
    }

    @Override
    public void onDataLoaded(Movie movie) {
        loadedMovie.postValue(movie);
        isDataLoading.postValue(false);
    }

    @Override
    public void onFailure() {
        isDataLoadFailure.postValue(true);
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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
        favoriteBaseMovie.setFromBaseMovie(getLoadedMovie().getValue());
        favoriteBaseMovie.setType(MovieConst.TYPE_MOVIES);
        favoriteBaseMovieRepository.insertNewFavoriteBaseMovie(favoriteBaseMovie);
    }

    private void removeFromFavorite() {
        favoriteBaseMovieRepository.deleteFavoriteBaseMovieByIdAndType(movieId, MovieConst.TYPE_MOVIES);
    }

    public boolean getFavoriteStatus() {
        if(isFavorite.getValue() == null) {
            return false;
        }

        return isFavorite.getValue();
    }

    public LiveData<Boolean> isFavorite() {
        return isFavorite;
    }

    public LiveData<Movie> getLoadedMovie() {
        return loadedMovie;
    }

    public LiveData<Boolean> isDataLoading() {
        return isDataLoading;
    }

    public LiveData<Boolean> isDataLoadFailure() {
        return isDataLoadFailure;
    }
}
