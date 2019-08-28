package id.dicoding.expertcourse.repository.tvshow;

import id.dicoding.expertcourse.BuildConfig;
import id.dicoding.expertcourse.model.TvShow;
import id.dicoding.expertcourse.request.ApiUtil;
import id.dicoding.expertcourse.request.MovieDBApi;
import id.dicoding.expertcourse.request.response.DiscoverMovieResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowOnlineDBDataSource implements TvShowDataSource {
    private final MovieDBApi movieDbApi;
    private final String API_KEY = BuildConfig.API_KEY;

    public TvShowOnlineDBDataSource() {
        this.movieDbApi = ApiUtil.getMovieDBService();
    }

    @Override
    public void getTvShows(String lang, final GetTvShowsLoadDataCallback callback) {
        movieDbApi.getTvShow(API_KEY, lang).enqueue(new Callback<DiscoverMovieResponse>() {
            @Override
            public void onResponse(Call<DiscoverMovieResponse> call, Response<DiscoverMovieResponse> response) {
                if(!response.isSuccessful()) {
                    callback.onFailure();
                    return;
                }

                callback.onDataLoaded(response.body().getResults());
            }

            @Override
            public void onFailure(Call<DiscoverMovieResponse> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    @Override
    public void getDetailTvShow(int tvShowId, String lang, final GetDetailDataCallback callback) {
        movieDbApi.getDetailTvShow(tvShowId, API_KEY, lang).enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                if(!response.isSuccessful()) {
                    callback.onFailure();
                    return;
                }

                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
