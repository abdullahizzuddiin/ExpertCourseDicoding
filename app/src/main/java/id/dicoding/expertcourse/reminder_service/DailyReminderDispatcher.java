package id.dicoding.expertcourse.reminder_service;

import android.content.Context;
import android.os.Bundle;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.JobTrigger;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import static id.dicoding.expertcourse.util.TimeUtils.ONE_MINUTES_IN_SEC;
import static id.dicoding.expertcourse.util.TimeUtils.SEVEN_AM;
import static id.dicoding.expertcourse.util.TimeUtils.TWENTY_FOUR_HOURS_IN_SEC;
import static id.dicoding.expertcourse.util.TimeUtils.calculateTimeInSecToNextSpecificTime;

public class DailyReminderDispatcher {
    private FirebaseJobDispatcher dispatcher;

    public DailyReminderDispatcher(Context context) {
        dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
    }

    public void startDailyReminderInitialDispatcher() {
        Job myJob = generateInitialDailyReminderJob();

        dispatcher.mustSchedule(myJob);
    }

    public void startDailyReminderRecurringDispatcher() {
        Job myJob = generateRecurringDailyReminderJob();

        dispatcher.mustSchedule(myJob);
    }

    private Job generateInitialDailyReminderJob() {
        JobTrigger.ExecutionWindowTrigger trigger = getReminderTrigger();
        Bundle dispatcherBundle = new Bundle();
        dispatcherBundle.putBoolean(DailyReminderService.INITIAL, true);

        return dispatcher.newJobBuilder()
                .setService(DailyReminderService.class)
                .setTag(DailyReminderService.DAILY_REMINDER_DISPATCHER_TAG)
                .setRecurring(false)
                .setLifetime(Lifetime.FOREVER)
                .setTrigger(trigger)
                .setReplaceCurrent(true)
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .setExtras(dispatcherBundle)
                .build();
    }

    private Job generateRecurringDailyReminderJob() {
        JobTrigger.ExecutionWindowTrigger trigger = getReminderRecurringTrigger();

        return dispatcher.newJobBuilder()
                .setService(DailyReminderService.class)
                .setTag(DailyReminderService.DAILY_REMINDER_DISPATCHER_TAG)
                .setRecurring(true)
                .setLifetime(Lifetime.FOREVER)
                .setTrigger(trigger)
                .setReplaceCurrent(true)
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .build();
    }

    private JobTrigger.ExecutionWindowTrigger getReminderTrigger() {
        int timeToSevenAmInSec = calculateTimeInSecToNextSpecificTime(SEVEN_AM);
        return Trigger.executionWindow(timeToSevenAmInSec, timeToSevenAmInSec + ONE_MINUTES_IN_SEC);
    }

    private JobTrigger.ExecutionWindowTrigger getReminderRecurringTrigger() {
        return Trigger.executionWindow(TWENTY_FOUR_HOURS_IN_SEC, TWENTY_FOUR_HOURS_IN_SEC + ONE_MINUTES_IN_SEC);
    }

    public void cancelDailyReminderDispatcher() {
        dispatcher.cancel(DailyReminderService.DAILY_REMINDER_DISPATCHER_TAG);
    }
}
