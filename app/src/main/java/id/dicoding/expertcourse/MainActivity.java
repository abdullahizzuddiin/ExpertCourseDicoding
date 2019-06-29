package id.dicoding.expertcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import id.dicoding.expertcourse.adapter.MovieListStandardAdapter;
import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.model.MovieSeedData;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView movieListLv;
    private MovieListStandardAdapter standardAdapter;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupData();
        setupView();
        setupAdapter();
    }

    private void setupData() {
        movieList = MovieSeedData.getSeedData();
    }

    private void setupView() {
        movieListLv = findViewById(R.id.lv_movie_list);
        movieListLv.setOnItemClickListener(this);
    }

    private void setupAdapter() {
        standardAdapter = new MovieListStandardAdapter(this);
        standardAdapter.setData(movieList);
        movieListLv.setAdapter(standardAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie movie = (Movie) parent.getItemAtPosition(position);

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(getString(R.string.extra_data_movie), movie);
        startActivity(intent);
    }
}
