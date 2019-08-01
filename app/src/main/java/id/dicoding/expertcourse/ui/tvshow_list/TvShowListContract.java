package id.dicoding.expertcourse.ui.tvshow_list;

import java.util.List;

import id.dicoding.expertcourse.BasePresenter;
import id.dicoding.expertcourse.BaseView;
import id.dicoding.expertcourse.model.TvShow;

public class TvShowListContract {
    interface View extends BaseView<Presenter> {
        void navigateToDetailView(TvShow tvShow);

        void showTvShowList(List<TvShow> tvShowList);
    }

    interface Presenter extends BasePresenter {

        void onItemClicked(int position);
    }
}
