package id.dicoding.expertcourse.repository.favorite_base_movie;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.dicoding.expertcourse.db.model.FavoriteBaseMovie;

public interface FavoriteBaseMovieDataSource {
    LiveData<List<FavoriteBaseMovie>> getFavoriteBaseMovies();

    Cursor getFavoriteBaseMoviesCursor();

    LiveData<FavoriteBaseMovie> getFavoriteBaseMovieByIdAndType(int id, int type);

    void insertNewFavoriteBaseMovie(FavoriteBaseMovie favoriteBaseMovie);

    void deleteFavoriteBaseMovie(FavoriteBaseMovie favoriteBaseMovie);

    void deleteFavoriteBaseMovieByIdAndType(int id, int type);
}
