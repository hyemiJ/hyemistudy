package org.example.di;

import org.example.annotation.Inject;
import org.example.controller.UserController;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {

    private Set<Class<?>> preInstantiatedBeans;
    private Map<Class<?>, Object> beans = new HashMap<>();
    //클래스 타입 객체를 key로 가지며 , 생성된 인스턴스를 value로 가지는 Map

    public BeanFactory(Set<Class<?>> preInstantiatedBeans) {
        this.preInstantiatedBeans = preInstantiatedBeans;
        initialize();
    }

    private void initialize() {
        for (Class<?> clazz : preInstantiatedBeans) {
            Object instance = createInstance(clazz);
            beans.put(clazz,instance);
        }
    }

    private Object createInstance(Class<?> concreteClass) {
        //생성자
        Constructor<?> constructor = findConstructor(concreteClass);
        //파라미터
        List<Object> parameters = new ArrayList<>();

        for (Class<?> typeClass : Objects.requireNonNull(constructor).getParameterTypes()) {
            parameters.add(getBean(typeClass));
        }
        //인스턴스 생성
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


//    private Constructor<?> findConstructor(Class<?> clazz) {
//        // ReflectionUtils 클래스를 활용하여 , 생성자를 찾는데 , @Inject가 붙은 생성자만 찾도록함.
//        Set<Constructor> injectedConstructor = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));
//        if(injectedConstructor.isEmpty()) {
//            return null;
//        }
//        return injectedConstructor.iterator().next();
//        //첫번째의 값을 return
//    } // BeanFactoryUtills 클래스로 옮김.


    private Constructor<?> findConstructor(Class<?> clazz) {
        Constructor<?> constructor = BeanFactoryUtills.getInjectedConstructor(clazz) ;
        if(Objects.nonNull(constructor)) {
            return constructor;
        }else return clazz.getConstructors()[0];
    }

    public <T> T getBean(Class<T> reqiredType) {
        return (T) beans.get(reqiredType);
    }//클래스 타입의 객체를 key로 가지는 beans 에서 해당 인스턴스를 꺼내는 메소드.

}
