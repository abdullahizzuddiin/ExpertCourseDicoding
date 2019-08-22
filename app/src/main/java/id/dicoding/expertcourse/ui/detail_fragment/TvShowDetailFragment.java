package id.dicoding.expertcourse.ui.detail_fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;

import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.model.TvShow;
import id.dicoding.expertcourse.viewmodel.DetailTvShowViewModel;

public class TvShowDetailFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ImageView bannerIv;
    private TextView titleTv,
            releaseYearTv,
            genresTv,
            reviewScoreTv,
            voteCountTv,
            overviewTv,
            originalLanguageTv,
            statusTv;
    private ProgressBar progressBar;
    private ConstraintLayout containerCl;
    private DetailTvShowViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Observer<TvShow> onTvShowLoaded = new Observer<TvShow>() {
        @Override
        public void onChanged(TvShow loadedTvShow) {
            showTvShow(loadedTvShow);
        }
    };
    private Observer<Boolean> onLoadingStatusChanged = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isLoading) {
            showLoadingIndicator(isLoading);
        }
    };
    private Observer<Boolean> onFailureStatusChanged = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isFailed) {
            if(isFailed) {
                swipeRefreshLayout.setRefreshing(false);
                showEmptyView();
            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupView(view);
        setupListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subscribeObserver();
        initialStart();
    }

    private void subscribeObserver() {
        viewModel = ViewModelProviders.of(this).get(DetailTvShowViewModel.class);
        viewModel.getLoadedTvShow().observe(this, onTvShowLoaded);
        viewModel.isLoading().observe(this, onLoadingStatusChanged);
        viewModel.isFailure().observe(this, onFailureStatusChanged);
    }

    private void initialStart() {
        if(viewModel.hasInitiate()) {
            return;
        }

        viewModel.setTvShowId(getTvShowIdExtras());
        viewModel.loadTvShow();
    }

    private int getTvShowIdExtras() throws NullPointerException {
        Bundle extras = getArguments();

        if (extras == null) {
            throw new NullPointerException();
        }

        return extras.getInt(getResources().getString(R.string.extra_data_base_movie_id));
    }

    private void setupView(View view) {
        bannerIv = view.findViewById(R.id.iv_banner_movie_detail);
        titleTv = view.findViewById(R.id.tv_movie_title_detail);
        releaseYearTv = view.findViewById(R.id.tv_movie_release_year_detail);
        genresTv = view.findViewById(R.id.tv_movie_genres_detail);
        reviewScoreTv = view.findViewById(R.id.tv_movie_review_score_text_detail);
        voteCountTv = view.findViewById(R.id.tv_movie_vote_count_text_detail);
        overviewTv = view.findViewById(R.id.tv_movie_overview_detail);
        originalLanguageTv = view.findViewById(R.id.tv_original_language_detail);
        statusTv = view.findViewById(R.id.tv_status_detail);
        progressBar = view.findViewById(R.id.pb_detail_movie);
        containerCl = view.findViewById(R.id.cl_detail_movie);
        swipeRefreshLayout = view.findViewById(R.id.srl_detail_movie);
    }

    private void setupListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void showTvShow(TvShow tvShow) {
        Picasso.get().
                load(tvShow.getBannerUrl()).
                config(Bitmap.Config.RGB_565).
                fit().
                into(bannerIv);
        titleTv.setText(tvShow.getOriginalTitle());
        releaseYearTv.setText(tvShow.getReleaseYear());
        reviewScoreTv.setText(String.valueOf(tvShow.getVoteAverage()));
        voteCountTv.setText(getString(R.string.vote_count_text, tvShow.getVoteCount()));
        overviewTv.setText(tvShow.getOverview());
        originalLanguageTv.setText(tvShow.getDisplayLanguage());
        statusTv.setText(tvShow.getStatus());
        genresTv.setText(tvShow.getGenreListInText());
    }

    private void showEmptyView() {
        titleTv.setText(getString(R.string.no_movie_title));
        bannerIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_local_movies_black_big));
        releaseYearTv.setVisibility(View.GONE);
        reviewScoreTv.setVisibility(View.GONE);
        voteCountTv.setVisibility(View.GONE);
        overviewTv.setText(getString(R.string.dash));
        originalLanguageTv.setText(getString(R.string.dash));
        statusTv.setText(getString(R.string.dash));
    }

    private void showLoadingIndicator(boolean isLoading) {
        if(isLoading) {
            containerCl.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            swipeRefreshLayout.setRefreshing(false);
            containerCl.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRefresh() {
        viewModel.loadTvShow();
    }
}
