package id.dicoding.expertcourse.repository.favorite_base_movie;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.dicoding.expertcourse.model.FavoriteBaseMovie;

public interface FavoriteBaseMovieDataSource {
    LiveData<List<FavoriteBaseMovie>> getFavoriteBaseMovies();

    LiveData<FavoriteBaseMovie> getFavoriteBaseMovieByIdAndType(int id, int type);

    void insertNewFavoriteBaseMovie(FavoriteBaseMovie favoriteBaseMovie);

    void deleteFavoriteBaseMovie(FavoriteBaseMovie favoriteBaseMovie);

    void deleteFavoriteBaseMovieByIdAndType(int id, int type);
}
