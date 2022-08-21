package parser;

import java.util.Map;

public class Utils {
    public static String getPercentage(int count, int totalCount) {
        return ((double) Math.round(((double) count / (double) ((totalCount)) * 100) * 100) / 100) + "%";
    }
}