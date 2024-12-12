package org.example.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface View {
     void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;
}//jsp 일수 있고 redirect 일 수 있기 때문에 인터페이스로 생성
