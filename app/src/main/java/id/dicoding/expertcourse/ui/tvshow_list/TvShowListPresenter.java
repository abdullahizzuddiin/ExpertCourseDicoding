package id.dicoding.expertcourse.ui.tvshow_list;

import java.util.ArrayList;
import java.util.List;

import id.dicoding.expertcourse.model.TvShow;
import id.dicoding.expertcourse.repository.tvshow.TvShowDataSource;
import id.dicoding.expertcourse.repository.tvshow.TvShowRepository;

public class TvShowListPresenter implements TvShowListContract.Presenter, TvShowDataSource.LoadDataCallback {
    private final TvShowListContract.View view;
    private final TvShowRepository repository;
    private List<TvShow> tvShowList;

    public TvShowListPresenter(TvShowListContract.View view, TvShowRepository repository) {
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
        TvShow movie = tvShowList.get(position);
        view.navigateToDetailView(movie);
    }

    private void loadData() {
        repository.getTvShows(this);
    }

    @Override
    public void onDataLoaded(List<TvShow> tvShowList) {
        this.tvShowList = new ArrayList<>(tvShowList);
        view.showTvShowList(tvShowList);
    }
}
