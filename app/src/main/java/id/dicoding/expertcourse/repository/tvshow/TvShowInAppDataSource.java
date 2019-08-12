package id.dicoding.expertcourse.repository.tvshow;

import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.repository.seed_data_provider.TvShowSeedData;

public class TvShowInAppDataSource implements TvShowDataSource {
    @Override
    public void getTvShows(LoadDataCallback callback) {
        List<BaseMovie> tvShowList = TvShowSeedData.getSeedData();
        callback.onDataLoaded(tvShowList);
    }
}
