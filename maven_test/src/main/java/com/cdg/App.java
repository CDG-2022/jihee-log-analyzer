package com.cdg;

import org.apache.commons.lang3.StringUtils;
import parser.Parser;

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

    public static void main(String[] args) {
        String data = "";
        int logCount = 0;
        int serverCodeCount10 = 0;
        int serverCodeCount200 = 0;
        int serverCodeCount404 = 0;

        try {
            File myObj = new File(INPUT_FILE);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();

                Parser parser = new Parser();
                parser.parse(data);


                serverCodeCount10 = serverCodeCount10 + StringUtils.countMatches(data, "[10]");
                serverCodeCount200 = serverCodeCount200 + StringUtils.countMatches(data, "[200]");
                serverCodeCount404 = serverCodeCount404 + StringUtils.countMatches(data, "[404]");

                String[] arrayData = StringUtils.substringsBetween(data, "[", "]");
                API_KEY.put(StringUtils.substringBetween(arrayData[1], "apikey=", "&"), API_KEY.getOrDefault(StringUtils.substringBetween(arrayData[1], "apikey=", "&"), 0) + 1);
                API_SERVICE.put(StringUtils.substringBetween(arrayData[1], "search/", "?"), API_SERVICE.getOrDefault(StringUtils.substringBetween(arrayData[1], "search/", "?"), 0) + 1);
                PEAK_TIME.put(arrayData[3].substring(0, arrayData[3].length()-3), PEAK_TIME.getOrDefault(arrayData[3].substring(0, arrayData[3].length()-3), 0) + 1);
                WEB_BROWSER.put(arrayData[2], WEB_BROWSER.getOrDefault(arrayData[2], 0) + 1);

                logCount++;
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("찾는 파일이 없어요!");
            e.printStackTrace();
        }

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
        final String webBrowserForWrite = entryIteratorWebBrowser.next().getKey();
        Map.Entry<String, Integer> entryWebBrowser1 = entryIteratorWebBrowser.next();

        System.out.println(webBrowserForWrite + " " + getPercentage(entryWebBrowser1, logCount) + " " + webBrowserForWrite);

        System.out.println(webBrowserForWrite + entryIteratorWebBrowser.next().getValue());


        System.out.println("웹 브라우저 별 사용비율\n\n" + orderedWebBrowserMap + "\n");

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
            myWriter.write("웹 브라우저 별 사용비율\n\n" + orderedWebBrowserMap + "\n" +
                    "IE : " + "\n" +
                    "Firefox : " + "\n" +
                    "Safari : " + "\n" +
                    "Chrome : " + "\n" +
                    "Opera : ");

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
    public static double getPercentage (Map.Entry<String, Integer> entryWebBrowser1, int logCount) {
        return (double)Math.round(((double)(entryWebBrowser1.getValue())/(double)((logCount))*100)*100)/100;
    }
}
