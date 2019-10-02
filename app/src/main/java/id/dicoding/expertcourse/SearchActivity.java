package id.dicoding.expertcourse;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;
import java.util.Locale;

import id.dicoding.expertcourse.constant.MovieConst;
import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.ui.adapter.MovieListAdapter;
import id.dicoding.expertcourse.util.ItemClickSupport;
import id.dicoding.expertcourse.viewmodel.SearchViewModel;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener, ItemClickSupport.OnItemClickListener {

    private static final String SEARCH_QUERY_EXTRA = "search_query_extra";
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvFailureMessage;
    private TextView tvEmptyMessage;
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
                showFailureMessage();
            }
        }
    };
    private SearchView searchView;
    private MovieListAdapter adapter;
    private final Observer<List<BaseMovie>> onBaseMovieListChanged = new Observer<List<BaseMovie>>() {
        @Override
        public void onChanged(List<BaseMovie> baseMovieList) {
            if(baseMovieList.size() == 0) {
                showEmptyMessage();
            } else {
                showMovieList(baseMovieList);
            }
        }
    };
    private SearchViewModel viewModel;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupView();
        setupAdapter();
        setupListener();
        subscribeObserver();
        initiateViewModelConstant();
        retainLastQuery(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String query = searchView.getQuery().toString();
        outState.putString(SEARCH_QUERY_EXTRA, query);
    }

    private void initiateViewModelConstant() {
        Locale currentLocale = Locale.getDefault();
        viewModel.setLang(currentLocale.getLanguage());
        viewModel.setMovieType(getMovieType());
    }

    private void setupView() {
        progressBar = findViewById(R.id.pb_base_movie_list);
        recyclerView = findViewById(R.id.rv_base_movie_list);
        swipeRefreshLayout = findViewById(R.id.srl_base_movie_list);
        tvFailureMessage = findViewById(R.id.tv_failure_message_base_movie_list);
        tvEmptyMessage = findViewById(R.id.tv_empty_message_base_movie_list);
        showBackButtonNavigation();
        setToolbarTitle();
    }

    private void setupAdapter() {
        adapter = new MovieListAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    private void setupListener() {
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void showMovieList(List<BaseMovie> baseMovieList) {
        adapter.clearData();
        adapter.setData(baseMovieList);
    }

    private void subscribeObserver() {
        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        viewModel.getBaseMovieList().observe(this, onBaseMovieListChanged);
        viewModel.isLoading().observe(this, onDataLoadingStatusChanged);
        viewModel.isFailure().observe(this, onDataLoadFailureStatusChanged);
    }

    private void retainLastQuery(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }

        query = savedInstanceState.getString(SEARCH_QUERY_EXTRA);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        setupSearchView(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            onBackPressed();
        } else if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showBackButtonNavigation() {
        if(getSupportActionBar() == null) {
            return;
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * just for high readability purpose
     *
     */
    private void setToolbarTitle() {
        setTitle(R.string.search_activity_title);
    }

    private void setupSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if(searchManager == null) {
            return;
        }

        searchView = (SearchView) (menu.findItem(R.id.action_search)).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(false);
        searchView.requestFocusFromTouch();

        if(query != null && !query.isEmpty())
            searchView.setQuery(query, false);
    }

    private int getMovieType() {
        Bundle extras = getIntent().getExtras();

        if(extras == null) {
            return MovieConst.TYPE_MOVIES;
        }

        return extras.getInt(getString(R.string.extra_data_base_movie_type), MovieConst.TYPE_MOVIES);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        viewModel.searchMovie(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        query = newText;
        return false;
    }

    @Override
    public void onRefresh() {
        viewModel.searchMovie(query);
    }

    private void showLoadingIndicator(boolean isLoading) {
        if(isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            tvFailureMessage.setVisibility(View.GONE);
            tvEmptyMessage.setVisibility(View.GONE);
        } else {
            swipeRefreshLayout.setRefreshing(false);
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            tvFailureMessage.setVisibility(View.GONE);
            tvEmptyMessage.setVisibility(View.GONE);
        }
    }

    private void showFailureMessage() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        tvFailureMessage.setVisibility(View.VISIBLE);
        tvEmptyMessage.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void showEmptyMessage() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        tvFailureMessage.setVisibility(View.GONE);
        tvEmptyMessage.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        BaseMovie baseMovie = viewModel.getBaseMovieByPosition(position);
        navigateToDetailView(baseMovie);
    }

    private void navigateToDetailView(BaseMovie baseMovie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(getString(R.string.extra_data_base_movie_type), getMovieType());
        intent.putExtra(getString(R.string.extra_data_base_movie_title), baseMovie.getOriginalTitle());
        intent.putExtra(getString(R.string.extra_data_base_movie_id), baseMovie.getId());
        startActivity(intent);
    }
}
