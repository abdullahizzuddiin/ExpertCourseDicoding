package id.dicoding.expertcourse.repository.movie;

import java.util.List;

import id.dicoding.expertcourse.model.Movie;

public class MovieRepository implements MovieDataSource {
    private MovieDataSource inAppDataSource;

    public MovieRepository(MovieDataSource dataSource) {
        this.inAppDataSource = dataSource;
    }

    @Override
    public void getMovies(final LoadDataCallback callback) {
        inAppDataSource.getMovies(new LoadDataCallback() {
            @Override
            public void onDataLoaded(List<Movie> movieList) {
                callback.onDataLoaded(movieList);
            }
        });
    }
}
