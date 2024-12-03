package org.example;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServer {
    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);
    //프로젝트 관리 설정을 이렇게 설정해야지만 톰캣이 이 해당클래스를 찾음.
    // 톰캣은 root -webapps-WEB-INF - classes에서 자바 클래스를 찾기 떄문 .
    //webapps/WEB-INF/classes 경로를 outpath로 지정하는 이유는:
    //
    //Tomcat의 클래스 로딩 규칙을 따르기 위함.
    //표준 디렉토리 구조를 준수하여 보안과 유지보수를 쉽게 하기 위함.
    //WAR 파일 배포 시 문제가 없도록 하기 위함.
    public static void main(String[] args) throws Exception {
        // 웹 애플리케이션 디렉토리 경로 설정 (webapps/ 디렉토리를 기준으로 사용)
        String webappDirLocation = "webapp/";

        // Tomcat 서버 객체 생성
        Tomcat tomcat = new Tomcat();

        // Tomcat 서버의 포트 번호 설정 (8080번 포트로 설정)
        tomcat.setPort(8080);

        // 웹 애플리케이션 추가
        // 첫 번째 인자: "/"는 애플리케이션의 컨텍스트 경로를 의미
        // 두 번째 인자: 웹 애플리케이션의 실제 경로 (webappDirLocation 경로를 절대 경로로 변환)
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

        // 로깅: 웹 애플리케이션의 기본 디렉토리 정보 출력
        log.info("configuring app with basedir: {}", new File("" + webappDirLocation).getAbsolutePath());

        // Tomcat 서버 시작
        tomcat.start();

        // Tomcat 서버가 종료되지 않고 계속 실행 상태를 유지하도록 대기
        tomcat.getServer().await();
    }
}