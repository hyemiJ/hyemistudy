package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


public class UserDAOTest {
    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        //1. db_schema.sql 이라는 스크립트를 읽어와서 스크립트에 추가하겠다 라는 의미.
        DatabasePopulatorUtils.execute(populator,ConnectionManager.getDataSource());
        //2. execute메서드를 살펴보면 , 인자로 데이터 소스를 가지고 와야하는데 ,
        //현재는 없는 클래스에서 데이터소스를 받아오기 위해 먼저 구현. (TDD 방식)
    }

    @Test
    void createTest() throws SQLException {
        UserDAO userDAO = new UserDAO();// dao 클래스 생성
        userDAO.create(new User("user1","password","name","email"));

        //DAO란 Data Access Object의 약자로 ,
        // DB 작업을 수행할때 userDAO에게 해당 작업을 위임한다.
        //create 메서드를 호출할 때 해당값을 전달하면 dao 는 DB에 유저정보를 저장하는 역할을 수행한다.
        org.example.User user = userDAO.findByUserId("user1");
        //해당 유저 조회해보기

        assertThat(user).isEqualTo(new User("user1","password","name","email"));
        //위에서 저장한 정보와 같은지 검증하는 테스트
    }
}
