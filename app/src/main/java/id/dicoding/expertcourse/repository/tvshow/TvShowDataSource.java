package id.dicoding.expertcourse.repository.tvshow;

import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.TvShow;

public interface TvShowDataSource {
    void getTvShows(String lang, GetTvShowsLoadDataCallback callback);

    void getDetailTvShow(int tvShowId, String lang, GetDetailDataCallback callback);

    interface GetTvShowsLoadDataCallback {
        void onDataLoaded(List<BaseMovie> tvShowList);

        void onFailure();
    }

    interface GetDetailDataCallback {
        void onDataLoaded(TvShow tvShow);

        void onFailure();
    }
}
