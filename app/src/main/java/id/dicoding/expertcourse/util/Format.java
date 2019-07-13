package id.dicoding.expertcourse.util;

public class Format {
    public static float parseToPerFiveRate(int score) {
        int maximumRegularRate = 100;
        float maximumFiveRate = 5.0f;

        return score * maximumFiveRate / maximumRegularRate;
    }

    public static float parseToPerTenRate(int score) {
        float tenRateFormat = 10.0f;

        return score / tenRateFormat;
    }
}
