package id.dicoding.expertcourse.repository.movie;

import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.repository.BaseLoadDataCallback;

public interface MovieDataSource {
    void getMovies(String lang, GetMoviesDataCallback callback);

    void getReleaseTodayMovies(String lang, String primaryReleaseDateGTE, String primaryReleaseDateLTE, GetMoviesDataCallback callback);

    void getDetailMovie(int movieId, String lang, GetDetailDataCallback callback);

    void searchMovies(String lang, String query, GetMoviesDataCallback callback);

    interface GetMoviesDataCallback extends BaseLoadDataCallback<List<BaseMovie>> {}

    interface GetDetailDataCallback extends BaseLoadDataCallback<Movie> {}
}
