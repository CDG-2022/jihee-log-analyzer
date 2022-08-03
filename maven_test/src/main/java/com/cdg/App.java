package com.cdg;

import org.apache.commons.lang3.StringUtils;

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
    private static final Map<String, Integer> apiKey = new HashMap<>();
    private static final Map<String, Integer> apiService = new HashMap<>();
    private static final Map<String, Integer> peakTime = new HashMap<>();
    private static final Map<String, Integer> webBrowser = new HashMap<>();

    public static void main(String[] args) {
        String data = "";
        int logCount = 0;
        int serverCodeCount10 = 0;
        int serverCodeCount200 = 0;
        int serverCodeCount404 = 0;

        try {
            File myObj = new File("input.log");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();

                serverCodeCount10 = serverCodeCount10 + StringUtils.countMatches(data, "[10]");
                serverCodeCount200 = serverCodeCount200 + StringUtils.countMatches(data, "[200]");
                serverCodeCount404 = serverCodeCount404 + StringUtils.countMatches(data, "[404]");

                String[] arrayData = StringUtils.substringsBetween(data, "[", "]");
                apiKey.put(StringUtils.substringBetween(arrayData[1], "apikey=", "&"), apiKey.getOrDefault(StringUtils.substringBetween(arrayData[1], "apikey=", "&"), 0) + 1);
                apiService.put(StringUtils.substringBetween(arrayData[1], "search/", "?"), apiService.getOrDefault(StringUtils.substringBetween(arrayData[1], "search/", "?"), 0) + 1);
                peakTime.put(arrayData[3].substring(0, arrayData[3].length()-3), peakTime.getOrDefault(arrayData[3].substring(0, arrayData[3].length()-3), 0) + 1);
                webBrowser.put(arrayData[2], webBrowser.getOrDefault(arrayData[2], 0) + 1);

                logCount++;
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("찾는 파일이 없어요!");
            e.printStackTrace();
        }

        Map<String, Integer> orderedMap = peakTime.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> newValue, LinkedHashMap::new));

        final Iterator<Map.Entry<String, Integer>> entryIterator = orderedMap.entrySet().iterator();
        System.out.println(entryIterator.next().getKey());


        try {
            File myObj = new File("output.log");
            if (myObj.createNewFile()) {
                System.out.println("파일을 생성했어요!: " + myObj.getName());
            } else {
                System.out.println("이미 존재하는 파일입니다.");
            }

            FileWriter myWriter = new FileWriter("output.log");

            myWriter.write("로그 수: " + logCount + "\n\n");

            myWriter.write("최다호출 APIKEY"+ "\n\n" + apiKey + "\n\n");
            myWriter.write("상태코드 별 횟수\n\n" +
                    "10 : " + serverCodeCount10 + "\n" +
                    "200 : " + serverCodeCount200 + "\n" +
                    "404 : " + serverCodeCount404 + "\n\n");
            myWriter.write("상위 3개의 API ServiceID와 각각의 요청 수\n\n" + apiService + "\n" +
                    "blog : " + "\n" +
                    "vdip : " + "\n" +
                    "image : " + "\n\n");
            myWriter.write("피크 시간대\n\n" + entryIterator.next().getKey() + "\n\n");
            myWriter.write("웹 브라우저 별 사용비율\n\n" + webBrowser + "\n" +
                    "IE : " + "\n" +
                    "Firefox : " + "\n" +
                    "Safari : " + "\n" +
                    "Chrome : " + "\n" +
                    "Opera : " + "\n\n");

            myWriter.close();
            System.out.println("변경사항을 저장했어요!");
        } catch (IOException e) {
            System.out.println("문제가 발생했어요.");
            e.printStackTrace();
        }
    }
}
