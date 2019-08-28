package id.dicoding.expertcourse.repository.movie;

import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.Movie;

public interface MovieDataSource {
    void getMovies(String lang, GetMoviesDataCallback callback);

    void getDetailMovie(int movieId, String lang, GetDetailDataCallback callback);

    interface GetMoviesDataCallback {
        void onDataLoaded(List<BaseMovie> movieList);

        void onFailure();
    }

    interface GetDetailDataCallback {
        void onDataLoaded(Movie movie);

        void onFailure();
    }
}
