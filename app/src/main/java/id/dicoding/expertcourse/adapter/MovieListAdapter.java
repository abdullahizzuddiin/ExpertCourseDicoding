package id.dicoding.expertcourse.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.model.Movie;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<Movie> movieList;

    public MovieListAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.row_standard_movie_list, parent, false);

        return new ViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView logoIv;
        private TextView titleTv, releaseYearTv;
        private TextView reviewScoreTv;
        private TextView runtimeTv;
        private Context context;

        ViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            logoIv = view.findViewById(R.id.iv_movie_logo_row_standard);
            titleTv = view.findViewById(R.id.tv_movie_title_row_standard);
            releaseYearTv = view.findViewById(R.id.tv_movie_release_year_row_standard);
            reviewScoreTv = view.findViewById(R.id.tv_movie_rate_text_row_standard);
            runtimeTv = view.findViewById(R.id.tv_movie_runtime_row_standard);
        }

        void bind(Movie movie) {
            Picasso.get().
                    load("file:///android_asset/" + movie.getBanner()).
                    config(Bitmap.Config.RGB_565).
                    fit().
                    into(logoIv);

            titleTv.setText(movie.getTitle());
            releaseYearTv.setText(movie.getReleasedYear());
            reviewScoreTv.setText(String.valueOf(movie.getReviewScoreTenMaxFormat()));
            runtimeTv.setText(movie.getRuntime());
        }
    }
}
