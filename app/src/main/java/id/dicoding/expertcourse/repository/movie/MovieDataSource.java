package id.dicoding.expertcourse.repository.movie;

import java.util.List;

import id.dicoding.expertcourse.model.Movie;

public interface MovieDataSource {
    void getMovies(LoadDataCallback callback);

    interface LoadDataCallback {
        void onDataLoaded(List<Movie> movieList);
    }
}
