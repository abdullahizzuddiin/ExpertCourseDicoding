package id.dicoding.expertcourse.request;

public class ApiUtil {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";

    public static final String BASE_BIG_IMAGE_URL = BASE_IMAGE_URL + "w500";
    public static final String BASE_MEDIUM_IMAGE_URL = BASE_IMAGE_URL + "w185";

    public static MovieDBApi getMovieDBService() {
        return RetrofitClient.getClient(BASE_URL).create(MovieDBApi.class);
    }
}
