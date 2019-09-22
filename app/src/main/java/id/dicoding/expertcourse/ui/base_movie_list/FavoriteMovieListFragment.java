package id.dicoding.expertcourse.ui.base_movie_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import id.dicoding.expertcourse.DetailActivity;
import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.model.FavoriteBaseMovie;
import id.dicoding.expertcourse.ui.adapter.FavoriteMovieListAdapter;
import id.dicoding.expertcourse.util.ItemClickSupport;
import id.dicoding.expertcourse.viewmodel.FavoriteBaseMovieListViewModel;

public class FavoriteMovieListFragment extends Fragment implements ItemClickSupport.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvFailureMessage;
    private FavoriteMovieListAdapter adapter;
    private FavoriteBaseMovieListViewModel baseMovieListViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_movie_list, container, false);
    }
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

    private void subscribeObserver() {
        baseMovieListViewModel = ViewModelProviders.of(this).get(FavoriteBaseMovieListViewModel.class);
        baseMovieListViewModel.getFavoriteBaseMovieList().observe(getViewLifecycleOwner(), onBaseMovieListChanged);
        baseMovieListViewModel.isLoading().observe(getViewLifecycleOwner(), onDataLoadingStatusChanged);
        baseMovieListViewModel.isFailure().observe(getViewLifecycleOwner(), onDataLoadFailureStatusChanged);
    }

    private void initialStart() {

    }

    private void setupView(View view) {
        progressBar = view.findViewById(R.id.pb_base_movie_list);
        recyclerView = view.findViewById(R.id.rv_base_movie_list);
        swipeRefreshLayout = view.findViewById(R.id.srl_base_movie_list);
        tvFailureMessage = view.findViewById(R.id.tv_failure_message_base_movie_list);
    }

    private void setupAdapter() {
        adapter = new FavoriteMovieListAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }

    private void setupListener() {
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        FavoriteBaseMovie favoriteBaseMovie = baseMovieListViewModel.getFavoriteBaseMovieByPosition(position);
        navigateToDetailView(favoriteBaseMovie);
    }

    private void navigateToDetailView(FavoriteBaseMovie favoriteBaseMovie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(getString(R.string.extra_data_base_movie_type), favoriteBaseMovie.getType());
        intent.putExtra(getString(R.string.extra_data_base_movie_title), favoriteBaseMovie.getOriginalTitle());
        intent.putExtra(getString(R.string.extra_data_base_movie_id), favoriteBaseMovie.getId());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        baseMovieListViewModel.getFavoriteBaseMovies();
    }

    private void showMovieList(List<FavoriteBaseMovie> baseMovieList) {
        adapter.clearData();
        adapter.setData(baseMovieList);
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

    private final Observer<List<FavoriteBaseMovie>> onBaseMovieListChanged = new Observer<List<FavoriteBaseMovie>>() {
        @Override
        public void onChanged(List<FavoriteBaseMovie> baseMovieList) {
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
}
