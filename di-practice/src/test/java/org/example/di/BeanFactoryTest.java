package org.example.di;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {
    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        reflections = new Reflections("org.example");
        Set<Class<?>> clazz = getTypeAnnotationWith(Controller.class, Service.class);
        //@Controller , @Service 가 붙은 클래스들을 조회하여 clazz에 담음.
        //최종적으로 clazz 는 현재 UserService 와 UserController 가 된다.

        beanFactory = new BeanFactory(clazz);
    }//test메서드가 호출되기전에 실행됨으로 초기화 시키기


    //(Class<? extends Annotation> ... annotation)
    //애노테이션 타입의 객체가 여러개 들어 올 수도 있다.
    private Set<Class<?>> getTypeAnnotationWith(Class<? extends Annotation> ... annotations) {
        Set<Class<?>> beans = new HashSet<>();
        //결과를 저장할 빈 Set 컬렉션

        for(Class<? extends Annotation> anno : annotations){
            beans.addAll(reflections.getTypesAnnotatedWith(anno));
        }//@Controller , @Service 가 붙은 클래스들을 조회하여 beans 라는 Set에 추가

        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);
        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
    }
}