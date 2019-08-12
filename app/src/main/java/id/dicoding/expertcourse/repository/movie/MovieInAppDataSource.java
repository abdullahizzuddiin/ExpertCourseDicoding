package id.dicoding.expertcourse.repository.movie;

import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.repository.seed_data_provider.MovieSeedData;

public class MovieInAppDataSource implements MovieDataSource {
    @Override
    public void getMovies(LoadDataCallback callback) {
        List<BaseMovie> movieList = MovieSeedData.getSeedData();
        callback.onDataLoaded(movieList);
    }
}
