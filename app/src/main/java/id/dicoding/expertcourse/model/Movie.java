package id.dicoding.expertcourse.model;

import android.os.Parcel;
import android.os.Parcelable;

import static id.dicoding.expertcourse.util.Format.parseToPerFiveRate;

public class Movie implements Parcelable {
    private String title, banner, releasedYear, overview, revenue, budget, runtime, originalLanguage;
    private int reviewScore;

    public Movie() {

    }

    protected Movie(Parcel in) {
        title = in.readString();
        banner = in.readString();
        releasedYear = in.readString();
        overview = in.readString();
        revenue = in.readString();
        budget = in.readString();
        runtime = in.readString();
        originalLanguage = in.readString();
        reviewScore = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getOverview() {
        return overview;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public float getReviewScoreFiveMaxFormat() {
        return parseToPerFiveRate(getReviewScore());
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(banner);
        dest.writeString(releasedYear);
        dest.writeString(overview);
        dest.writeString(revenue);
        dest.writeString(budget);
        dest.writeString(runtime);
        dest.writeString(originalLanguage);
        dest.writeInt(reviewScore);
    }
}
