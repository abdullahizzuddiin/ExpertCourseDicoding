package id.dicoding.expertcourse.presenter;

import android.app.Activity;
import android.content.Context;

import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.model.Movie;
import id.dicoding.expertcourse.presenter_operation.DetailPresenterOperation;
import id.dicoding.expertcourse.view_operation.DetailViewOperation;

public class DetailPresenter implements DetailPresenterOperation {
    private Context context;
    private DetailViewOperation detailView;
    private Movie movie;

    public DetailPresenter(Context context, DetailViewOperation detailView) {
        this.context = context;
        this.detailView = detailView;
    }

    private void getExtras() {
        movie = ((Activity) context).getIntent().getParcelableExtra(context.getString(R.string.extra_data_movie));
    }

    @Override
    public void start() {
        getExtras();
        detailView.setToolbarTitle(movie.getTitle());
        detailView.showMovie(movie);
    }
}
