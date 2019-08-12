package id.dicoding.expertcourse.repository.movie;

import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;

public interface MovieDataSource {
    void getMovies(LoadDataCallback callback);

    interface LoadDataCallback {
        void onDataLoaded(List<BaseMovie> movieList);
    }
}
