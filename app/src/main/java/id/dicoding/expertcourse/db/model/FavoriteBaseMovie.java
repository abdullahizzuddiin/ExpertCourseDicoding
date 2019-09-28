package id.dicoding.expertcourse.db.model;

import android.database.Cursor;
import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import id.dicoding.expertcourse.constant.MovieConst;
import id.dicoding.expertcourse.model.BaseMovie;

import static id.dicoding.expertcourse.request.ApiUtil.BASE_MEDIUM_IMAGE_URL;

@Entity(tableName =  FavoriteBaseMovie.TABLE_NAME)
public class FavoriteBaseMovie {
    public static final String TABLE_NAME = "favorite_base_movie";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_ORIGINAL_TITLE = "original_title";
    public static final String COLUMN_POSTER_PATH = "poster_path";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_VOTE_AVERAGE = "vote_average";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_TYPE = "type";

    public static final int INDEX_MOVIE_ID = 0;
    public static final int INDEX_MOVIE_TITLE = 1;
    public static final int INDEX_MOVIE_POSTER_PATH = 2;
    public static final int INDEX_MOVIE_TYPE = 6;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index =  true, name = COLUMN_ID)
    private int id; //0

    @ColumnInfo(name = COLUMN_ORIGINAL_TITLE)
    private String originalTitle; //1

    @ColumnInfo(name = COLUMN_POSTER_PATH)
    private String posterPath; //2

    @ColumnInfo(name = COLUMN_RELEASE_DATE)
    private String releaseDate; //3

    @ColumnInfo(name = COLUMN_VOTE_AVERAGE)
    private double voteAverage; //4

    @ColumnInfo(name = COLUMN_OVERVIEW)
    private String overview; //5

    @ColumnInfo(name = COLUMN_TYPE)
    private int type; //6

    public FavoriteBaseMovie() {}


    @Ignore
    public FavoriteBaseMovie(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        this.originalTitle = cursor.getString(cursor.getColumnIndex(COLUMN_ORIGINAL_TITLE));
        this.posterPath = cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_PATH));
        this.releaseDate = cursor.getString(cursor.getColumnIndex(COLUMN_RELEASE_DATE));
        this.voteAverage = cursor.getDouble(cursor.getColumnIndex(COLUMN_VOTE_AVERAGE));
        this.overview = cursor.getString(cursor.getColumnIndex(COLUMN_OVERVIEW));
        this.type = cursor.getInt(cursor.getColumnIndex(COLUMN_TYPE));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getPosterUrl() {
        return BASE_MEDIUM_IMAGE_URL + getPosterPath();
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getReleaseYear() {
        try {
            SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
            Date date = oldFormat.parse(releaseDate);
            return newFormat.format(date);
        } catch (ParseException exception) {
            return "1990";
        }
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setFromBaseMovie(BaseMovie movie) {
        this.id = movie.getId();
        this.originalTitle = movie.getOriginalTitle();
        this.posterPath = movie.getPosterPath();
        this.releaseDate = movie.getReleaseDate();
        this.voteAverage = movie.getVoteAverage();
        this.overview = movie.getOverview();
        this.type = MovieConst.TYPE_MOVIES;
    }
}