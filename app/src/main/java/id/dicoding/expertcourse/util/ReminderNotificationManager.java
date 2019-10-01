package id.dicoding.expertcourse.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import id.dicoding.expertcourse.DetailActivity;
import id.dicoding.expertcourse.MainActivity;
import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.constant.MovieConst;
import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.NotificationItem;

public class ReminderNotificationManager {
    private Context context;
    private NotificationItem notificationItem;

    public ReminderNotificationManager(Context context, NotificationItem notificationItem) {
        this.context = context;
        this.notificationItem = notificationItem;
    }

    public void showOpenAppNotification() {
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(notificationManagerCompat == null) {
            Toast.makeText(context, R.string.notification_unavailable, Toast.LENGTH_SHORT).show();
            return;
        }

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = getBuilder(context, alarmSound);
        addHomeActivityIntent(builder);
        registerNotificationChannel(notificationManagerCompat, builder);

        Notification notification = builder.build();

        notificationManagerCompat.notify(notificationItem.getNotifId(), notification);
    }

    public void showOpenDetailMovieNotification(BaseMovie movie) {
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(notificationManagerCompat == null) {
            Toast.makeText(context, R.string.notification_unavailable, Toast.LENGTH_SHORT).show();
            return;
        }

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = getBuilder(context, alarmSound);
        addDetailActivityIntent(builder, movie);
        registerNotificationChannel(notificationManagerCompat, builder);

        Notification notification = builder.build();

        notificationManagerCompat.notify(notificationItem.getNotifId(), notification);
    }

    private void addHomeActivityIntent(NotificationCompat.Builder builder) {
        Intent notificationIntent = new Intent(context, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);
        builder.setContentIntent(intent);
    }

    private void addDetailActivityIntent(NotificationCompat.Builder builder, BaseMovie movie) {
        Intent notificationIntent = new Intent(context, DetailActivity.class);
        notificationIntent.putExtra(context.getResources().getString(R.string.extra_data_base_movie_type), MovieConst.TYPE_MOVIES);
        notificationIntent.putExtra(context.getResources().getString(R.string.extra_data_base_movie_title), movie.getOriginalTitle());
        notificationIntent.putExtra(context.getResources().getString(R.string.extra_data_base_movie_id), movie.getId());

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);
        builder.setContentIntent(intent);
    }

    private NotificationCompat.Builder getBuilder(Context context, Uri alarmSound) {
        return new NotificationCompat.Builder(context, notificationItem.getChannelId())
                    .setContentTitle(notificationItem.getTitle())
                    .setSmallIcon(notificationItem.getIcon())
                    .setContentText(notificationItem.getMessage())
                    .setColor(ContextCompat.getColor(context, android.R.color.black))
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setSound(alarmSound)
                    .setAutoCancel(true);
    }

    private void registerNotificationChannel(NotificationManager notificationManagerCompat, NotificationCompat.Builder builder) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }

        NotificationChannel channel = new NotificationChannel(notificationItem.getChannelId(),
                notificationItem.getChannelName(),
                NotificationManager.IMPORTANCE_DEFAULT);

        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

        builder.setChannelId(notificationItem.getChannelId());

        if (notificationManagerCompat != null) {
            notificationManagerCompat.createNotificationChannel(channel);
        }
    }
}
