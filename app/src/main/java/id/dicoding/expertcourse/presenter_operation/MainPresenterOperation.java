package id.dicoding.expertcourse.presenter_operation;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface MainPresenterOperation {
    void setupData();

    void onItemClicked(int position);
}
