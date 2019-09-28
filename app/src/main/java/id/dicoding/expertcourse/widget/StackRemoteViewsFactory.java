package id.dicoding.expertcourse.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.concurrent.ExecutionException;

import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.db.model.FavoriteBaseMovie;

import static id.dicoding.expertcourse.content_provider.FavoriteMovieProvider.CONTENT_URI;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private final Context context;
    private Cursor cursor;

    StackRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        if (cursor != null){
            cursor.close();
        }

        final long identityToken = Binder.clearCallingIdentity();

        cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);

        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {
        cursor = null;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        FavoriteBaseMovie favoriteBaseMovie = getFavoriteBaseMovie(position);
        final RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.favorite_movie_widget_item);

        try {
            Bitmap preview = Glide.with(context)
                    .asBitmap()
                    .load(favoriteBaseMovie.getPosterUrl())
                    .apply(new RequestOptions().centerCrop())
                    .submit()
                    .get();
            rv.setImageViewBitmap(R.id.imageView, preview);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Intent fillInIntent = getClickFillInIntent();
        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    private Intent getClickFillInIntent() {
        Intent fillInIntent = new Intent();
        Bundle extras = new Bundle();
        extras.putInt(context.getResources().getString(R.string.extra_data_base_movie_id), cursor.getInt(FavoriteBaseMovie.INDEX_MOVIE_ID));
        extras.putInt(context.getResources().getString(R.string.extra_data_base_movie_type), cursor.getInt(FavoriteBaseMovie.INDEX_MOVIE_TYPE));
        extras.putString(context.getResources().getString(R.string.extra_data_base_movie_title), cursor.getString(FavoriteBaseMovie.INDEX_MOVIE_TITLE));
        fillInIntent.putExtras(extras);
        return fillInIntent;
    }


    private FavoriteBaseMovie getFavoriteBaseMovie(int position) {
        if(!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Data not found");
        }

        return new FavoriteBaseMovie(cursor);
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return cursor.moveToPosition(position) ? cursor.getLong(0) : position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
