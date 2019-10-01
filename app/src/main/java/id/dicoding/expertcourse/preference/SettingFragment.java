package id.dicoding.expertcourse.preference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.reminder_service.DailyReminderDispatcher;
import id.dicoding.expertcourse.reminder_service.ReleaseNotificationDispatcher;

public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {
    private String LANGUAGE_KEY;
    private String DAILY_REMINDER_KEY;
    private String RELEASE_NOTIFICATION_KEY;

    private Preference languagePref;
    private SwitchPreference dailyReminderPref;
    private SwitchPreference releaseNotificationPref;

    private DailyReminderDispatcher dailyReminderDispatcher;
    private ReleaseNotificationDispatcher releaseNotificationDispatcher;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
        setupKeyVariable();
        setupView();
        setInitialPref();
        setListener();
        setupDisptacher();
    }

    private void setupKeyVariable() {
         LANGUAGE_KEY = getResources().getString(R.string.language_pref_key);
         DAILY_REMINDER_KEY = getResources().getString(R.string.daily_reminder_pref_key);
         RELEASE_NOTIFICATION_KEY = getResources().getString(R.string.release_reminder_pref_key);
    }

    private void setupView() {
        languagePref = findPreference(getResources().getString(R.string.language_pref_key));
        dailyReminderPref = findPreference(getResources().getString(R.string.daily_reminder_pref_key));
        releaseNotificationPref = findPreference(getResources().getString(R.string.release_reminder_pref_key));
    }

    private void setInitialPref() {
        SharedPreferences sh = getPreferenceManager().getSharedPreferences();
        dailyReminderPref.setChecked(sh.getBoolean(DAILY_REMINDER_KEY, false));
        releaseNotificationPref.setChecked(sh.getBoolean(RELEASE_NOTIFICATION_KEY, false));
    }

    private void setListener() {
        languagePref.setOnPreferenceClickListener(this);
    }

    private void setupDisptacher() {
        dailyReminderDispatcher = new DailyReminderDispatcher(getContext());
        releaseNotificationDispatcher = new ReleaseNotificationDispatcher(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(DAILY_REMINDER_KEY)) {
            boolean dailyReminderPreferenceValue = sharedPreferences.getBoolean(DAILY_REMINDER_KEY, false);
            dailyReminderPref.setChecked(dailyReminderPreferenceValue);
            onDailyReminderValueChanged(dailyReminderPreferenceValue);
        }

        if(key.equals(RELEASE_NOTIFICATION_KEY)) {
            boolean releaseNotificationPreferenceValue = sharedPreferences.getBoolean(RELEASE_NOTIFICATION_KEY, false);
            releaseNotificationPref.setChecked(releaseNotificationPreferenceValue);
            onReleaseNotifcationValueChanged(releaseNotificationPreferenceValue);
        }
    }

    private void onDailyReminderValueChanged(boolean dailyReminderPreferenceValue) {
        if(dailyReminderPreferenceValue) {
            dailyReminderDispatcher.startDailyReminderInitialDispatcher();
        } else {
            dailyReminderDispatcher.cancelDailyReminderDispatcher();
        }
    }

    private void onReleaseNotifcationValueChanged(boolean releaseNotificationPreferenceValue) {
        if(releaseNotificationPreferenceValue) {
            releaseNotificationDispatcher.startReleaseNotificationInitialDispatcher();
        } else {
            releaseNotificationDispatcher.cancelReleaseNotificationDispatcher();
        }
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if(preference.getKey().equals(LANGUAGE_KEY)) {
            navigateToChangeLanguageView();
        }

        return super.onPreferenceTreeClick(preference);
    }

    private void navigateToChangeLanguageView() {
        Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(mIntent);
    }
}
