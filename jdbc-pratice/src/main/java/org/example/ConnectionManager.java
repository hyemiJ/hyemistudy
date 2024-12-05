package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    //메서드 안에 사용했던 문자열들을 상수로 추출하여 사용
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";
    private static final int MAXIMUM_POOL_SIZE = 40;
    private static final DataSource dataSource;

    static {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(DB_DRIVER);
        hikariDataSource.setJdbcUrl(DB_URL);
        hikariDataSource.setUsername(DB_USERNAME);
        hikariDataSource.setPassword(DB_PASSWORD);
        hikariDataSource.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
        //최대 풀 사이즈.
        hikariDataSource.setMinimumIdle(MAXIMUM_POOL_SIZE);
        //HikariCP가 풀에서 유지하려고 하는 유효 연결의 최소 수를 제어

        dataSource = hikariDataSource;
    }

    // 리팩터링으로 인한 DAO -> ConnectionManager 이식
    public static Connection getConnection() {
//        String url = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
//        String id = "sa";
//        String password = "";
//        try {
//            Class.forName("org.h2.Driver");
//            return DriverManager.getConnection(url,id,password);
//        } catch (Exception e) {
//            return null;
//        }// ConnectionPool 적용으로 인한 코드 삭제
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

//    public static DataSource getDataSource() {
//        //의존성에 주입하였던 h2DB와 HikariCP를 사용할것.
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setDriverClassName(DB_DRIVER);
//        hikariDataSource.setJdbcUrl(DB_URL);
//        hikariDataSource.setUsername(DB_USERNAME);
//        hikariDataSource.setPassword(DB_PASSWORD);
//        hikariDataSource.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
//        //최대 풀 사이즈.
//        hikariDataSource.setMinimumIdle(MAXIMUM_POOL_SIZE);
//        //HikariCP가 풀에서 유지하려고 하는 유효 연결의 최소 수를 제어
//
//        return hikariDataSource;
//    }

    public static DataSource getDataSource(){
        return dataSource;
    }//test코드에서 사용하던 메서드

}
