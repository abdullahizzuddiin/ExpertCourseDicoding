package id.dicoding.expertcourse.request;

import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.model.TvShow;
import id.dicoding.expertcourse.request.response.DiscoverMovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDBApi {

    @GET("discover/movie")
    Call<DiscoverMovieResponse> getMovies(
            @Query("api_key") String key,
            @Query("language") String lang);

    @GET("discover/movie")
    Call<DiscoverMovieResponse> getReleaseTodayMovies(
            @Query("api_key") String key,
            @Query("language") String lang,
            @Query("primary_release_date.gte") String primaryReleaseDateGTE,
            @Query("primary_release_date.lte") String primaryReleaseDateLTE);

    @GET("discover/tv")
    Call<DiscoverMovieResponse> getTvShow(
            @Query("api_key") String key,
            @Query("language") String lang);

    @GET("movie/{movieId}")
    Call<Movie> getDetailMovie(
            @Path("movieId") int movieId,
            @Query("api_key") String key,
            @Query("language") String lang);

    @GET("tv/{tvId}")
    Call<TvShow> getDetailTvShow(
            @Path("tvId") int tvId,
            @Query("api_key") String key,
            @Query("language") String lang);

}
