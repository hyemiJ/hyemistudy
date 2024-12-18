package org.example.di;

import org.example.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtills {

    public static Constructor<?> getInjectedConstructor(Class<?> clazz) {
        // ReflectionUtils 클래스를 활용하여 , 생성자를 찾는데 , @Inject가 붙은 생성자만 찾도록함.
        Set<Constructor> injectedConstructor = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));
        if(injectedConstructor.isEmpty()) {
            return null;
        }
        return injectedConstructor.iterator().next();
        //첫번째의 값을 return
    }
}
