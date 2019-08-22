package id.dicoding.expertcourse;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import id.dicoding.expertcourse.constant.MovieConst;
import id.dicoding.expertcourse.ui.detail_fragment.MovieDetailFragment;
import id.dicoding.expertcourse.ui.detail_fragment.TvShowDetailFragment;

public class DetailActivity extends AppCompatActivity {
    private final String TAG = DetailActivity.class.getName();
    private int movieType;
    private final String FRAGMENT_TAG = "detailFragment";
    private String title;
    private int baseMovieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getExtras();
        setupView();
        installFragment(savedInstanceState);
    }

    private void getExtras() {
        movieType = getIntent().getIntExtra(getString(R.string.extra_data_base_movie_type), MovieConst.TYPE_MOVIES);
        title = getIntent().getStringExtra(getString(R.string.extra_data_base_movie_title));
        baseMovieId = getIntent().getIntExtra(getString(R.string.extra_data_base_movie_id), 0);
    }

    private void setupView() {
        showBackButtonNavigation();
        setToolbarTitle(title);
    }

    private void installFragment(Bundle savedInstanceState) {
        if(savedInstanceState == null) {
            Fragment detailFragment = getFragmentByMovieType();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.detail_frame_container, detailFragment, FRAGMENT_TAG);
            fragmentTransaction.commit();
        }
    }

    private Fragment getFragmentByMovieType() {
        if (movieType == MovieConst.TYPE_MOVIES) {
            return getMovieDetailFragment();
        } else {
            return getTvShowDetailFragment();
        }
    }

    private Fragment getMovieDetailFragment() {
        Bundle extras = new Bundle();
        extras.putInt(getString(R.string.extra_data_base_movie_id), baseMovieId);

        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    private Fragment getTvShowDetailFragment() {
        Bundle extras = new Bundle();
        extras.putInt(getString(R.string.extra_data_base_movie_id), baseMovieId);

        TvShowDetailFragment fragment = new TvShowDetailFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    private void showBackButtonNavigation() {
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * just for high readability purpose
     *
     * @param title: self explanatory
     */
    private void setToolbarTitle(String title) {
        setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            onBackPressed();
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
