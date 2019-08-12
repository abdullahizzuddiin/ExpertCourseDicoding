package id.dicoding.expertcourse.ui.base_movie_list;

import java.util.ArrayList;
import java.util.List;

import id.dicoding.expertcourse.constant.MovieConst;
import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.model.TvShow;
import id.dicoding.expertcourse.repository.movie.MovieDataSource;
import id.dicoding.expertcourse.repository.movie.MovieRepository;
import id.dicoding.expertcourse.repository.tvshow.TvShowDataSource;
import id.dicoding.expertcourse.repository.tvshow.TvShowRepository;

public class BaseMovieListPresenter implements BaseMovieListContract.Presenter, MovieDataSource.LoadDataCallback, TvShowDataSource.LoadDataCallback {
    private final BaseMovieListContract.View view;
    private final MovieRepository movieRepository;
    private final TvShowRepository tvShowRepository;
    private List<BaseMovie> baseMovieList;
    private int movieType;

    public BaseMovieListPresenter(BaseMovieListContract.View view, MovieRepository movieRepository, TvShowRepository tvShowRepository) {
        this.view = view;
        this.movieRepository = movieRepository;
        this.tvShowRepository = tvShowRepository;

        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        movieType = view.getMovieType();
        loadData();
    }

    @Override
    public void onItemClicked(int position) {
        if(movieType == MovieConst.TYPE_MOVIES) {
            Movie movie = (Movie) baseMovieList.get(position);
            view.navigateToDetailView(movie);
        } else {
            TvShow movie = (TvShow) baseMovieList.get(position);
            view.navigateToDetailView(movie);
        }
    }

    private void loadData() {
        if(movieType == MovieConst.TYPE_MOVIES) {
            movieRepository.getMovies(this);
        } else {
            tvShowRepository.getTvShows(this);
        }

    }

    @Override
    public void onDataLoaded(List<BaseMovie> movieList) {
        this.baseMovieList = new ArrayList<>(movieList);
        view.showMovieList(movieList);
    }
}
