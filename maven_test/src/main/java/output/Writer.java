package output;

import lombok.Data;
import parser.*;
import input.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static input.Reader.logCount;

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

            myWriter.write("로그 수: " + Reader.logCount + "\n\n");

            myWriter.write("최다호출 APIKEY" + "\n\n" + Parser.getApiKeyForWrite() + "\n\n");
            myWriter.write("상태코드 별 횟수\n\n" +
                    "10 : " + Parser.serverCodeCount10 + "\n" +
                    "200 : " + Parser.serverCodeCount200 + "\n" +
                    "404 : " + Parser.serverCodeCount404 + "\n\n");
            myWriter.write("상위 3개의 API ServiceID와 각각의 요청 수\n\n" +
                    Parser.getAPIServiceForWrite() + "\n\n");
            myWriter.write("피크 시간대\n\n" + Parser.getPeakTimeForWrite() + "\n\n");
            myWriter.write("웹 브라우저 별 사용비율\n\n");
            while (entryWebBrowserKeyListIterator.hasNext()) {
                myWriter.write(entryWebBrowserKeyListIterator.next() + " : " + Utils.getPercentage(entryWebBrowserValueListIterator.next(), logCount));
            }
            myWriter.close();
            System.out.println("변경사항을 저장했어요!");
        } catch (IOException e) {
            System.out.println("문제가 발생했어요.");
            e.printStackTrace();
        }
    }
}