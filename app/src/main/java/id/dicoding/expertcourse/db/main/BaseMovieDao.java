package id.dicoding.expertcourse.db.main;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.dicoding.expertcourse.db.model.FavoriteBaseMovie;

@Dao
public interface BaseMovieDao {
    @Query("Select * from " + FavoriteBaseMovie.TABLE_NAME)
    LiveData<List<FavoriteBaseMovie>> getBaseMovieList();

    @Query("Select * from " + FavoriteBaseMovie.TABLE_NAME)
    Cursor getBaseMovieListCursor();

    @Query("Select * from "+ FavoriteBaseMovie.TABLE_NAME +
            " where " + FavoriteBaseMovie.COLUMN_ID + " = :id " +
            "and " +  FavoriteBaseMovie.COLUMN_TYPE+" = :type")
    LiveData<FavoriteBaseMovie> getBaseMovieByIdAndType(int id, int type);

    @Query("Select * from "+ FavoriteBaseMovie.TABLE_NAME +
            " where " + FavoriteBaseMovie.COLUMN_ID + " = :id " +
            "and " +  FavoriteBaseMovie.COLUMN_TYPE+" = :type")
    Cursor getBaseMovieByIdAndTypeCursor(int id, int type);

    @Insert
    void insertBaseMovie(FavoriteBaseMovie baseMovie);

    @Delete
    void deleteBaseMovie(FavoriteBaseMovie baseMovieDB);

    @Query("DELETE FROM " + FavoriteBaseMovie.TABLE_NAME +
            " where " + FavoriteBaseMovie.COLUMN_ID + " = :id " +
            "and " +  FavoriteBaseMovie.COLUMN_TYPE +" = :type")
    void deleteBaseMovieByIdAndType(int id, int type);
}
