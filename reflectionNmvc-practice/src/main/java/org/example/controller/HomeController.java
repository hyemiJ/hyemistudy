package org.example.controller;

import org.example.annotation.Controller;
import org.example.annotation.RequestMapping;
import org.example.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    //현재 요구사항은 클래스단위로 찾는것이기 떄문에 메소드에 명시할 필요는 없지만
    //메소드 단위에서도 만들어보자
    @RequestMapping(value = "/", method= RequestMethod.GET)
    public String home(HttpServletRequest request , HttpServletResponse response){
        return "home";
    }
}
