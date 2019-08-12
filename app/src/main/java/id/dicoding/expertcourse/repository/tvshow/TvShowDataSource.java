package id.dicoding.expertcourse.repository.tvshow;

import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;

public interface TvShowDataSource {
    void getTvShows(LoadDataCallback callback);

    interface LoadDataCallback {
        void onDataLoaded(List<BaseMovie> tvShowList);
    }
}
