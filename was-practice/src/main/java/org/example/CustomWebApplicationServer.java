package org.example;

import org.example.calculate.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer {

    private final int port;
    private static final Logger log = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(port)){
            log.info("Server started port : {}", port);
            //해당 포트번호로 서버 소켓을 만듬

            Socket clientSocket;
            log.info("Waiting for client.");

            //accept()로 해당 클라이언트를 기다리게 함.
            while ((clientSocket=serverSocket.accept()) !=null){
                //클라이언트 연결을 의미
                log.info("Client connected");

                /*
                step 1. 사용자 요청을 mainThread 가 처리하도록 한다.
                 */
//                try(InputStream in = clientSocket.getInputStream();
//                    OutputStream out = clientSocket.getOutputStream()){
//                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
//                    //inputStream 을 BufferReader 로 바꾼 이유 ? : Line by Line 으로 읽고 싶었기 때문.
//                    //그로 인한 작업 ? : InputStreamReader 로 InputStream을 감싸줌.
//                    // 이를 BufferedReader로 한번 더 감싸줌.
//                    DataOutputStream dos = new DataOutputStream(out);
//                    //DataOutputStream 을 활용해 OutputStream 을 감싸줌.

                    //HTTP 프로토콜이 어떻게 생겼는지 보자.
//                    String line;
//                    while ((line=br.readLine())!=""){
//                        log.info(line);
//                    }
                    //1. 메인스레드가 처리하도록 만듬.
//                    HttpRequest request = new HttpRequest(br);
//                    if(request.isGetRequest() && request.matchPath("/calculate")){
//                        QueryStrings queryStrings =  request.getQueryStrings();
//
//                        int a = Integer.parseInt(queryStrings.getValue("a"));
//                        String operator = queryStrings.getValue("operator");
//                        int b = Integer.parseInt(queryStrings.getValue("b"));
//
//                        int result = CalCulator.calculateInterface2(new PositiveNumber(a),operator,new PositiveNumber(b));
//                        byte[] body = String.valueOf(result).getBytes(StandardCharsets.UTF_8);
//
//                        HttpResponse response = new HttpResponse(dos);
//                        response.response200Header("application/json",body.length);
//                        response.responseBody(body);
//                    }
//                }
                //2. 요청마다 개별 thread 생성
                new Thread(new ClientRequestHandler(clientSocket)).start();
            }
        }
    }
}
