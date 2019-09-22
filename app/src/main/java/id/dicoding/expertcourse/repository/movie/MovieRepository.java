package id.dicoding.expertcourse.repository.movie;

public class MovieRepository implements MovieDataSource {
    private final MovieDataSource movieDataSource;

    public MovieRepository(MovieDataSource dataSource) {
        this.movieDataSource = dataSource;
    }

    @Override
    public void getMovies(String lang, final GetMoviesDataCallback callback) {
        movieDataSource.getMovies(lang, callback);
    }

    @Override
    public void getDetailMovie(int movieId, String lang, final GetDetailDataCallback callback) {
        movieDataSource.getDetailMovie(movieId, lang, callback);
    }
}
