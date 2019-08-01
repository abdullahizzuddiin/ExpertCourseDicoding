package id.dicoding.expertcourse.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

import static id.dicoding.expertcourse.util.Format.parseToPerFiveRate;
import static id.dicoding.expertcourse.util.Format.parseToPerTenRate;

public class BaseMovie implements Parcelable {
    private String title, banner, releasedYear, overviewEn, overviewId, runtime, originalLanguageEn, originalLanguageId;
    private int reviewScore;
    private final String NO_OVERVIEW_ENGLISH_SUPPORTED = "No overview in english";
    private final String NO_OVERVIEW_INDONESIAN_SUPPORTED = "Tidak ada ringkasan dalam bahasa Indonesia";

    public BaseMovie() {
    }

    protected BaseMovie(Parcel in) {
        title = in.readString();
        banner = in.readString();
        releasedYear = in.readString();
        overviewEn = in.readString();
        overviewId = in.readString();
        runtime = in.readString();
        originalLanguageEn = in.readString();
        originalLanguageId = in.readString();
        reviewScore = in.readInt();
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
        boolean isEnglish = Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage());
        return isEnglish ? getOverviewEn() : getOverviewId();
    }

    public String getOverviewEn() {
        return overviewEn.equals("null") ? NO_OVERVIEW_ENGLISH_SUPPORTED : overviewEn;
    }

    public void setOverviewEn(String overviewEn) {
        this.overviewEn = overviewEn;
    }

    public String getOverviewId() {
        return overviewId.equals("null") ? NO_OVERVIEW_INDONESIAN_SUPPORTED : overviewId;
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
        boolean isEnglish = Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage());
        return isEnglish ? getOriginalLanguageEn() : getOriginalLanguageId();
    }

    public String getOriginalLanguageEn() {
        return originalLanguageEn;
    }

    public void setOriginalLanguageEn(String originalLanguageEn) {
        this.originalLanguageEn = originalLanguageEn;
    }

    public String getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(String originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public float getReviewScoreTenMaxFormat() {
        return parseToPerTenRate(getReviewScore());
    }

    public float getReviewScoreFiveMaxFormat() {
        return parseToPerFiveRate(getReviewScore());
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
        dest.writeString(originalLanguageEn);
        dest.writeString(originalLanguageId);
        dest.writeInt(reviewScore);
    }
}
