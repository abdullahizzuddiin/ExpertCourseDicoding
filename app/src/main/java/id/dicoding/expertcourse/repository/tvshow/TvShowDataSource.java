package id.dicoding.expertcourse.repository.tvshow;

import java.util.List;

import id.dicoding.expertcourse.model.TvShow;

public interface TvShowDataSource {
    void getTvShows(LoadDataCallback callback);

    interface LoadDataCallback {
        void onDataLoaded(List<TvShow> tvShowList);
    }
}
