package id.dicoding.expertcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import id.dicoding.expertcourse.model.Movie;

import static id.dicoding.expertcourse.model.MovieSeedData.getSeedData;
import static id.dicoding.expertcourse.util.Format.parseToPerFiveRate;

public class DetailActivity extends AppCompatActivity {
    private ImageView bannerIv;
    private TextView titleTv, releaseYearTv, reviewScoreTv, overviewTv, originalLanguangeTv, runtimeTv, revenueTv, budgetTv;
    private RatingBar reviewScoreRb;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getExtras();
        setupView();
        setupData();
    }

    private void getExtras() {
        movie = getIntent().getParcelableExtra(getString(R.string.extra_data_movie));
    }

    private void setupView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

    private void setupData() {
        setToolbarTitle(movie.getTitle());
        Picasso.get().
                load("file:///android_asset/"+movie.getBanner()).
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
