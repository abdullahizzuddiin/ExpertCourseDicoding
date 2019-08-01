package id.dicoding.expertcourse.repository.tvshow;

import java.util.List;

import id.dicoding.expertcourse.model.TvShow;

public class TvShowRepository implements TvShowDataSource {
    private TvShowDataSource inAppDataSource;

    public TvShowRepository(TvShowDataSource dataSource) {
        this.inAppDataSource = dataSource;
    }

    @Override
    public void getTvShows(final LoadDataCallback callback) {
        inAppDataSource.getTvShows(new LoadDataCallback() {
            @Override
            public void onDataLoaded(List<TvShow> tvShowList) {
                callback.onDataLoaded(tvShowList);
            }
        });
    }
}
