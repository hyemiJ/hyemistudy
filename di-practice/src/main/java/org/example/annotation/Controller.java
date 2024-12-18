package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
//사용 처 : ( TYPE ) class , interface , enum
@Retention(RetentionPolicy.RUNTIME)
//Retention : 유지 기간
public @interface Controller {
}
