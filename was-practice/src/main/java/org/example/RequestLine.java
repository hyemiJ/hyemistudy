package org.example;

import java.util.Objects;

public class RequestLine {

    private final String method; //GET
    private String urlPath; // /calculate
    private QueryStrings queryStrings; //a=11&operator=*&b=33
    /*
        GET /calculate?a=11&operator=*&b=33 HTTP/1.1
         */
    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0]; //"GET"

        String[] urlPathTokens = tokens[1].split("\\?");
        //"/calculate?a=11&operator=*&b=33 HTTP/1.1"

        if (urlPathTokens.length == 2) { // 쿼리스트링이 있다면.
            this.urlPath = urlPathTokens[0]; // URL Path 설정
            this.queryStrings = new QueryStrings(urlPathTokens[1]); // Query String 설정
        }
    }

    public RequestLine(String method, String urlPath, String queryString ) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryStrings = new QueryStrings(queryString);
    }

    public boolean isGetRequest() {
        return "GET".equals(this.method);
    }

    public boolean mathPath(String path) {
        return path.equals(this.urlPath);
    }

    public QueryStrings getQueryString() {
        return queryStrings;
    }
}

