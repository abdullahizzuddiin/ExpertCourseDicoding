package id.dicoding.expertcourse.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.model.TvShow;

public class TvShowListAdapter extends RecyclerView.Adapter<TvShowListAdapter.ViewHolder> {
    private List<TvShow> tvShowList = new ArrayList<>();

    public void setData(List<TvShow> tvSHowList) {
        this.tvShowList.addAll(tvSHowList);
        notifyItemRangeChanged(0, tvSHowList.size());
    }

    public void clearData() {
        int lastItemCount = this.getItemCount();
        this.tvShowList.clear();
        notifyItemRangeRemoved(0, lastItemCount);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.row_base_movie_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(tvShowList.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView logoIv;
        private TextView titleTv, releaseYearTv, reviewScoreTv, runtimeTv, overviewTv;

        ViewHolder(View view) {
            super(view);
            logoIv = view.findViewById(R.id.iv_movie_logo_row);
            titleTv = view.findViewById(R.id.tv_movie_title_row);
            releaseYearTv = view.findViewById(R.id.tv_movie_release_year_row);
            reviewScoreTv = view.findViewById(R.id.tv_movie_rate_text_row);
            runtimeTv = view.findViewById(R.id.tv_movie_runtime_row);
            overviewTv = view.findViewById(R.id.tv_movie_overview_row);
        }

        void bind(TvShow tvShow) {
            Picasso.get().
                    load("file:///android_asset/" + tvShow.getBanner()).
                    config(Bitmap.Config.RGB_565).
                    fit().
                    into(logoIv);

            titleTv.setText(tvShow.getTitle());
            releaseYearTv.setText(tvShow.getReleasedYear());
            reviewScoreTv.setText(String.valueOf(tvShow.getReviewScoreTenMaxFormat()));
            runtimeTv.setText(tvShow.getRuntime());
            overviewTv.setText(tvShow.getOverview());
        }
    }
}
