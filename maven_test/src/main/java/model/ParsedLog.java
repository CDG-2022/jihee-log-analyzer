package model;


import java.time.LocalDateTime;

public class ParsedLog {
    private String httpCode;
    private String apiKey;
    private String browser;
    // todo LocalDateTime 으로 받는다면 장점이 있을지?
    private String peekTime;

    public ParsedLog(String httpCode, String apiKey, String browser, String peekTime) {
        this.httpCode = httpCode;
        this.apiKey = apiKey;
        this.browser = browser;
        this.peekTime = peekTime;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBrowser() {
        return browser;
    }

    public String getPeekTime() {
        return peekTime;
    }
}
