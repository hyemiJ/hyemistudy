package org.example;

import org.slf4j.*;

import org.example.calculate.PositiveNumber;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);
    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        logger.info("새로운 클라이언트 , ({})가 시작됩니다.",Thread.currentThread().getName());

        try(InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            //inputStream 을 BufferReader 로 바꾼 이유 ? : Line by Line 으로 읽고 싶었기 때문.
            //그로 인한 작업 ? : InputStreamReader 로 InputStream을 감싸줌.
            // 이를 BufferedReader로 한번 더 감싸줌.
            DataOutputStream dos = new DataOutputStream(out);
            HttpRequest request = new HttpRequest(br);
            if (request.isGetRequest() && request.matchPath("/calculate")) {
                QueryStrings queryStrings = request.getQueryStrings();

                int a = Integer.parseInt(queryStrings.getValue("a"));
                String operator = queryStrings.getValue("operator");
                int b = Integer.parseInt(queryStrings.getValue("b"));

                int result = CalCulator.calculateInterface2(new PositiveNumber(a), operator, new PositiveNumber(b));
                byte[] body = String.valueOf(result).getBytes(StandardCharsets.UTF_8);

                HttpResponse response = new HttpResponse(dos);
                response.response200Header("application/json", body.length);
                response.responseBody(body);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}// 클라이언트의 요청을 핸들러 해주는 역할의 클래스
