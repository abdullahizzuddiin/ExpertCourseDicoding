package id.dicoding.expertcourse.model;

import java.util.List;

import static id.dicoding.expertcourse.util.Format.parseToPerFiveRate;

public class Movie {
    private String title, banner, releasedYear, overview, revenue, budget, runtime, originalLanguage;
    private int reviewScore;
    private List<Cast> topBilledCasts;

    public Movie() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getOverview() {
        return overview;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public float getReviewScoreFiveMaxFormat() {
        return parseToPerFiveRate(reviewScore);
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public List<Cast> getTopBilledCasts() {
        return topBilledCasts;
    }

    public void setTopBilledCasts(List<Cast> topBilledCasts) {
        this.topBilledCasts = topBilledCasts;
    }
}
