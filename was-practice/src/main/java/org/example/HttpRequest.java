package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private final RequestLine requestLine;

    public HttpRequest(BufferedReader reader) throws IOException {
        this.requestLine = new RequestLine(reader.readLine());
    }

    public boolean isGetRequest() {
        return requestLine.isGetRequest();
    }

    public boolean matchPath(String path) {
        return requestLine.mathPath(path);
    }

    public QueryStrings getQueryStrings() {
        return requestLine.getQueryString();
    }
}
