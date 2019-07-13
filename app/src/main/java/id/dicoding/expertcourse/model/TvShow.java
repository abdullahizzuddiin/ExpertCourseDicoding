package id.dicoding.expertcourse.model;

import android.os.Parcel;
import android.os.Parcelable;

import static id.dicoding.expertcourse.util.Format.parseToPerFiveRate;

public class TvShow implements Parcelable {
    public static final int STATUS_CONTINUE = 0;
    public static final int STATUS_ENDED = 1;
    public static final int STATUS_CANCELED = 0;

    private String title, banner, releasedYear, overviewEn, overviewId, runtime, originalLanguage;
    private int status, reviewScore;

    public TvShow() {}

    protected TvShow(Parcel in) {
        title = in.readString();
        banner = in.readString();
        releasedYear = in.readString();
        overviewEn = in.readString();
        overviewId = in.readString();
        runtime = in.readString();
        originalLanguage = in.readString();
        status = in.readInt();
        reviewScore = in.readInt();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner() {
        return "tv_shows/" + banner;
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

    public String getOverviewEn() {
        return overviewEn;
    }

    public void setOverviewEn(String overviewEn) {
        this.overviewEn = overviewEn;
    }

    public String getOverviewId() {
        return overviewId;
    }

    public void setOverviewId(String overviewId) {
        this.overviewId = overviewId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(banner);
        dest.writeString(releasedYear);
        dest.writeString(overviewEn);
        dest.writeString(overviewId);
        dest.writeString(runtime);
        dest.writeString(originalLanguage);
        dest.writeInt(status);
        dest.writeInt(reviewScore);
    }
}
