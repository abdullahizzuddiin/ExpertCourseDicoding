package id.dicoding.expertcourse.ui.detail_tvshow;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.model.TvShow;

public class TvShowDetailFragment extends Fragment implements TvShowDetailContract.View {
    private ImageView bannerIv;
    private TextView titleTv, releaseYearTv, reviewScoreTv, reviewScoreBasisTv, overviewTv,
            originalLanguageTv, runtimeTv, statusTv;
    private RatingBar reviewScoreRb;
    private TvShowDetailContract.Presenter presenter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        new TvShowDetailPresenter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupView(view);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    public void setPresenter(TvShowDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public TvShow getTvShowExtra() throws NullPointerException {
        Bundle extras = getArguments();

        if (extras == null) {
            throw new NullPointerException();
        }

        return extras.getParcelable(getString(R.string.extra_data_movie));
    }

    private void setupView(View view) {
        bannerIv = view.findViewById(R.id.iv_banner_movie_detail);
        titleTv = view.findViewById(R.id.tv_movie_title_detail);
        releaseYearTv = view.findViewById(R.id.tv_movie_release_year_detail);
        reviewScoreTv = view.findViewById(R.id.tv_movie_review_score_text_detail);
        reviewScoreBasisTv = view.findViewById(R.id.tv_movie_review_score_basis);
        overviewTv = view.findViewById(R.id.tv_movie_overview_detail);
        originalLanguageTv = view.findViewById(R.id.tv_original_language_detail);
        runtimeTv = view.findViewById(R.id.tv_runtime_detail);
        statusTv = view.findViewById(R.id.tv_status_detail);
        reviewScoreRb = view.findViewById(R.id.rb_movie_review_score_detail);
    }

    @Override
    public void showTvShow(TvShow tvShow) {
        Picasso.get().
                load("file:///android_asset/" + tvShow.getBanner()).
                config(Bitmap.Config.RGB_565).
                fit().
                into(bannerIv);
        titleTv.setText(tvShow.getTitle());
        releaseYearTv.setText(tvShow.getReleasedYear());
        reviewScoreTv.setText(String.valueOf(tvShow.getReviewScoreTenMaxFormat()));
        overviewTv.setText(tvShow.getOverview());
        originalLanguageTv.setText(tvShow.getOriginalLanguage());
        runtimeTv.setText(tvShow.getRuntime());
        statusTv.setText(getResources().getStringArray(R.array.tv_show_status_text)[tvShow.getStatus()]);
        reviewScoreRb.setRating(tvShow.getReviewScoreFiveMaxFormat());
    }

    @Override
    public void showEmptyView() {
        titleTv.setText(getString(R.string.no_movie_title));
        bannerIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_local_movies_black_big));
        reviewScoreRb.setVisibility(View.GONE);
        reviewScoreBasisTv.setVisibility(View.GONE);
        reviewScoreTv.setVisibility(View.GONE);
        overviewTv.setText(getString(R.string.dash));
        originalLanguageTv.setText(getString(R.string.dash));
        runtimeTv.setText(getString(R.string.dash));
        statusTv.setText(getString(R.string.dash));
    }
}
