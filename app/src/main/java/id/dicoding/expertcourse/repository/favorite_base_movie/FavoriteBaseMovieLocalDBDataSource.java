package id.dicoding.expertcourse.repository.favorite_base_movie;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.dicoding.expertcourse.db.AppDatabase;
import id.dicoding.expertcourse.db.AppExecutors;
import id.dicoding.expertcourse.model.FavoriteBaseMovie;

public class FavoriteBaseMovieLocalDBDataSource implements FavoriteBaseMovieDataSource {
    private AppDatabase appDatabase;

    public FavoriteBaseMovieLocalDBDataSource() {
        appDatabase = AppDatabase.getInstance();
    }

    @Override
    public LiveData<List<FavoriteBaseMovie>> getFavoriteBaseMovies() {
        return appDatabase.baseMovieDao().getBaseMovieList();
    }

    @Override
    public LiveData<FavoriteBaseMovie> getFavoriteBaseMovieByIdAndType(int id, int type) {
        return appDatabase.baseMovieDao().getBaseMovieByIdAndType(id, type);
    }

    @Override
    public void insertNewFavoriteBaseMovie(final FavoriteBaseMovie favoriteBaseMovie) {
        AppExecutors.
            getInstance().
            diskIO().
            execute(new Runnable() {
                @Override
                public void run() {
                    appDatabase.baseMovieDao().insertBaseMovie(favoriteBaseMovie);
                }});
    }

    @Override
    public void deleteFavoriteBaseMovie(final FavoriteBaseMovie favoriteBaseMovie) {
        AppExecutors
            .getInstance()
            .diskIO()
            .execute(new Runnable() {
                @Override
                public void run() {
                    appDatabase.baseMovieDao().deleteBaseMovie(favoriteBaseMovie);
            }});
    }

    @Override
    public void deleteFavoriteBaseMovieByIdAndType(final int id, final int type) {
        AppExecutors
            .getInstance()
            .diskIO()
            .execute(new Runnable() {
                @Override
                public void run() {
                    appDatabase.baseMovieDao().deleteBaseMovieByIdAndType(id, type);
                }});
    }
}
