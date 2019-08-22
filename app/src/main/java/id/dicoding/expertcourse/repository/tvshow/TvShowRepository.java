package id.dicoding.expertcourse.repository.tvshow;

import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.TvShow;

public class TvShowRepository implements TvShowDataSource {
    private TvShowDataSource tvShowDataSource;

    public TvShowRepository(TvShowDataSource dataSource) {
        this.tvShowDataSource = dataSource;
    }

    @Override
    public void getTvShows(final GetTvShowsLoadDataCallback callback) {
        tvShowDataSource.getTvShows(new GetTvShowsLoadDataCallback() {
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
    public void getDetailTvShow(int tvShowId, final GetDetailDataCallback callback) {
        tvShowDataSource.getDetailTvShow(tvShowId, new GetDetailDataCallback() {
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
