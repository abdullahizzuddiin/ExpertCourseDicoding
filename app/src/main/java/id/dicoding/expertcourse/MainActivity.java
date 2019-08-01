package id.dicoding.expertcourse;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import id.dicoding.expertcourse.ui.adapter.BaseMoviePagerAdapter;
import id.dicoding.expertcourse.ui.movie_list.MovieListFragment;
import id.dicoding.expertcourse.ui.tvshow_list.TvShowListFragment;

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

        pagerAdapter.addContents(new MovieListFragment(), getString(R.string.movie_view_pager_label));
        pagerAdapter.addContents(new TvShowListFragment(), getString(R.string.tv_show_view_pager_label));
        baseMovieViewPager.setOffscreenPageLimit(2);
        baseMovieViewPager.setAdapter(pagerAdapter);
    }

    private void setupView() {
        baseMovieViewPager = findViewById(R.id.base_movie_viewpager);
    }
}
