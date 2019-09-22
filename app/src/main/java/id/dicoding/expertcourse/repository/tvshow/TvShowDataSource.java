package id.dicoding.expertcourse.repository.tvshow;

import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.TvShow;
import id.dicoding.expertcourse.repository.BaseLoadDataCallback;

public interface TvShowDataSource {
    void getTvShows(String lang, GetTvShowsLoadDataCallback callback);

    void getDetailTvShow(int tvShowId, String lang, GetDetailDataCallback callback);

    interface GetTvShowsLoadDataCallback extends BaseLoadDataCallback<List<BaseMovie>> {}

    interface GetDetailDataCallback extends BaseLoadDataCallback<TvShow> {}
}
