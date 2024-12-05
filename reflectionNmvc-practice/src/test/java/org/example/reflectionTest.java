package org.example;

//목표 : @Controller 애노테이션이 붙은 클래스를 알아보기

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class reflectionTest {
    private final Logger log = LoggerFactory.getLogger(reflectionTest.class);
    @DisplayName("@Controller 애노테이션 설정의 클래스 찾아보자.")
    @Test
    void controllerScanTest() {
        Set<Class<?>> beans = getTypeAnnotations(List.of(Controller.class , Service.class));
        log.debug("add service beans : {}",beans);
        // * 결과
        //add service beans : [class org.example.controller.HomeController,
        // class org.example.controller.HomeService, class org.example.controller.HealthCheckController]

    }

    private Set<Class<?>> getTypeAnnotations(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example");//패키지는 어디서 찾을 것인가.
        Set<Class<?>> beans = new HashSet<>();
        //beans.addAll(reflections.getTypesAnnotatedWith(controllerClass));
        //해당 패키지내의 클래스 내에서 Controller 애노테이션을 붙은 것들을 찾겠다는 의미.
        //해당 hashSet에 담는 코드.

        //log.debug("beans : {}",beans);
        //* 결과
        //beans : [class org.example.controller.HomeController]
        //* healthCheackController추가후 결과
        //beans : [class org.example.controller.HomeController, class org.example.controller.HealthCheckController]

        //@service 애노테이션 추가하기
        //b\eans.addAll(reflections.getTypesAnnotatedWith(serviceClass));

        //* 리팩터링
        annotations.forEach(annotation -> {beans.addAll(reflections.getTypesAnnotatedWith(annotation));});
        return beans;
    }

    @Test
    void showClassInfo() {
        Class<User> userClass = User.class;
        log.debug("user.class getname : {}", userClass.getName());
        // user.class getname : org.example.model.User
        log.debug("user.class getDeclaredFields : {}", Arrays.stream(userClass.getDeclaredFields()).collect(Collectors.toList()));
        //user.class getDeclaredFields : [private java.lang.String org.example.model.User.userId, private java.lang.String org.example.model.User.userName]
        log.debug("user.class constructor : {}", Arrays.stream(userClass.getDeclaredConstructors()).collect(Collectors.toList()));
        //user.class constructor : [public org.example.model.User(java.lang.String,java.lang.String)]
        log.debug("user.class getDeclaredMethods : {}", Arrays.stream(userClass.getDeclaredMethods()).collect(Collectors.toList()));
        //user.class getDeclaredMethods : [public boolean org.example.model.User.equals(java.lang.Object),
        // public int org.example.model.User.hashCode(), public boolean org.example.model.User.equalUser(org.example.model.User)]
    }

    @Test
    void load() throws ClassNotFoundException {
        // 힙 영역에 로드되어있는 클래스 타입의 객체를 가지고 올 수 있다.
        // 방법 3가지
        //1.
        Class<User> userClass = User.class;

        //2.
        User user = new User("userId","userName");
        Class<? extends User> userClass2 = user.getClass();

        //3.
        Class<?> userClass3 = Class.forName("org.example.model.User");

        log.debug("user.class : {}", userClass); //user.class : class org.example.model.User
        log.debug("user2.class : {}", userClass2);//user2.class : class org.example.model.User
        log.debug("user3.class : {}", userClass3);//user3.class : class org.example.model.User

        assertThat(userClass ==userClass2).isTrue();
        assertThat(userClass2 ==userClass3).isTrue();
        assertThat(userClass3 ==userClass).isTrue();
    }
}
