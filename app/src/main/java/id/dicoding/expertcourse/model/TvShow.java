package id.dicoding.expertcourse.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class TvShow extends BaseMovie implements Parcelable {
    @Expose
    private String status;

    public TvShow() {
        super();
    }

    protected TvShow(Parcel in) {
        super(in);
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
