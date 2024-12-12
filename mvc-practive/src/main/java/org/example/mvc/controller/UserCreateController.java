package org.example.mvc.controller;

import org.example.mvc.model.User;
import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserCreateController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //유저를 추가해주는 코드가 필요함.
        UserRepository.save(new User(request.getParameter("userId"), request.getParameter("name")));
        return "redirect:/users";
        //요청으로 받은 userId와 Name을 생성해서 redirect /users로 이동한다.
        // 클라이언트로 응답을 내려주는데 , /users로 다시 요청을 보내게 되는데 ,
        //get , /users로 이동하면서 , UserListController로 이동하게 된다
    }
}
