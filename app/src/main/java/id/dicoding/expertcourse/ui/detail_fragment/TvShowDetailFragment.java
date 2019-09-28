package id.dicoding.expertcourse.ui.detail_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Locale;

import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.model.TvShow;
import id.dicoding.expertcourse.viewmodel.DetailTvShowViewModel;
import id.dicoding.expertcourse.widget.FavoriteMovieWidget;

import static id.dicoding.expertcourse.widget.FavoriteMovieWidget.WIDGET_UPDATE_ACTION;

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
    private Menu menuItem;

    private final Observer<TvShow> onTvShowLoaded = new Observer<TvShow>() {
        @Override
        public void onChanged(TvShow loadedTvShow) {
            showTvShow(loadedTvShow);
        }
    };
    private final Observer<Boolean> onDataLoadingStatusChanged = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isLoading) {
            showLoadingIndicator(isLoading);
        }
    };
    private final Observer<Boolean> onDataLoadFailureStatusChanged = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isFailed) {
            if(isFailed) {
                showLoadingIndicator(false);
                showEmptyView();
            }
        }
    };
    private final Observer<Boolean> onIsFavoriteStatusChanged = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isFavorite) {
            updateFavoriteIcon(isFavorite);
            updateWidget();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detail_menu, menu);
        menuItem = menu;
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_to_favorite) {
            onFavoriteMenuClick();
        }

        return super.onOptionsItemSelected(item);
    }

    private void onFavoriteMenuClick() {
        viewModel.onFavoriteClick();
    }

    private void subscribeObserver() {
        viewModel = ViewModelProviders.of(this).get(DetailTvShowViewModel.class);
        viewModel.getLoadedTvShow().observe(getViewLifecycleOwner(), onTvShowLoaded);
        viewModel.isDataLoading().observe(getViewLifecycleOwner(), onDataLoadingStatusChanged);
        viewModel.isDataLoadFailure().observe(getViewLifecycleOwner(), onDataLoadFailureStatusChanged);
        viewModel.isFavorite().observe(getViewLifecycleOwner(), onIsFavoriteStatusChanged);
    }

    private void initialStart() {
        Locale currentLocale = Locale.getDefault();
        if(viewModel.hasInitiate() && !viewModel.isLangChanged(currentLocale)) {
            return;
        }

        viewModel.setLang(currentLocale.getLanguage());
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
        Glide.with(this)
                .load(tvShow.getBannerUrl())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(bannerIv);
        titleTv.setText(tvShow.getOriginalTitle());
        releaseYearTv.setText(tvShow.getReleaseYear());
        reviewScoreTv.setText(String.valueOf(tvShow.getVoteAverage()));
        voteCountTv.setText(getResources().getQuantityString(R.plurals.vote_count_text, tvShow.getVoteCount(), tvShow.getVoteCount()));
        String overview = tvShow.getOverview().isEmpty() ? getString(R.string.no_overview_text) : tvShow.getOverview();
        overviewTv.setText(overview);
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

    private void updateFavoriteIcon(boolean isFavorite) {
        if(menuItem == null) {
            return;
        }

        int icon = isFavorite ? R.drawable.ic_added_to_favorite : R.drawable.ic_add_to_favorite;
        menuItem.getItem(0).setIcon(ContextCompat.getDrawable(getContext(), icon));
    }

    private void updateWidget() {
        if(getActivity() == null) {
            return;
        }

        Intent i = new Intent(getActivity(), FavoriteMovieWidget.class);
        i.setAction(WIDGET_UPDATE_ACTION);
        getActivity().sendBroadcast(i);

    }

    @Override
    public void onRefresh() {
        viewModel.loadTvShow();
    }
}
