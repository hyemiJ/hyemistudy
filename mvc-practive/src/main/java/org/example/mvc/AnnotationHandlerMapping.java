package org.example.mvc;


import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;
import org.example.mvc.controller.HandlerKey;
import org.example.mvc.controller.RequestMethod;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {
    private final Object[] basePakage;
    private Map<HandlerKey,AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePakage) {
        this.basePakage = basePakage;
    }

    public void init(){
        Reflections reflections = new Reflections(basePakage);//basePakeage(-> org.example) 하위의
        Set<Class <?>> classes  =reflections.getTypesAnnotatedWith(Controller.class); //@Controller 가 있는 클래스를 추적
        classes.forEach(clazz ->

                Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> {
                    //추적한 클래스드을 기반으로 , RequestMapping을 다시한번 추적.
                    RequestMapping requestMappingAnnotation = declaredMethod.getDeclaredAnnotation(RequestMapping.class);

                    Arrays.stream(getRequestMethods(requestMappingAnnotation))
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod,requestMappingAnnotation.value()),
                                    new AnnotationHandler(clazz, declaredMethod)
                            ));

                })
        );
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMapping) {
        return requestMapping.method();
    }

    @Override
    public Object findController(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
