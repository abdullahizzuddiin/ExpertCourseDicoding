package id.dicoding.expertcourse.repository.movie;

import androidx.annotation.NonNull;

import java.util.Locale;

import id.dicoding.expertcourse.BuildConfig;
import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.request.ApiUtil;
import id.dicoding.expertcourse.request.MovieDBApi;
import id.dicoding.expertcourse.request.response.DiscoverMovieResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieOnlineDBDataSource implements MovieDataSource {
    private final MovieDBApi movieDbApi;
    private final String API_KEY = BuildConfig.API_KEY;
    private String lang;

    public MovieOnlineDBDataSource() {
        movieDbApi = ApiUtil.getMovieDBService();
        lang = Locale.getDefault().getLanguage();
    }

    @Override
    public void getMovies(final GetMoviesDataCallback callback) {
        movieDbApi.getMovies(API_KEY, lang).enqueue(new Callback<DiscoverMovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<DiscoverMovieResponse> call, @NonNull Response<DiscoverMovieResponse> response) {
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
    public void getDetailMovie(int movieId, final GetDetailDataCallback callback) {
        movieDbApi.getDetailMovie(movieId, API_KEY, lang).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(!response.isSuccessful()) {
                    callback.onFailure();
                    return;
                }

                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
