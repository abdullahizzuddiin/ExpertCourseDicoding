package id.dicoding.expertcourse.db.main;

import android.content.Context;

import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import id.dicoding.expertcourse.db.model.FavoriteBaseMovie;

@Database(entities = {FavoriteBaseMovie.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "din-movie-database";
    private static AppDatabase INSTANCE;

    public abstract BaseMovieDao baseMovieDao();

    public synchronized static void init(Context context) {
        if(INSTANCE != null) {
            return;
        }

        INSTANCE =
                Room.databaseBuilder(
                        context,
                        AppDatabase.class,
                        DB_NAME)
                        .build();
    }

    public static AppDatabase getInstance() {
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
