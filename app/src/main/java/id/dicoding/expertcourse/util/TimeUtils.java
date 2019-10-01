package id.dicoding.expertcourse.util;

import java.util.Calendar;

public class TimeUtils {
    public static final int ONE_MINUTES_IN_SEC = 60;
    public static final int SEVEN_AM = 7;
    public static final int EIGHT_AM = 8;
    private static final int ONE_HOURS_IN_SEC = ONE_MINUTES_IN_SEC * 60;
    public static final int TWENTY_FOUR_HOURS_IN_SEC = ONE_HOURS_IN_SEC * 24;

    public static int calculateTimeInSecToNextSpecificTime(int hourOfDay) {
        Calendar currentTime = Calendar.getInstance();

        Calendar nextTime = Calendar.getInstance();
        if(nextTime.get(Calendar.HOUR_OF_DAY) >= hourOfDay) {
            nextTime.add(Calendar.DATE, 1);
        }
        nextTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        nextTime.set(Calendar.MINUTE, 0);
        nextTime.set(Calendar.SECOND, 0);

        return (int) ((nextTime.getTimeInMillis() - currentTime.getTimeInMillis()) / 1000);
    }
}
