package org.example.mvc.controller;

import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//annotation 적용전 인터페이스 활용
//public class HomeController implements Controller {
//    @Override
//    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        return "/home";// homeController 가 호출되면 , home 이라는 화면을 노출해달라는 의미
//    }
//}

//annotation 적용 후
@Controller
public class HomeController {
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home";// homeController 가 호출되면 , home 이라는 화면을 노출해달라는 의미
    }
}