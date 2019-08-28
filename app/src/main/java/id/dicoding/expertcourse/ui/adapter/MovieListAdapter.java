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
import id.dicoding.expertcourse.model.BaseMovie;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private final List<BaseMovie> movieList = new ArrayList<>();

    public void setData(List<BaseMovie> baseMovieList) {
        this.movieList.addAll(baseMovieList);
        notifyItemRangeInserted(0, baseMovieList.size());
    }

    public void clearData() {
        int lastItemCount = this.getItemCount();
        this.movieList.clear();
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
        BaseMovie baseMovie = movieList.get(position);
        String overview = baseMovie.getOverview().isEmpty() ?
                holder.itemView.getContext().getResources().getString(R.string.no_overview_text) :
                baseMovie.getOverview();
        holder.bind(baseMovie, overview);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView logoIv;
        private final TextView titleTv;
        private final TextView releaseYearTv;
        private final TextView reviewScoreTv;
        private final TextView overviewTv;

        ViewHolder(View view) {
            super(view);
            logoIv = view.findViewById(R.id.iv_movie_logo_row);
            titleTv = view.findViewById(R.id.tv_movie_title_row);
            releaseYearTv = view.findViewById(R.id.tv_movie_release_year_row);
            reviewScoreTv = view.findViewById(R.id.tv_movie_rate_text_row);
            overviewTv = view.findViewById(R.id.tv_movie_overview_row);
        }

        void bind(BaseMovie movie, String overview) {
            Picasso.get().
                    load(movie.getPosterUrl()).
                    config(Bitmap.Config.RGB_565).
                    fit().
                    into(logoIv);

            titleTv.setText(movie.getOriginalTitle());
            releaseYearTv.setText(movie.getReleaseYear());
            reviewScoreTv.setText(String.valueOf(movie.getVoteAverage()));
            overviewTv.setText(overview);
        }
    }
}
