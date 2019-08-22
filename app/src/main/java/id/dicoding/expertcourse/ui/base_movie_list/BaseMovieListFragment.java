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
import id.dicoding.expertcourse.constant.MovieConst;
import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.ui.adapter.MovieListAdapter;
import id.dicoding.expertcourse.util.ItemClickSupport;
import id.dicoding.expertcourse.viewmodel.BaseMovieListViewModel;

//import id.dicoding.expertcourse.DetailActivity;

public class BaseMovieListFragment extends Fragment implements ItemClickSupport.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvFailureMessage;
    private MovieListAdapter adapter;

    private BaseMovieListViewModel baseMovieListViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_movie_list, container, false);
    }
    private Observer<List<BaseMovie>> onBaseMovieListChanged = new Observer<List<BaseMovie>>() {
        @Override
        public void onChanged(List<BaseMovie> baseMovieList) {
            showMovieList(baseMovieList);
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
                showFailureMessage();
            }
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupView(view);
        setupAdapter();;
        setupListener();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subscribeObserver();
        initialStart();
    }

    private void subscribeObserver() {
        baseMovieListViewModel = ViewModelProviders.of(this).get(BaseMovieListViewModel.class);
        baseMovieListViewModel.getBaseMovieList().observe(this, onBaseMovieListChanged);
        baseMovieListViewModel.isLoading().observe(this, onLoadingStatusChanged);
        baseMovieListViewModel.isFailure().observe(this, onFailureStatusChanged);
    }

    private void initialStart() {
        if(baseMovieListViewModel.hasInitiate()) {
            return;
        }

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
