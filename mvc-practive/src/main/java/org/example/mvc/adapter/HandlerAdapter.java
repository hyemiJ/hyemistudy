package org.example.mvc.adapter;

import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
    boolean supports(Object handler) ;
    //전달된 핸들러를 지원하는 adapter인지 반환
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
    //이 핸들러를 지원해주는 어뎁터이면 , 핸들러를 실행하고 결과값으로 modelAndView를 반환받는 메서드.
}
