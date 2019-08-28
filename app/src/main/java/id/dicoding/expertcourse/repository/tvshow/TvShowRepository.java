package id.dicoding.expertcourse.repository.tvshow;

import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.TvShow;

public class TvShowRepository implements TvShowDataSource {
    private final TvShowDataSource tvShowDataSource;

    public TvShowRepository(TvShowDataSource dataSource) {
        this.tvShowDataSource = dataSource;
    }

    @Override
    public void getTvShows(String lang, final GetTvShowsLoadDataCallback callback) {
        tvShowDataSource.getTvShows(lang, new GetTvShowsLoadDataCallback() {
            @Override
            public void onDataLoaded(List<BaseMovie> tvShowList) {
                callback.onDataLoaded(tvShowList);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }

    @Override
    public void getDetailTvShow(int tvShowId, String lang, final GetDetailDataCallback callback) {
        tvShowDataSource.getDetailTvShow(tvShowId, lang, new GetDetailDataCallback() {
            @Override
            public void onDataLoaded(TvShow tvShow) {
                callback.onDataLoaded(tvShow);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }
}
