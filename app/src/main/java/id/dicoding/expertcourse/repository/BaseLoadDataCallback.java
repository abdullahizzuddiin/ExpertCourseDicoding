package id.dicoding.expertcourse.repository;

public interface BaseLoadDataCallback<T> {
    void onDataLoaded(T result);
    void onFailure();
}
