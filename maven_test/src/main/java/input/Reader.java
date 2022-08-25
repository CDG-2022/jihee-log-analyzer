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
    public static int logCount = 0;

    public static void fileReader() {
        try {
            File myObj = new File(INPUT_FILE);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                Parser parser = new Parser();
                parser.parse(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("찾는 파일이 없어요!");
            e.printStackTrace();
        }
    }
}