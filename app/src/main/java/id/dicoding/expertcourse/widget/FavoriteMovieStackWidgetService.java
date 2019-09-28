package id.dicoding.expertcourse.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class FavoriteMovieStackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext());
    }
}
