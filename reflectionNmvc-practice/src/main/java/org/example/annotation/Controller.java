package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//유지기간은 runtime으로 설정
@Target(ElementType.TYPE)//사용할 어노테이션을 적용할 대상을 지정 : 타입(클래스, 인터페이스, Enum)
public @interface Controller {
}
