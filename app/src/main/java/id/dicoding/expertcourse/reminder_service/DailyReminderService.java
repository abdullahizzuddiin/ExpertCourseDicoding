package id.dicoding.expertcourse.reminder_service;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.NotificationItem;
import id.dicoding.expertcourse.repository.movie.MovieDataSource;
import id.dicoding.expertcourse.repository.movie.MovieOnlineDBDataSource;
import id.dicoding.expertcourse.repository.movie.MovieRepository;
import id.dicoding.expertcourse.util.ReminderNotificationManager;

/**
 * https://www.youtube.com/watch?v=XFN3MrnNhZA
 */
public class DailyReminderService extends JobService implements MovieDataSource.GetMoviesDataCallback {
    public static final String DAILY_REMINDER_DISPATCHER_TAG = "reminder_service_dispatcher";
    public static final String RELEASE_NOTIFICATION_DISPATCHER_TAG = "release_film_service_dispatcher";
    public static String INITIAL = "initial";
    private JobParameters job;
    private MovieRepository movieRepository;
    private String today;

    /**
     * main thread!
     *
     * @param job job
     * @return boolean: true jika manggil background thread. Nanti perlu panggil JobFinished
     */
    @Override
    public boolean onStartJob(@NonNull JobParameters job) {
        this.job = job;
        return processJob(job);
    }

    private boolean processJob(@NonNull JobParameters job) {
        switch (job.getTag()) {
            case DAILY_REMINDER_DISPATCHER_TAG:
                processDailyReminderJob();
                return false;
            case RELEASE_NOTIFICATION_DISPATCHER_TAG:
                processReleasedNotificationJob();
                return true;
        }
        return false;
    }

    private void processReleasedNotificationJob() {
        movieRepository = new MovieRepository(new MovieOnlineDBDataSource());
        setTodayDate();
        getReleasedTodayMovies();
    }

    private void processDailyReminderJob() {
        showReminderNotification();
        setDailyReminderRecurOn();
    }

    private void setDailyReminderRecurOn() {
        Bundle extras = job.getExtras();

        if(extras == null) {
            return;
        }

        boolean isInitialJob = extras.getBoolean(INITIAL, false);

        //if true, it means this job will be fired only when 7am since the time when initialized
        //of course not 24hours.
        //so we will start reminder to next 24 hours
        if(!isInitialJob) {
            return;
        }

        DailyReminderDispatcher dispatcher = new DailyReminderDispatcher(getBaseContext());
        dispatcher.startDailyReminderRecurringDispatcher();
    }

    public void showReminderNotification() {
        NotificationItem notificationItem = createReminderNotification();
        ReminderNotificationManager manager = new ReminderNotificationManager(this, notificationItem);
        manager.showOpenAppNotification();
    }

    // channel name itu hanya digunakan di Oreo++ saja.
    // Jadi di Notification Setting device-nya, ada switch on-off notification per channel.
    // Channel Name == Categories (di tampilannya)
    private NotificationItem createReminderNotification() {
        NotificationItem notificationItem = new NotificationItem();
        notificationItem.setNotifId(NotificationItem.REMINDER_NOTIFICATION_ID);
        notificationItem.setChannelId(NotificationItem.REMINDER_NOTIFICATION_CHANNEL_ID);
        notificationItem.setChannelName(getApplicationContext().getString(R.string.daily_reminder_notification_channel_name));
        notificationItem.setTitle(getApplicationContext().getString(R.string.daily_reminder_notification_title));
        notificationItem.setMessage(getApplicationContext().getString(R.string.daily_reminder_notification_message));
        notificationItem.setIcon(R.drawable.ic_launcher_foreground);
        return notificationItem;
    }

