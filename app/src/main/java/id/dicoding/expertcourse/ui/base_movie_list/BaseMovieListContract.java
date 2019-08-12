package id.dicoding.expertcourse.ui.base_movie_list;

import java.util.List;

import id.dicoding.expertcourse.BasePresenter;
import id.dicoding.expertcourse.BaseView;
import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.model.TvShow;

public class BaseMovieListContract {
    interface View extends BaseView<Presenter> {
        void navigateToDetailView(Movie movie);

        void navigateToDetailView(TvShow tvShow);

        void showMovieList(List<BaseMovie> baseMovieList);

        int getMovieType();
    }

    interface Presenter extends BasePresenter {
        void onItemClicked(int position);
    }
}
