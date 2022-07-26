package com.cdg;

import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle error
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            File myObj = new File("input.log");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            File myObj = new File("output.log");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("output.log");

            myWriter.write("최다호출 APIKEY\n\n");
            myWriter.write("상태코드 별 횟수\n\n" +
                    "10 : " + "\n" +
                    "200 : " + "\n" +
                    "404 : " + "\n\n");
            myWriter.write("상위 3개의 API ServiceID와 각각의 요청 수\n\n" +
                    "blog : " + "\n" +
                    "vdip : " + "\n" +
                    "image : " + "\n\n");
            myWriter.write("피크 시간대\n\n");
            myWriter.write("웹 브라우저 별 사용비율\n\n" +
                    "IE : " + "\n" +
                    "Firefox : " + "\n" +
                    "Safari : " + "\n" +
                    "Chrome : " + "\n" +
                    "Opera : " + "\n\n");

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
