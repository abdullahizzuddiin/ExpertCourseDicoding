package id.dicoding.expertcourse.repository.favorite_base_movie;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.dicoding.expertcourse.model.FavoriteBaseMovie;

public class FavoriteBaseMovieRepository implements FavoriteBaseMovieDataSource {
    private final FavoriteBaseMovieDataSource favoriteBaseMovieDataSource;

    public FavoriteBaseMovieRepository(FavoriteBaseMovieDataSource favoriteBaseMovieDataSource) {
        this.favoriteBaseMovieDataSource = favoriteBaseMovieDataSource;
    }

    @Override
    public LiveData<List<FavoriteBaseMovie>> getFavoriteBaseMovies() {
        return favoriteBaseMovieDataSource.getFavoriteBaseMovies();
    }

    @Override
    public LiveData<FavoriteBaseMovie> getFavoriteBaseMovieByIdAndType(int id, int type) {
        return favoriteBaseMovieDataSource.getFavoriteBaseMovieByIdAndType(id, type);
    }

    @Override
    public void insertNewFavoriteBaseMovie(FavoriteBaseMovie favoriteBaseMovie) {
        favoriteBaseMovieDataSource.insertNewFavoriteBaseMovie(favoriteBaseMovie);
    }

    @Override
    public void deleteFavoriteBaseMovie(FavoriteBaseMovie favoriteBaseMovie) {
        favoriteBaseMovieDataSource.deleteFavoriteBaseMovie(favoriteBaseMovie);
    }

    @Override
    public void deleteFavoriteBaseMovieByIdAndType(int id, int type) {
        favoriteBaseMovieDataSource.deleteFavoriteBaseMovieByIdAndType(id, type);
    }
}
