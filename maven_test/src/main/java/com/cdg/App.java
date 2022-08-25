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

public class App {

    private static final String OUTPUT_FILE = "output.log";

    public static void main(String[] args) {

        Reader.fileReader();

        Map<String, Integer> orderedApiKeyMap = Parser.makeOrderedMap(Parser.API_KEY);
        Map<String, Integer> orderedApiServiceMap = Parser.makeOrderedMap(Parser.API_SERVICE);
        Map<String, Integer> orderedPeakTimeMap = Parser.makeOrderedMap(Parser.PEAK_TIME);
        Map<String, Integer> orderedWebBrowserMap = Parser.makeOrderedMap(Parser.WEB_BROWSER);

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

        Writer.fileWriter();
    }
}
