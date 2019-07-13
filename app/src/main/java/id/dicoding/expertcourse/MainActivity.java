package id.dicoding.expertcourse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.dicoding.expertcourse.adapter.MovieListAdapter;
import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.presenter.MainPresenter;
import id.dicoding.expertcourse.presenter_operation.MainPresenterOperation;
import id.dicoding.expertcourse.util.ItemClickSupport;
import id.dicoding.expertcourse.view_operation.MainViewOperation;

public class MainActivity extends AppCompatActivity implements MainViewOperation, ItemClickSupport.OnItemClickListener {
    private RecyclerView movieListRv;
    private MovieListAdapter standardAdapter;
    private MainPresenterOperation presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        setupPresenter();
        presenter.setupData();
    }

    private void setupPresenter() {
        presenter = new MainPresenter(this);
    }


    private void setupView() {
        movieListRv = findViewById(R.id.lv_movie_list);
        ItemClickSupport.addTo(movieListRv).setOnItemClickListener(this);
    }

    @Override
    public void setupAdapter(List<Movie> movieList) {
        standardAdapter = new MovieListAdapter(movieList);
        movieListRv.setAdapter(standardAdapter);
        movieListRv.setLayoutManager(new LinearLayoutManager(this));
        movieListRv.setHasFixedSize(true);
    }

    @Override
    public void navigateToDetailView(Movie movie) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(getString(R.string.extra_data_movie), movie);
        startActivity(intent);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        presenter.onItemClicked(position);
    }
}
