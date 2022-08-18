package com.cdg;

import input.Reader;
import org.apache.commons.lang3.StringUtils;
import parser.Parser;
import parser.Utils;
import input.Reader;

import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle error
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {

    private static final String INPUT_FILE = "input.log";
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

        try {
            File myObj = new File(OUTPUT_FILE);
            if (myObj.createNewFile()) {
                System.out.println("파일을 생성했어요!: " + myObj.getName());
            } else {
                System.out.println("이미 존재하는 파일입니다.");
            }

            FileWriter myWriter = new FileWriter(OUTPUT_FILE);

            myWriter.write("로그 수: " + logCount + "\n\n");

            myWriter.write("최다호출 APIKEY"+ "\n\n" + apiKeyForWrite + "\n\n");
            myWriter.write("상태코드 별 횟수\n\n" +
                    "10 : " + serverCodeCount10 + "\n" +
                    "200 : " + serverCodeCount200 + "\n" +
                    "404 : " + serverCodeCount404 + "\n\n");
            myWriter.write("상위 3개의 API ServiceID와 각각의 요청 수\n\n" +
                    APIServiceForWrite + "\n\n");
            myWriter.write("피크 시간대\n\n" + peakTimeForWrite + "\n\n");
            myWriter.write("웹 브라우저 별 사용비율\n\n" + orderedWebBrowserMap +
                    entryWebBrowser0.getKey() + " : " + Utils.getPercentage(entryWebBrowser0, logCount) + "\n" +
                    entryWebBrowser1.getKey() + " : " + Utils.getPercentage(entryWebBrowser1, logCount) + "\n" +
                    entryWebBrowser2.getKey() + " : " + Utils.getPercentage(entryWebBrowser2, logCount) + "\n" +
                    entryWebBrowser3.getKey() + " : " + Utils.getPercentage(entryWebBrowser3, logCount) + "\n" +
                    entryWebBrowser4.getKey() + " : " + Utils.getPercentage(entryWebBrowser4, logCount));

            myWriter.close();
            System.out.println("변경사항을 저장했어요!");
        } catch (IOException e) {
            System.out.println("문제가 발생했어요.");
            e.printStackTrace();
        }
    }
    public static Map<String, Integer> makeOrderedMap(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> newValue, LinkedHashMap::new));
         }
    }
}
