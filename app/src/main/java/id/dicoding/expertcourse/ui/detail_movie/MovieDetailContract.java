package id.dicoding.expertcourse.ui.detail_movie;

import id.dicoding.expertcourse.BasePresenter;
import id.dicoding.expertcourse.BaseView;
import id.dicoding.expertcourse.model.Movie;

public class MovieDetailContract {
    interface View extends BaseView<Presenter> {
        void showMovie(Movie movie);

        Movie getMovieExtra() throws NullPointerException;

        void showEmptyView();
    }

    interface Presenter extends BasePresenter {

    }
}
