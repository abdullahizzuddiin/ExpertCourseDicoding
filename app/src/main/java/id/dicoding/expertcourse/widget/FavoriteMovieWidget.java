package id.dicoding.expertcourse.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import id.dicoding.expertcourse.DetailActivity;
import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.constant.MovieConst;

/**
 * Implementation of App Widget functionality.
 */
public class FavoriteMovieWidget extends AppWidgetProvider {
    public static final String WIDGET_UPDATE_ACTION = "WIDGET_UPDATE_ACTION";
    private static final String WIDGET_CLICK_ACTION = "WIDGET_CLICK_ACTION";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //karena di dicoding kagak jelas, penjelasan lebih lengkap cek di
        //https://github.com/vogellacompany/codeexamples-android/blob/master/com.example.android.stackwidget/src/com/example/android/stackwidget/StackWidgetProvider.java
        //membuat intent dengan StackService yang akan digunakan oleh widget ini
        Intent initialIntent = new Intent(context, FavoriteMovieStackWidgetService.class);
        initialIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        //Saat intent dikomparasi/dibandingkan, extra-nya diabaikan.
        //jadi perlu dimasukkin ke data, agar extra-nya gak diabaikan saat dibandingkan
        initialIntent.setData(Uri.parse(initialIntent.toUri(Intent.URI_INTENT_SCHEME)));

        //menginisiasi RemoteViews Object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.favorite_movie_widget);
        views.setRemoteAdapter(R.id.stack_view, initialIntent);
        views.setEmptyView(R.id.stack_view, R.id.empty_view);

        //membuat intent jika widget di-click
        Intent clickIntent = new Intent(context, FavoriteMovieWidget.class);
        clickIntent.setAction(WIDGET_CLICK_ACTION);
        clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        //gak tau kenapa dipanggil dua kali
        initialIntent.setData(Uri.parse(initialIntent.toUri(Intent.URI_INTENT_SCHEME)));

        //membuat intent broadcast yang akan dikirimkan jika widget di-klik
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, appWidgetId, clickIntent, 0);
        views.setPendingIntentTemplate(R.id.stack_view, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction() == null) return;

        switch (intent.getAction()) {
            case WIDGET_CLICK_ACTION:
                navigateToDetailView(context, intent);
                break;
            case WIDGET_UPDATE_ACTION:
                updateWidgetData(context);
                break;
        }
    }

    private void updateWidgetData(Context context) {
        int[] widgetId = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, FavoriteMovieWidget.class));
        for(int id : widgetId){
            AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(id, R.id.stack_view);
        }
    }

    private void navigateToDetailView(Context context, Intent intent) {
        int movieId = intent.getIntExtra(context.getResources().getString(R.string.extra_data_base_movie_id), 0);
        int movieType = intent.getIntExtra(context.getResources().getString(R.string.extra_data_base_movie_type), MovieConst.TYPE_MOVIES);
        String movieTitle = intent.getStringExtra(context.getResources().getString(R.string.extra_data_base_movie_title));

        Intent detailIntent = new Intent(context, DetailActivity.class);
        detailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        detailIntent.putExtra(context.getResources().getString(R.string.extra_data_base_movie_type), movieType);
        detailIntent.putExtra(context.getResources().getString(R.string.extra_data_base_movie_title), movieTitle);
        detailIntent.putExtra(context.getResources().getString(R.string.extra_data_base_movie_id), movieId);
        context.startActivity(detailIntent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

