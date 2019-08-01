package id.dicoding.expertcourse.ui.detail_tvshow;

import id.dicoding.expertcourse.model.TvShow;

public class TvShowDetailPresenter implements TvShowDetailContract.Presenter {
    private final TvShowDetailContract.View view;
    private TvShow tvShow;

    public TvShowDetailPresenter(TvShowDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        try {
            tvShow = view.getTvShowExtra();
            view.showTvShow(tvShow);
        } catch (NullPointerException exception) {
            view.showEmptyView();
        }
    }
}
