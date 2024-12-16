package org.example.mvc.adapter;

import org.example.mvc.controller.Controller;
import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleControllerHandlerAdapter implements HandlerAdapter{

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof Controller);
    }//전달된 핸들러가 컨트롤러를 인터페이스를 구현한 클래스라면 지원을 해주겠다.

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewName = ((Controller)handler).handleRequest(request, response);

        return new ModelAndView(viewName);
    }
}//
