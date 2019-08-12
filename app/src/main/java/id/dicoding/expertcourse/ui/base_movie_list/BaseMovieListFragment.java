package id.dicoding.expertcourse.ui.base_movie_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.dicoding.expertcourse.DetailActivity;
import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.constant.MovieConst;
import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.model.TvShow;
import id.dicoding.expertcourse.repository.movie.MovieInAppDataSource;
import id.dicoding.expertcourse.repository.movie.MovieRepository;
import id.dicoding.expertcourse.repository.tvshow.TvShowInAppDataSource;
import id.dicoding.expertcourse.repository.tvshow.TvShowRepository;
import id.dicoding.expertcourse.ui.adapter.BaseMovieListAdapter;
import id.dicoding.expertcourse.util.ItemClickSupport;

public class BaseMovieListFragment extends Fragment implements ItemClickSupport.OnItemClickListener, BaseMovieListContract.View {
    private RecyclerView recyclerView;
    private BaseMovieListAdapter adapter;
    private BaseMovieListContract.Presenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        new BaseMovieListPresenter(
                this,
                new MovieRepository(new MovieInAppDataSource()),
                new TvShowRepository(new TvShowInAppDataSource()));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_movie_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupView(view);
    }

    @Override
    public int getMovieType() {
        Bundle extras = getArguments();
        if(extras == null) {
            return MovieConst.TYPE_MOVIES;
        }

        return extras.getInt(getString(R.string.extra_data_movie_type), MovieConst.TYPE_MOVIES);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    public void setPresenter(BaseMovieListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void setupView(View view) {
        recyclerView = view.findViewById(R.id.rv_base_movie_list);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(this);

        adapter = new BaseMovieListAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        presenter.onItemClicked(position);
    }

    @Override
    public void navigateToDetailView(Movie movie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(getString(R.string.extra_data_movie_type), MovieConst.TYPE_MOVIES);
        intent.putExtra(getString(R.string.extra_data_movie), movie);
        intent.putExtra(getString(R.string.extra_data_movie_title), movie.getTitle());
        startActivity(intent);
    }

    @Override
    public void navigateToDetailView(TvShow tvShow) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(getString(R.string.extra_data_movie_type), MovieConst.TYPE_TV_SHOWS);
        intent.putExtra(getString(R.string.extra_data_movie), tvShow);
        intent.putExtra(getString(R.string.extra_data_movie_title), tvShow.getTitle());
        startActivity(intent);
    }

    @Override
    public void showMovieList(List<BaseMovie> baseMovieList) {
        adapter.clearData();
        adapter.setData(baseMovieList);
    }
}
