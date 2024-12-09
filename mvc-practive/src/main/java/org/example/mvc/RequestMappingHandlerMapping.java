package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    private Map<String , Controller> mappings = new HashMap<>();
    //key : url path / value : 해당 controller
    //만약 "/user" -> userController 를 호출해달라 라는 의미

    void init(){
        mappings.put("/", new HomeController());
        // 어떤 경로가 없을경우 HomeController 실행
    }

    public Controller findController(String urlpath){
        return mappings.get(urlpath);
        //urlPath 와 일치하는 컨트롤러를 리턴해주는 메소드
    };

}
