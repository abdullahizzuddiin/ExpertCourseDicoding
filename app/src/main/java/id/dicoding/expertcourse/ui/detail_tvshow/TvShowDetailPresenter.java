package id.dicoding.expertcourse.ui.detail_tvshow;

import id.dicoding.expertcourse.model.TvShow;

public class TvShowDetailPresenter implements TvShowDetailContract.Presenter {
    private final TvShowDetailContract.View view;

    public TvShowDetailPresenter(TvShowDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        try {
            TvShow tvShow = view.getTvShowExtra();
            view.showTvShow(tvShow);
        } catch (NullPointerException exception) {
            view.showEmptyView();
        }
    }
}
