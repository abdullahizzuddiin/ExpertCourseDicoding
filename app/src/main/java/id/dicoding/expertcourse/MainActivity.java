package id.dicoding.expertcourse;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import id.dicoding.expertcourse.adapter.MovieListStandardAdapter;
import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.presenter.MainPresenter;
import id.dicoding.expertcourse.presenter_operation.MainPresenterOperation;
import id.dicoding.expertcourse.view_operation.MainViewOperation;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, MainViewOperation {
    private ListView movieListLv;
    private MovieListStandardAdapter standardAdapter;
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
        presenter = new MainPresenter(this, this);
    }


    private void setupView() {
        movieListLv = findViewById(R.id.lv_movie_list);
        movieListLv.setOnItemClickListener(this);
    }

    @Override
    public void setupAdapter(List<Movie> movieList) {
        standardAdapter = new MovieListStandardAdapter(this);
        standardAdapter.setData(movieList);
        movieListLv.setAdapter(standardAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClick(parent, view, position, id);
    }
}
