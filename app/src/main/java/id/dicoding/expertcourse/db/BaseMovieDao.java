package id.dicoding.expertcourse.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.dicoding.expertcourse.model.FavoriteBaseMovie;

@Dao
public interface BaseMovieDao {
    @Query("Select * from favorite_base_movie")
    LiveData<List<FavoriteBaseMovie>> getBaseMovieList();

    @Query("Select * from favorite_base_movie where id =:id and type = :type")
    LiveData<FavoriteBaseMovie> getBaseMovieByIdAndType(int id, int type);

    @Insert
    void insertBaseMovie(FavoriteBaseMovie baseMovie);

    @Delete
    void deleteBaseMovie(FavoriteBaseMovie baseMovieDB);

    @Query("DELETE FROM favorite_base_movie WHERE id = :id and type = :type")
    void deleteBaseMovieByIdAndType(int id, int type);
}
