package id.dicoding.expertcourse.ui.movie_list;

import java.util.List;

import id.dicoding.expertcourse.BasePresenter;
import id.dicoding.expertcourse.BaseView;
import id.dicoding.expertcourse.model.Movie;

public class MovieListContract {
    interface View extends BaseView<Presenter> {
        void navigateToDetailView(Movie movie);

        void showMovieList(List<Movie> movieList);
    }

    interface Presenter extends BasePresenter {
        void onItemClicked(int position);
    }
}
