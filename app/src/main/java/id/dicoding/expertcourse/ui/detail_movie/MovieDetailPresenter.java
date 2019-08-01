package id.dicoding.expertcourse.ui.detail_movie;

import id.dicoding.expertcourse.model.Movie;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {
    private final MovieDetailContract.View view;
    private Movie movie;

    public MovieDetailPresenter(MovieDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        try {
            movie = view.getMovieExtra();
            view.showMovie(movie);
        } catch (NullPointerException exception) {
            view.showEmptyView();
        }
    }
}
