package id.dicoding.expertcourse.presenter_operation;

import android.view.View;
import android.widget.AdapterView;

public interface MainPresenterOperation {
    void setupData();

    void onItemClick(AdapterView<?> parent, View view, int position, long id);
}
