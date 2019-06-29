package id.dicoding.expertcourse.util;

public class Format {
    public static float parseToPerFiveRate(int score) {
        int maximumRegularRate = 100;
        float maximumFiveRate = 5.0f;

        return score * maximumFiveRate / maximumRegularRate;
    }
}
