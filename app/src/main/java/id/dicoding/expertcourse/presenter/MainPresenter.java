package id.dicoding.expertcourse.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import id.dicoding.expertcourse.DetailActivity;
import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.model.MovieSeedData;
import id.dicoding.expertcourse.presenter_operation.MainPresenterOperation;
import id.dicoding.expertcourse.view_operation.MainViewOperation;

public class MainPresenter implements MainPresenterOperation {
    private Context context;
    private MainViewOperation mainView;
    private List<Movie> movieList;

    public MainPresenter(Context context, MainViewOperation mainView) {
        this.context = context;
        this.mainView = mainView;
    }

    @Override
    public void setupData() {
        movieList = MovieSeedData.getSeedData();
        mainView.setupAdapter(movieList);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie movie = (Movie) parent.getItemAtPosition(position);
        navigateToDetailView(movie);
    }

    private void navigateToDetailView(Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(context.getString(R.string.extra_data_movie), movie);
        context.startActivity(intent);
    }
}
