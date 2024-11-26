package org.example;

import java.io.IOException;

//서버를 통해 값을 리턴받기
//
//GET /calculate?a=11&operator=*&b=33
public class Main {
    public static void main(String[] args) throws IOException {
        new CustomWebApplicationServer(8080).start();
    }
}