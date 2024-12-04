package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class ConnectionManager {

    public static DataSource getDataSource() {
        //의존성에 주입하였던 h2DB와 HikariCP를 사용할것.
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName("org.h2.Driver");
        hikariDataSource.setJdbcUrl("jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1");
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");

        return hikariDataSource;
    }
}
