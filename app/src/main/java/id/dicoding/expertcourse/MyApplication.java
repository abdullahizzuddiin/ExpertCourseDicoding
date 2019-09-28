package id.dicoding.expertcourse;

import android.app.Application;
import android.content.Context;

import id.dicoding.expertcourse.db.main.AppDatabase;

public class MyApplication extends Application {
    //onCreate di MyApplication dijalankan setelah onCreate di ContentProvider
    //menyebabkan AppDatabase belum ter-init karena butuh contextnya
    //https://stackoverflow.com/questions/9873669/how-do-i-catch-content-provider-initialize
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        AppDatabase.init(this);
    }
}
