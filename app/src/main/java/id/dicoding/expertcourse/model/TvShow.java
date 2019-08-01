package id.dicoding.expertcourse.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow extends BaseMovie implements Parcelable {
    private int status;

    public TvShow() {
        super();
    }

    protected TvShow(Parcel in) {
        super(in);
        status = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(status);
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBanner() {
        return "tv_shows/" + super.getBanner();
    }
}
