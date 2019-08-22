package id.dicoding.expertcourse.repository.movie;

import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.Movie;

public class MovieRepository implements MovieDataSource {
    private MovieDataSource movieDataSource;

    public MovieRepository(MovieDataSource dataSource) {
        this.movieDataSource = dataSource;
    }

    @Override
    public void getMovies(final GetMoviesDataCallback callback) {
        movieDataSource.getMovies(new GetMoviesDataCallback() {
            @Override
            public void onDataLoaded(List<BaseMovie> movieList) {
                callback.onDataLoaded(movieList);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }

    @Override
    public void getDetailMovie(int movieId, final GetDetailDataCallback callback) {
        movieDataSource.getDetailMovie(movieId, new GetDetailDataCallback() {
            @Override
            public void onDataLoaded(Movie movie) {
                callback.onDataLoaded(movie);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }
}
