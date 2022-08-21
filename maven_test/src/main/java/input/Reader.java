package input;

import org.apache.commons.lang3.StringUtils;
import parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reader {
    public static final String INPUT_FILE = "input.log";
    public static String data = "";
    public static int serverCodeCount10 = 0;
    public static int serverCodeCount200 = 0;
    public static int serverCodeCount404 = 0;
    public static int logCount = 0;

    public static final Map<String, Integer> API_KEY = new HashMap<>();
    public static final Map<String, Integer> API_SERVICE = new HashMap<>();
    public static final Map<String, Integer> PEAK_TIME = new HashMap<>();
    public static final Map<String, Integer> WEB_BROWSER = new HashMap<>();

    public static void fileReader() {
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
                PEAK_TIME.put(arrayData[3].substring(0, arrayData[3].length() - 3), PEAK_TIME.getOrDefault(arrayData[3].substring(0, arrayData[3].length() - 3), 0) + 1);
                WEB_BROWSER.put(arrayData[2], WEB_BROWSER.getOrDefault(arrayData[2], 0) + 1);

                logCount++;
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("찾는 파일이 없어요!");
            e.printStackTrace();
        }
    }
}