    private void setTodayDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        today = dateFormat.format(date);
    }

    private void getReleasedTodayMovies() {
        movieRepository.getReleaseTodayMovies(Locale.getDefault().getLanguage(), today, today, this);
    }

    @Override
    public void onDataLoaded(List<BaseMovie> result) {
        processReleasedTodayMovieData(result);
        setDailyReleaseNotificationRecurOn();
        jobFinished(job, false);
    }

    @Override
    public void onFailure() {
        Log.d("ReleaseNotif", "Ada error");
        jobFinished(job, true);
    }

    private void processReleasedTodayMovieData(List<BaseMovie> movies) {
        List<BaseMovie> filteredMovies = getTop3ReleasedTodayMovies(movies);

        if(filteredMovies.size() == 0) {
            showNoReleasedTodayMovieNotification();
            return;
        }

        showReleasedTodayManyNotification(filteredMovies);
    }

    private void showReleasedTodayManyNotification(List<BaseMovie> movies) {
        for (int ii = 0; ii < movies.size(); ii++) {
            showReleasedTodayNotification(movies.get(ii), ii);
        }
    }

    private void showNoReleasedTodayMovieNotification() {
        NotificationItem notificationItem = createNoReleasedTodayMovieNotification();
        ReminderNotificationManager manager = new ReminderNotificationManager(this, notificationItem);
        manager.showOpenAppNotification();
    }

    private List<BaseMovie> getTop3ReleasedTodayMovies(List<BaseMovie> movies) {
        List<BaseMovie> result = new ArrayList<>();

        for (BaseMovie movie : movies) {
            if(result.size() == 3) {
                break;
            }

            if(!movie.getReleaseDate().equals(today)) {
                continue;
            }

            result.add(movie);
        }

        return result;
    }

    public void showReleasedTodayNotification(BaseMovie movie, int count) {
        NotificationItem notificationItem = createReminderNotification(movie, count);
        ReminderNotificationManager manager = new ReminderNotificationManager(this, notificationItem);
        manager.showOpenDetailMovieNotification(movie);
    }

    // channel name itu hanya digunakan di Oreo++ saja.
    // Jadi di Notification Setting device-nya, ada switch on-off notification per channel.
    // Channel Name == Categories (di tampilannya)
    private NotificationItem createReminderNotification(BaseMovie movie, int count) {
        NotificationItem notificationItem = new NotificationItem();
        notificationItem.setNotifId(NotificationItem.RELEASE_NOTIFICATION_ID + count);
        notificationItem.setChannelId(NotificationItem.RELEASE_NOTIFICATION_CHANNEL_ID);
        notificationItem.setChannelName(getApplicationContext().getString(R.string.release_notification_channel_name));
        notificationItem.setTitle(getApplicationContext().getString(R.string.release_notification_title, movie.getOriginalTitle()));
        notificationItem.setMessage(getApplicationContext().getString(R.string.release_notification_message));
        notificationItem.setIcon(R.drawable.ic_launcher_foreground);
        return notificationItem;
    }

    private NotificationItem createNoReleasedTodayMovieNotification() {
        NotificationItem notificationItem = new NotificationItem();
        notificationItem.setNotifId(NotificationItem.RELEASE_NOTIFICATION_ID);
        notificationItem.setChannelId(NotificationItem.RELEASE_NOTIFICATION_CHANNEL_ID);
        notificationItem.setChannelName(getApplicationContext().getString(R.string.release_notification_channel_name));
        notificationItem.setTitle(getApplicationContext().getString(R.string.empty_release_notification_title));
        notificationItem.setIcon(R.drawable.ic_launcher_foreground);
        return notificationItem;
    }

    private void setDailyReleaseNotificationRecurOn() {
        Bundle extras = job.getExtras();

        if(extras == null) {
            return;
        }

        boolean isInitialJob = extras.getBoolean(INITIAL, false);

        //if true, it means this job will be fired only when 7am since the time when initialized
        //of course not 24hours.
        //so we will start reminder to next 24 hours
        if(!isInitialJob) {
            return;
        }

        ReleaseNotificationDispatcher dispatcher = new ReleaseNotificationDispatcher(getBaseContext());
        dispatcher.startReleaseNotificationRecurringDispatcher();
    }

    /**
     * called saat job ter-cancel karena kondisi tidak lagi dipenuhi
     * @param job job
     * @return
     */
    @Override
    public boolean onStopJob(@NonNull JobParameters job) {
        return false;
    }
}
