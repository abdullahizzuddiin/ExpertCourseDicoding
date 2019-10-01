package id.dicoding.expertcourse.db.main;

import android.content.Context;

import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import id.dicoding.expertcourse.db.model.FavoriteBaseMovie;

@Database(entities = {FavoriteBaseMovie.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "din-movie-database";
    private static AppDatabase INSTANCE;

    public abstract BaseMovieDao baseMovieDao();

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE favorite_base_movie RENAME TO favorite_base_movie_tmp");
                database.execSQL("CREATE TABLE favorite_base_movie " +
                        "(_id INTEGER NOT NULL, " +
                        "original_title TEXT, " +
                        "poster_path TEXT, " +
                        "release_date TEXT, " +
                        "vote_average REAL NOT NULL, " +
                        "overview TEXT, " +
                        "type INTEGER NOT NULL," +
                        "PRIMARY KEY(_id))");
                database.execSQL("CREATE INDEX index_favorite_base_movie__id on favorite_base_movie (_id)");
                database.execSQL("INSERT INTO favorite_base_movie " +
                            "(_id, original_title, poster_path, release_date, vote_average, overview, type) " +
                        "SELECT id, original_title, poster_path, release_date, vote_average, overview, type FROM favorite_base_movie_tmp");

                database.execSQL("DROP TABLE favorite_base_movie_tmp");
        }
    };

    public static AppDatabase getInstance() {
        return INSTANCE;
    }

    public synchronized static void init(Context context) {
        if(INSTANCE != null) {
            return;
        }

        INSTANCE =
                Room.databaseBuilder(
                        context,
                        AppDatabase.class,
                        DB_NAME)
                        .addMigrations(MIGRATION_1_2)
                        .build();
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
