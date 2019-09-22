package id.dicoding.expertcourse.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import id.dicoding.expertcourse.constant.MovieConst;

import static id.dicoding.expertcourse.request.ApiUtil.BASE_MEDIUM_IMAGE_URL;

@Entity(tableName =  "favorite_base_movie")
public class FavoriteBaseMovie {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "original_title")
    private String originalTitle;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "vote_average")
    private double voteAverage;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "type")
    private int type;

    public FavoriteBaseMovie() {}

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