package parser;

import java.util.Map;

public class Utils {
    public static String getPercentage (Map.Entry<String, Integer> entryWebBrowser, int logCount) {
        return ((double)Math.round(((double)(entryWebBrowser.getValue())/(double)((logCount))*100)*100)/100)+"%";
    }
}