package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("users", List.of());
        return "/user/list.jsp";
        //root 디렉토리 (/webapps) 아래에 , user 하위의 list.jsp 를 의미한다.
    }
}
