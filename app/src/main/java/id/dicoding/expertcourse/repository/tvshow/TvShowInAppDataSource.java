package id.dicoding.expertcourse.repository.tvshow;

import java.util.List;

import id.dicoding.expertcourse.model.TvShow;
import id.dicoding.expertcourse.repository.seed_data_provider.TvShowSeedData;

public class TvShowInAppDataSource implements TvShowDataSource {
    @Override
    public void getTvShows(LoadDataCallback callback) {
        List<TvShow> tvShowList = TvShowSeedData.getSeedData();
        callback.onDataLoaded(tvShowList);
    }
}
