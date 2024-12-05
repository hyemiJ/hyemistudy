package org.example.mvc;

import org.example.mvc.controller.Controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    private Map<String , Controller> mappings = new HashMap<>();
    //key : url path / value : 해당 controller
    //만약 "/user" -> userController 를 호출해달라 라는 의미
}
