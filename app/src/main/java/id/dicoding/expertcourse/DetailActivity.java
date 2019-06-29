package id.dicoding.expertcourse;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.presenter.DetailPresenter;
import id.dicoding.expertcourse.presenter_operation.DetailPresenterOperation;
import id.dicoding.expertcourse.view_operation.DetailViewOperation;

public class DetailActivity extends AppCompatActivity implements DetailViewOperation {
    private ImageView bannerIv;
    private TextView titleTv, releaseYearTv, reviewScoreTv, overviewTv, originalLanguangeTv, runtimeTv, revenueTv, budgetTv;
    private RatingBar reviewScoreRb;

    private DetailPresenterOperation presenter;

    private final String TAG = "DETAIL_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupView();
        setupPresenter();
        presenter.start();
    }

    private void setupPresenter() {
        presenter = new DetailPresenter(this, this);
    }

    private void setupView() {
        showBackButtonNavigation();

        bannerIv = findViewById(R.id.iv_banner_movie_detail);
        titleTv = findViewById(R.id.tv_movie_title_detail);
        releaseYearTv = findViewById(R.id.tv_movie_release_year_detail);
        reviewScoreTv = findViewById(R.id.tv_movie_review_score_text_detail);
        overviewTv = findViewById(R.id.tv_movie_overview_detail);
        originalLanguangeTv = findViewById(R.id.tv_original_language_detail);
        runtimeTv = findViewById(R.id.tv_runtime_detail);
        revenueTv = findViewById(R.id.tv_revenue_detail);
        budgetTv = findViewById(R.id.tv_budget_detail);
        reviewScoreRb = findViewById(R.id.rb_movie_review_score_detail);
    }

    private void showBackButtonNavigation() {
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void showMovie(Movie movie) {
        Picasso.get().
                load("file:///android_asset/" + movie.getBanner()).
                config(Bitmap.Config.RGB_565).
                fit().
                into(bannerIv);
        titleTv.setText(movie.getTitle());
        releaseYearTv.setText(movie.getReleasedYear());
        reviewScoreTv.setText(getString(R.string.rate_text, String.valueOf(movie.getReviewScoreFiveMaxFormat())));
        overviewTv.setText(movie.getOverview());
        originalLanguangeTv.setText(movie.getOriginalLanguage());
        runtimeTv.setText(movie.getRuntime());
        revenueTv.setText(movie.getRevenue());
        budgetTv.setText(movie.getBudget());
        reviewScoreRb.setRating(movie.getReviewScoreFiveMaxFormat());
    }

    /**
     * just for high readability purpose
     *
     * @param title: self explanatory
     */
    @Override
    public void setToolbarTitle(String title) {
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
