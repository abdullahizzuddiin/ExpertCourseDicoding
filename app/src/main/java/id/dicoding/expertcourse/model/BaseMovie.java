package id.dicoding.expertcourse.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static id.dicoding.expertcourse.request.ApiUtil.BASE_BIG_IMAGE_URL;
import static id.dicoding.expertcourse.request.ApiUtil.BASE_MEDIUM_IMAGE_URL;

public class BaseMovie implements Parcelable {
    @Expose
    private int id;

    @SerializedName(value = "original_title", alternate = {"original_name"})
    @Expose
    private String originalTitle;

    @Expose
    private double popularity;

    @SerializedName("vote_average")
    @Expose
    private double voteAverage;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("backdrop_path")
    @Expose
    private String bannerPath;

    @Expose
    private String overview;

    @SerializedName("vote_count")
    @Expose
    private int voteCount;

    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @SerializedName("genres")
    @Expose
    private List<Genre> genreList;

    @SerializedName(value =  "release_date", alternate = {"first_air_date"})
    @Expose
    private String releaseDate;

    public BaseMovie() {
    }

    protected BaseMovie(Parcel in) {
        id = in.readInt();
        originalTitle = in.readString();
        popularity = in.readDouble();
        voteAverage = in.readDouble();
        posterPath = in.readString();
        bannerPath = in.readString();
        overview = in.readString();
        voteCount = in.readInt();
        originalLanguage = in.readString();
        releaseDate = in.readString();
        genreList = in.createTypedArrayList(Genre.CREATOR);
    }

    public static final Creator<BaseMovie> CREATOR = new Creator<BaseMovie>() {
        @Override
        public BaseMovie createFromParcel(Parcel in) {
            return new BaseMovie(in);
        }

        @Override
        public BaseMovie[] newArray(int size) {
            return new BaseMovie[size];
        }
    };

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

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterUrl() {
        return BASE_MEDIUM_IMAGE_URL + getPosterPath();
    }

    public String getBannerPath() {
        return bannerPath;
    }

    public void setBannerPath(String bannerPath) {
        this.bannerPath = bannerPath;
    }

    public String getBannerUrl() {
        return BASE_BIG_IMAGE_URL + getBannerPath();
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getDisplayLanguage() {
        return new Locale(originalLanguage).getDisplayLanguage();
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public String getGenreListInText() {
        StringBuilder genreText = new StringBuilder();
        for (int ii = 0; ii < genreList.size(); ii++) {
            Genre genre = genreList.get(ii);
            genreText.append(genre.getName());

            boolean lastIndex = ii == genreList.size() - 1;
            if(!lastIndex) genreText.append(", ");
        }

        return genreText.toString();
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(originalTitle);
        dest.writeDouble(popularity);
        dest.writeDouble(voteAverage);
        dest.writeString(posterPath);
        dest.writeString(bannerPath);
        dest.writeString(overview);
        dest.writeInt(voteCount);
        dest.writeString(originalLanguage);
        dest.writeTypedList(genreList);
        dest.writeString(releaseDate);
    }
}
