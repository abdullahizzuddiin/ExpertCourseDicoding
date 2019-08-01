package id.dicoding.expertcourse.ui.detail_tvshow;

import id.dicoding.expertcourse.BasePresenter;
import id.dicoding.expertcourse.BaseView;
import id.dicoding.expertcourse.model.TvShow;

public class TvShowDetailContract {
    interface View extends BaseView<Presenter> {
        void showTvShow(TvShow tvShow);

        TvShow getTvShowExtra() throws NullPointerException;

        void showEmptyView();
    }

    interface Presenter extends BasePresenter {

    }
}
