package id.dicoding.expertcourse;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import id.dicoding.expertcourse.constant.MovieConst;
import id.dicoding.expertcourse.ui.adapter.BaseMoviePagerAdapter;
import id.dicoding.expertcourse.ui.base_movie_list.BaseMovieListFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager baseMovieViewPager;
    private BaseMoviePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        setupViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_language_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager() {
        pagerAdapter = new BaseMoviePagerAdapter(getSupportFragmentManager());

        pagerAdapter.addContents(getMovieTypeFragment(), getString(R.string.movie_view_pager_label));
        pagerAdapter.addContents(getTvShowTypeFragment(), getString(R.string.tv_show_view_pager_label));
        baseMovieViewPager.setOffscreenPageLimit(2);
        baseMovieViewPager.setAdapter(pagerAdapter);
    }

    private Fragment getMovieTypeFragment() {
        Bundle extras = new Bundle();
        extras.putInt(getString(R.string.extra_data_base_movie_type), MovieConst.TYPE_MOVIES);

        BaseMovieListFragment fragment = new BaseMovieListFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    private Fragment getTvShowTypeFragment() {
        Bundle extras = new Bundle();
        extras.putInt(getString(R.string.extra_data_base_movie_type), MovieConst.TYPE_TV_SHOWS);

        BaseMovieListFragment fragment = new BaseMovieListFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    private void setupView() {
        baseMovieViewPager = findViewById(R.id.base_movie_viewpager);
    }
}
