package id.dicoding.expertcourse;

import android.app.Application;

import id.dicoding.expertcourse.db.AppDatabase;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase.init(this);
    }
}
