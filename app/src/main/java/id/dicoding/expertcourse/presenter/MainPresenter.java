package id.dicoding.expertcourse.presenter;

import java.util.List;

import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.model.MovieSeedData;
import id.dicoding.expertcourse.presenter_operation.MainPresenterOperation;
import id.dicoding.expertcourse.view_operation.MainViewOperation;

public class MainPresenter implements MainPresenterOperation {
    private MainViewOperation mainView;
    private List<Movie> movieList;

    public MainPresenter(MainViewOperation mainView) {
        this.mainView = mainView;
    }

    @Override
    public void setupData() {
        movieList = MovieSeedData.getSeedData();
        mainView.setupAdapter(movieList);
    }

    @Override
    public void onItemClicked(int position) {
        Movie movie = movieList.get(position);
        mainView.navigateToDetailView(movie);
    }
}
