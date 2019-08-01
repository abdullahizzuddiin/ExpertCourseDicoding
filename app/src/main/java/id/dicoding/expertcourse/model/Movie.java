package id.dicoding.expertcourse.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie extends BaseMovie implements Parcelable {
    private String revenue, budget;

    public Movie() {
        super();
    }

    protected Movie(Parcel in) {
        super(in);
        revenue = in.readString();
        budget = in.readString();
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

    public String getBanner() {
        return "movies/" + super.getBanner();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(revenue);
        dest.writeString(budget);
    }
}
