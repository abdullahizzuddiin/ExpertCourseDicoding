package id.dicoding.expertcourse.repository.tvshow;

import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;

public class TvShowRepository implements TvShowDataSource {
    private TvShowDataSource inAppDataSource;

    public TvShowRepository(TvShowDataSource dataSource) {
        this.inAppDataSource = dataSource;
    }

    @Override
    public void getTvShows(final LoadDataCallback callback) {
        inAppDataSource.getTvShows(new LoadDataCallback() {
            @Override
            public void onDataLoaded(List<BaseMovie> tvShowList) {
                callback.onDataLoaded(tvShowList);
            }
        });
    }
}
