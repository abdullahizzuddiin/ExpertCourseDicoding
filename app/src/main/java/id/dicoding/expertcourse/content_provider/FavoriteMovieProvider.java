package id.dicoding.expertcourse.content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import id.dicoding.expertcourse.db.main.AppDatabase;
import id.dicoding.expertcourse.db.model.FavoriteBaseMovie;

public class FavoriteMovieProvider extends ContentProvider {
    public static final String AUTHORITY = "id.dicoding.expertcourse";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(FavoriteBaseMovie.TABLE_NAME)
            .build();

    private static final int FAVORITE_MOVIE_ALL = 1;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // content://id.dicoding.expertcourse.provider/favorite_base_movie
        sUriMatcher.addURI(AUTHORITY, FavoriteBaseMovie.TABLE_NAME, FAVORITE_MOVIE_ALL);
    }

    private AppDatabase appDatabase;

    @Override
    public boolean onCreate() {
        appDatabase = AppDatabase.getInstance();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;

        try {
            if (sUriMatcher.match(uri) == FAVORITE_MOVIE_ALL) {
                cursor = appDatabase.baseMovieDao().getBaseMovieListCursor();
            }
        } catch (Exception e) {
            Log.d("TAG", e.getMessage());
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
