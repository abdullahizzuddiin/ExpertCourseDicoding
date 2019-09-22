package id.dicoding.expertcourse.ui.detail_fragment;

import android.graphics.Bitmap;
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

import com.squareup.picasso.Picasso;

import java.util.Locale;

import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.viewmodel.DetailMovieViewModel;

import static id.dicoding.expertcourse.util.CurrencyFormatter.toDollar;

public class MovieDetailFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ImageView bannerIv;
    private TextView titleTv,
            releaseYearTv,
            genresTv,
            reviewScoreTv,
            voteCountTv,
            overviewTv,
            originalLanguageTv,
            runtimeTv,
            revenueTv,
            budgetTv;
    private ProgressBar progressBar;
    private ConstraintLayout containerCl;
    private DetailMovieViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Menu menuItem;
    private final Observer<Movie> onMovieLoaded = new Observer<Movie>() {
        @Override
        public void onChanged(Movie loadedMovie) {
            showMovie(loadedMovie);
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
                swipeRefreshLayout.setRefreshing(false);
                showEmptyView();
            }
        }
    };
    private final Observer<Boolean> onIsFavoriteStatusChanged = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isFavorite) {
            updateFavoriteIcon(isFavorite);
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
        return inflater.inflate(R.layout.fragment_detail_movie, container, false);
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
        updateFavoriteIcon(viewModel.getFavoriteStatus());
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
        viewModel = ViewModelProviders.of(this).get(DetailMovieViewModel.class);
        viewModel.getLoadedMovie().observe(getViewLifecycleOwner(), onMovieLoaded);
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
        viewModel.setMovieId(getMovieIdExtras());
        viewModel.loadMovie();
    }

    private int getMovieIdExtras() throws NullPointerException {
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
        reviewScoreTv = view.findViewById(R.id.tv_movie_review_score_text_detail);
        voteCountTv = view.findViewById(R.id.tv_movie_vote_count_text_detail);
        overviewTv = view.findViewById(R.id.tv_movie_overview_detail);
        originalLanguageTv = view.findViewById(R.id.tv_original_language_detail);
        runtimeTv = view.findViewById(R.id.tv_runtime_detail);
        revenueTv = view.findViewById(R.id.tv_revenue_detail);
        budgetTv = view.findViewById(R.id.tv_budget_detail);
        genresTv = view.findViewById(R.id.tv_movie_genres_detail);
        progressBar = view.findViewById(R.id.pb_detail_movie);
        containerCl = view.findViewById(R.id.cl_detail_movie);
        swipeRefreshLayout = view.findViewById(R.id.srl_detail_movie);
    }

    private void setupListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
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

    private void showEmptyView() {
        bannerIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_local_movies_black_big));
        titleTv.setText(getString(R.string.no_movie_title));
        releaseYearTv.setVisibility(View.GONE);
        reviewScoreTv.setVisibility(View.GONE);
        voteCountTv.setVisibility(View.GONE);
        overviewTv.setText(getString(R.string.dash));
        originalLanguageTv.setText(getString(R.string.dash));
        runtimeTv.setText(getString(R.string.dash));
        revenueTv.setText(getString(R.string.dash));
        budgetTv.setText(getString(R.string.dash));
    }

    private void showMovie(Movie movie) {
        Picasso.get().
                load(movie.getBannerUrl()).
                config(Bitmap.Config.RGB_565).
                fit().
                into(bannerIv);
        titleTv.setText(movie.getOriginalTitle());
        releaseYearTv.setText(getString(R.string.text_with_bracket, movie.getReleaseYear()));
        reviewScoreTv.setText(String.valueOf(movie.getVoteAverage()));
        voteCountTv.setText(getResources().getQuantityString(R.plurals.vote_count_text, movie.getVoteCount(), movie.getVoteCount()));
        String overview = movie.getOverview().isEmpty() ? getString(R.string.no_overview_text) : movie.getOverview();
        overviewTv.setText(overview);
        originalLanguageTv.setText(movie.getDisplayLanguage());
        runtimeTv.setText(getResources().getQuantityString(R.plurals.runtime_format, movie.getRuntime(),movie.getRuntime()));
        revenueTv.setText(toDollar(movie.getRevenue()));
        budgetTv.setText(toDollar(movie.getBudget()));
        genresTv.setText(movie.getGenreListInText());
    }

    private void updateFavoriteIcon(boolean isFavorite) {
        if(menuItem == null) {
            return;
        }

        int icon = isFavorite ? R.drawable.ic_added_to_favorite : R.drawable.ic_add_to_favorite;
        menuItem.getItem(0).setIcon(ContextCompat.getDrawable(getContext(), icon));
    }

    @Override
    public void onRefresh() {
        viewModel.loadMovie();
    }
}
