package id.dicoding.expertcourse.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.dicoding.expertcourse.R;
import id.dicoding.expertcourse.model.Movie;

/**
 * please check https://www.youtube.com/watch?v=wDBM6wVEO70 for best practices how to implement list view
 */
public class MovieListStandardAdapter extends BaseAdapter {
    private Context context;
    private List<Movie> movieList;

    public MovieListStandardAdapter(Context context) {
        this.context = context;
        this.movieList = new ArrayList<>();
    }

    public void setData(List<Movie> movieList) {
        this.movieList = new ArrayList<>(movieList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_standard_movie_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Movie movie = (Movie) getItem(position);
        viewHolder.bind(movie);
        return convertView;
    }

    private class ViewHolder {
        private ImageView logoIv;
        private TextView titleTv, releaseYearTv;
        private RatingBar reviewScoreRb;
        private TextView reviewScoreTv;

        ViewHolder(View view) {
            logoIv = view.findViewById(R.id.iv_movie_logo_row_standard);
            titleTv = view.findViewById(R.id.tv_movie_title_row_standard);
            releaseYearTv = view.findViewById(R.id.tv_movie_release_year_row_standard);
            reviewScoreRb = view.findViewById(R.id.rb_movie_review_score_row_standard);
            reviewScoreTv = view.findViewById(R.id.tv_movie_rate_text_row_standard);
        }

        void bind(Movie movie) {
            Picasso.get().
                    load("file:///android_asset/" + movie.getBanner()).
                    config(Bitmap.Config.RGB_565).
                    fit().
                    into(logoIv);

            titleTv.setText(movie.getTitle());
            releaseYearTv.setText(movie.getReleasedYear());
            reviewScoreRb.setRating(movie.getReviewScoreFiveMaxFormat());
            reviewScoreTv.setText(context.getString(R.string.rate_text, String.valueOf(movie.getReviewScoreFiveMaxFormat())));

        }
    }
}
