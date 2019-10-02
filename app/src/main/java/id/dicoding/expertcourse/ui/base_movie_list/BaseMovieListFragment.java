package id.dicoding.expertcourse.ui.base_movie_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;
import java.util.Locale;

import id.dicoding.expertcourse.DetailActivity;
import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.SearchActivity;
import id.dicoding.expertcourse.SettingActivity;
import id.dicoding.expertcourse.constant.MovieConst;
import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.ui.adapter.MovieListAdapter;
import id.dicoding.expertcourse.util.ItemClickSupport;
import id.dicoding.expertcourse.viewmodel.BaseMovieListViewModel;

public class BaseMovieListFragment extends Fragment implements ItemClickSupport.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvFailureMessage;
    private MovieListAdapter adapter;

    private BaseMovieListViewModel baseMovieListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_movie_list, container, false);
    }
    private final Observer<List<BaseMovie>> onBaseMovieListChanged = new Observer<List<BaseMovie>>() {
        @Override
        public void onChanged(List<BaseMovie> baseMovieList) {
            showMovieList(baseMovieList);
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
                showFailureMessage();
            }
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupView(view);
        setupAdapter();
        setupListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subscribeObserver();
        initialStart();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            navigateToSettingView();
        } else if (item.getItemId() == R.id.action_search) {
            navigateToSearchView();
        }

        return super.onOptionsItemSelected(item);
    }

    private void subscribeObserver() {
        baseMovieListViewModel = ViewModelProviders.of(this).get(BaseMovieListViewModel.class);
        baseMovieListViewModel.getBaseMovieList().observe(getViewLifecycleOwner(), onBaseMovieListChanged);
        baseMovieListViewModel.isLoading().observe(getViewLifecycleOwner(), onDataLoadingStatusChanged);
        baseMovieListViewModel.isFailure().observe(getViewLifecycleOwner(), onDataLoadFailureStatusChanged);
    }

    private void initialStart() {
        Locale currentLocale = Locale.getDefault();
        if(baseMovieListViewModel.hasInitiate() && !baseMovieListViewModel.isLangChanged(currentLocale)) {
            return;
        }

        baseMovieListViewModel.setLang(currentLocale.getLanguage());
        baseMovieListViewModel.setBaseMovieType(getMovieType());
    }

    private void showMovieList(List<BaseMovie> baseMovieList) {
        adapter.clearData();
        adapter.setData(baseMovieList);
    }

    private void setupView(View view) {
        progressBar = view.findViewById(R.id.pb_base_movie_list);
        recyclerView = view.findViewById(R.id.rv_base_movie_list);
        swipeRefreshLayout = view.findViewById(R.id.srl_base_movie_list);
        tvFailureMessage = view.findViewById(R.id.tv_failure_message_base_movie_list);
    }

    private void setupAdapter() {
        adapter = new MovieListAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }

    private void setupListener() {
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void navigateToSettingView() {
        Intent settingActivityIntent = new Intent(getActivity(), SettingActivity.class);
        startActivity(settingActivityIntent);
    }

    private void navigateToSearchView() {
        Intent searchActivityIntent = new Intent(getActivity(), SearchActivity.class);
        searchActivityIntent.putExtra(getString(R.string.extra_data_base_movie_type), getMovieType());
        startActivity(searchActivityIntent);
    }

    private int getMovieType() {
        Bundle extras = getArguments();

        if(extras == null) {
            return MovieConst.TYPE_MOVIES;
        }

        return extras.getInt(getString(R.string.extra_data_base_movie_type), MovieConst.TYPE_MOVIES);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        BaseMovie baseMovie = baseMovieListViewModel.getBaseMovieByPosition(position);
        navigateToDetailView(baseMovie);
    }

    private void navigateToDetailView(BaseMovie baseMovie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(getString(R.string.extra_data_base_movie_type), baseMovieListViewModel.getMovieType());
        intent.putExtra(getString(R.string.extra_data_base_movie_title), baseMovie.getOriginalTitle());
        intent.putExtra(getString(R.string.extra_data_base_movie_id), baseMovie.getId());
        startActivity(intent);
    }

    private void showLoadingIndicator(boolean isLoading) {
        if(isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            tvFailureMessage.setVisibility(View.GONE);
        } else {
            swipeRefreshLayout.setRefreshing(false);
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            tvFailureMessage.setVisibility(View.GONE);
        }
    }

    private void showFailureMessage() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        tvFailureMessage.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        baseMovieListViewModel.setBaseMovieType(baseMovieListViewModel.getMovieType());
    }
}
