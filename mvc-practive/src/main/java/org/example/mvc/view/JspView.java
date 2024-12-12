package org.example.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class JspView implements View {


    private final String viewname;

    public JspView(String viewname) {
        this.viewname = viewname;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        model.forEach(request::setAttribute);
        //위의 코드로 request.setAttribute("users", List.of()); 이런 식의 forEach를 하게 됨.
        //request 값이 있으면 , model에 setAttribute 하라는 코드.

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewname);
        dispatcher.forward(request, response); // v3. JspView로 이동
    }
}
