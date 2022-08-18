package model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParsedLog {
    private String httpCode;
    private String apiKey;
    private String browser;
    // todo LocalDateTime 으로 받는다면 장점이 있을지?
    private String peakTime;


    public ParsedLog(String httpCode, String apiKey, String browser, String peakTime) {
        this.httpCode = httpCode;
        this.apiKey = apiKey;
        this.browser = browser;
        this.peakTime = peakTime;
    }
}
