package id.dicoding.expertcourse.ui.movie_list;

import java.util.ArrayList;
import java.util.List;

import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.repository.movie.MovieDataSource;
import id.dicoding.expertcourse.repository.movie.MovieRepository;

public class MovieListPresenter implements MovieListContract.Presenter, MovieDataSource.LoadDataCallback {
    private final MovieListContract.View view;
    private final MovieRepository repository;
    private List<Movie> movieList;

    public MovieListPresenter(MovieListContract.View view, MovieRepository repository) {
        this.view = view;
        this.repository = repository;

        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadData();
    }

    @Override
    public void onItemClicked(int position) {
        Movie movie = movieList.get(position);
        view.navigateToDetailView(movie);
    }

    private void loadData() {
        repository.getMovies(this);
    }

    @Override
    public void onDataLoaded(List<Movie> movieList) {
        this.movieList = new ArrayList<>(movieList);
        view.showMovieList(movieList);
    }
}
