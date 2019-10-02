package id.dicoding.expertcourse.repository.tvshow;

public class TvShowRepository implements TvShowDataSource {
    private final TvShowDataSource tvShowDataSource;

    public TvShowRepository(TvShowDataSource dataSource) {
        this.tvShowDataSource = dataSource;
    }

    @Override
    public void getTvShows(String lang, final GetTvShowsLoadDataCallback callback) {
        tvShowDataSource.getTvShows(lang, callback);
    }

    @Override
    public void getDetailTvShow(int tvShowId, String lang, final GetDetailDataCallback callback) {
        tvShowDataSource.getDetailTvShow(tvShowId, lang, callback);
    }

    @Override
    public void searchTvShows(String lang, String query, GetTvShowsLoadDataCallback callback) {
        tvShowDataSource.searchTvShows(lang, query, callback);
    }
}
