package id.dicoding.expertcourse.util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {
    public static String toDollar(int number) {
        Locale locale = new Locale("en", "US");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(number);
    }
}
