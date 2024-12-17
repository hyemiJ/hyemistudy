package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HandlerKey;

public interface HandlerMapping {
    //Controller findController(HandlerKey handlerKey);
    // 애노테이션 타입으로 반환받을 수 있도록 반환값을 Controller -> Object 타입으로 변경
    Object findController(HandlerKey handlerKey);
}
