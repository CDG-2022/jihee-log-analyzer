package com.cdg;

import input.Reader;
import org.apache.commons.lang3.StringUtils;
import output.Writer;
import parser.Parser;
import parser.Utils;
import input.Reader;

import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle error
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;
import java.util.stream.Collectors;

import static input.Reader.logCount;

/**
 * Hello world!
 */
public class App {

    private static final String OUTPUT_FILE = "output.log";

    private static final Map<String, Integer> API_KEY = new HashMap<>();
    private static final Map<String, Integer> API_SERVICE = new HashMap<>();
    private static final Map<String, Integer> PEAK_TIME = new HashMap<>();
    private static final Map<String, Integer> WEB_BROWSER = new HashMap<>();

    private static int serverCodeCount10 = 0;
    private static int serverCodeCount200 = 0;
    private static int serverCodeCount404 = 0;

    public static void main(String[] args) {
        Reader.fileReader();

        Map<String, Integer> orderedApiKeyMap = makeOrderedMap(API_KEY);
        Map<String, Integer> orderedApiServiceMap = makeOrderedMap(API_SERVICE);
        Map<String, Integer> orderedPeakTimeMap = makeOrderedMap(PEAK_TIME);
        Map<String, Integer> orderedWebBrowserMap = makeOrderedMap(WEB_BROWSER);

        final Iterator<Map.Entry<String, Integer>> entryIteratorApiKey = orderedApiKeyMap.entrySet().iterator();
        final String apiKeyForWrite = entryIteratorApiKey.next().getKey();

        final Iterator<Map.Entry<String, Integer>> entryIteratorAPIService = orderedApiServiceMap.entrySet().iterator();
        Map.Entry<String, Integer> entryAPIService1 = entryIteratorAPIService.next();
        Map.Entry<String, Integer> entryAPIService2 = entryIteratorAPIService.next();
        Map.Entry<String, Integer> entryAPIService3 = entryIteratorAPIService.next();
        final String APIServiceForWrite = entryAPIService1.getKey() + " : " + entryAPIService1.getValue() +
                "\n" + entryAPIService2.getKey() + " : " + entryAPIService2.getValue() +
                "\n" + entryAPIService3.getKey() + " : " + entryAPIService3.getValue();

        final Iterator<Map.Entry<String, Integer>> entryIteratorPeakTime = orderedPeakTimeMap.entrySet().iterator();
        final String peakTimeForWrite = entryIteratorPeakTime.next().getKey();

        final Iterator<Map.Entry<String, Integer>> entryIteratorWebBrowser = orderedWebBrowserMap.entrySet().iterator();
        Map.Entry<String, Integer> entryWebBrowser0 = entryIteratorWebBrowser.next();
        Map.Entry<String, Integer> entryWebBrowser1 = entryIteratorWebBrowser.next();
        Map.Entry<String, Integer> entryWebBrowser2 = entryIteratorWebBrowser.next();
        Map.Entry<String, Integer> entryWebBrowser3 = entryIteratorWebBrowser.next();
        Map.Entry<String, Integer> entryWebBrowser4 = entryIteratorWebBrowser.next();

        Writer.fileWriter();
    }
    public static Map<String, Integer> makeOrderedMap(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> newValue, LinkedHashMap::new));
    }
}
