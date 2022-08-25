package parser;

import lombok.Data;
import model.ParsedLog;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static input.Reader.logCount;

@Data
public class Parser {
    public static final Map<String, Integer> API_KEY = new HashMap<>();
    public static final Map<String, Integer> API_SERVICE = new HashMap<>();
    public static final Map<String, Integer> PEAK_TIME = new HashMap<>();
    public static final Map<String, Integer> WEB_BROWSER = new HashMap<>();

    public static int serverCodeCount10 = 0;
    public static int serverCodeCount200 = 0;
    public static int serverCodeCount404 = 0;

    public void parse(String line) {

        serverCodeCount10 = serverCodeCount10 + StringUtils.countMatches(line, "[10]");
        serverCodeCount200 = serverCodeCount200 + StringUtils.countMatches(line, "[200]");
        serverCodeCount404 = serverCodeCount404 + StringUtils.countMatches(line, "[404]");

        String[] arrayData = StringUtils.substringsBetween(line, "[", "]");
        API_KEY.put(StringUtils.substringBetween(arrayData[1], "apikey=", "&"), API_KEY.getOrDefault(StringUtils.substringBetween(arrayData[1], "apikey=", "&"), 0) + 1);
        API_SERVICE.put(StringUtils.substringBetween(arrayData[1], "search/", "?"), API_SERVICE.getOrDefault(StringUtils.substringBetween(arrayData[1], "search/", "?"), 0) + 1);
        PEAK_TIME.put(arrayData[3].substring(0, arrayData[3].length() - 3), PEAK_TIME.getOrDefault(arrayData[3].substring(0, arrayData[3].length() - 3), 0) + 1);
        WEB_BROWSER.put(arrayData[2], WEB_BROWSER.getOrDefault(arrayData[2], 0) + 1);

        input.Reader.logCount++;
        System.out.println(line);

        Map<String, Integer> orderedApiKeyMap = makeOrderedMap(Parser.API_KEY);
        Map<String, Integer> orderedApiServiceMap = makeOrderedMap(Parser.API_SERVICE);
        Map<String, Integer> orderedPeakTimeMap = makeOrderedMap(Parser.PEAK_TIME);
        Map<String, Integer> orderedWebBrowserMap = makeOrderedMap(Parser.WEB_BROWSER);

        getApiKey(orderedApiKeyMap);


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

        ArrayList<String> entryWebBrowserKeyList = new ArrayList<String>();
        ListIterator<String> entryWebBrowserKeyListIterator = entryWebBrowserKeyList.listIterator();

        ArrayList<Integer> entryWebBrowserValueList = new ArrayList<Integer>();
        ListIterator<Integer> entryWebBrowserValueListIterator = entryWebBrowserValueList.listIterator();


        while(entryIteratorWebBrowser.hasNext()) {
            entryWebBrowserKeyList.add(entryIteratorWebBrowser.next().getKey());
            System.out.println(entryWebBrowserKeyList);
        }
        System.out.println();

        Iterator<Map.Entry<String, Integer>> entryIteratorWebBrowser1 = orderedWebBrowserMap.entrySet().iterator();

        while(entryIteratorWebBrowser1.hasNext()) {
            entryWebBrowserValueList.add(entryIteratorWebBrowser1.next().getValue());
            System.out.println(entryWebBrowserValueList);
        }

        System.out.println("웹 브라우저 별 사용비율\n\n");

        while(entryWebBrowserKeyListIterator.hasNext()) {
            System.out.println(entryWebBrowserKeyListIterator.next() + " : " + Utils.getPercentage(entryWebBrowserValueListIterator.next(), logCount));
        }
        // todo 파싱
    }

    public static String getApiKey(Map<String, Integer> orderedApiKeyMap) {
        final Iterator<Map.Entry<String, Integer>> entryIteratorApiKey = orderedApiKeyMap.entrySet().iterator();
        return entryIteratorApiKey.next().getKey();
    }

    public static void getServerCode() {

    }

    public static void getApiServiceId() {

    }

    public static void getPeakTime() {

    }

    public static void getWebBrowser() {

    }

    public static Map<String, Integer> makeOrderedMap(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> newValue, LinkedHashMap::new));
    }
}
