package output;

import lombok.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class Writer {

    private static final String OUTPUT_FILE = "output.log";

    public static void fileWriter() {
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
            myWriter.write("웹 브라우저 별 사용비율\n\n" +
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
}