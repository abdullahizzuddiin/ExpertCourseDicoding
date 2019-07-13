package id.dicoding.expertcourse.view_operation;

import java.util.List;

import id.dicoding.expertcourse.model.Movie;

public interface MainViewOperation {
    void setupAdapter(List<Movie> movieList);

    void navigateToDetailView(Movie movie);
}
