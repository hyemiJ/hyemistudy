package org.example.mvc;

import org.example.mvc.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    private Map<HandlerKey , Controller> mappings = new HashMap<>();
    //key : url path / value : 해당 controller
    //만약 "/user" -> userController 를 호출해달라 라는 의미

    void init(){
        //v1. 각각에 맞는 컨트롤러 리턴
        //문제점 발생 : Http method를 다양화 할 수 없음 (ex get , post ...)

        //mappings.put("/", new HomeController());
        // 어떤 경로가 없을경우 HomeController 실행
        //mappings.put("/users", new UserListController());

        //v2. Http method 를 사용하기 위해 HandlerKey를 사용
        mappings.put(new HandlerKey(RequestMethod.GET , "/"),new HomeController());
        mappings.put(new HandlerKey(RequestMethod.GET , "/users"),new UserListController());
        mappings.put(new HandlerKey(RequestMethod.POST , "/users"),new UserCreateController());
        mappings.put(new HandlerKey(RequestMethod.GET,"/user/form"), new ForwardController("/user/form.jsp"));
    }

    public Controller findController(HandlerKey urlpath){
        return mappings.get(urlpath);
        //urlPath 와 일치하는 컨트롤러를 리턴해주는 메소드
    };

}
