package id.dicoding.expertcourse.reminder_service;

import android.content.Context;
import android.os.Bundle;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.JobTrigger;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import static id.dicoding.expertcourse.util.TimeUtils.EIGHT_AM;
import static id.dicoding.expertcourse.util.TimeUtils.ONE_MINUTES_IN_SEC;
import static id.dicoding.expertcourse.util.TimeUtils.TWENTY_FOUR_HOURS_IN_SEC;
import static id.dicoding.expertcourse.util.TimeUtils.calculateTimeInSecToNextSpecificTime;

public class ReleaseNotificationDispatcher {
    private FirebaseJobDispatcher dispatcher;

    public ReleaseNotificationDispatcher(Context context) {
        dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
    }

    public void startReleaseNotificationInitialDispatcher() {
        Job myJob = generateInitialReminderJob();

        dispatcher.mustSchedule(myJob);
    }

    public void startReleaseNotificationRecurringDispatcher() {
        Job myJob = generateRecurringReminderJob();

        dispatcher.mustSchedule(myJob);
    }

    private Job generateInitialReminderJob() {
        JobTrigger.ExecutionWindowTrigger trigger = getInitialTrigger();
        Bundle dispatcherBundle = new Bundle();
        dispatcherBundle.putBoolean(DailyReminderService.INITIAL, true);

        return dispatcher.newJobBuilder()
                .setService(DailyReminderService.class)
                .setTag(DailyReminderService.RELEASE_NOTIFICATION_DISPATCHER_TAG)
                .setRecurring(false)
                .setLifetime(Lifetime.FOREVER)
                .setTrigger(trigger)
                .setReplaceCurrent(true)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .setExtras(dispatcherBundle)
                .build();
    }

    private Job generateRecurringReminderJob() {
        JobTrigger.ExecutionWindowTrigger trigger = getRecurringTrigger();

        return dispatcher.newJobBuilder()
                .setService(DailyReminderService.class)
                .setTag(DailyReminderService.RELEASE_NOTIFICATION_DISPATCHER_TAG)
                .setRecurring(true)
                .setLifetime(Lifetime.FOREVER)
                .setTrigger(trigger)
                .setReplaceCurrent(true)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .build();
    }

    private JobTrigger.ExecutionWindowTrigger getInitialTrigger() {
        int timeToEightnAmInSec = calculateTimeInSecToNextSpecificTime(EIGHT_AM);
        return Trigger.executionWindow(timeToEightnAmInSec, timeToEightnAmInSec + ONE_MINUTES_IN_SEC);
    }

    private JobTrigger.ExecutionWindowTrigger getRecurringTrigger() {
        return Trigger.executionWindow(TWENTY_FOUR_HOURS_IN_SEC, TWENTY_FOUR_HOURS_IN_SEC + ONE_MINUTES_IN_SEC);
    }

    public void cancelReleaseNotificationDispatcher() {
        dispatcher.cancel(DailyReminderService.RELEASE_NOTIFICATION_DISPATCHER_TAG);
    }
}
