package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HandlerKey;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//톰캣이 실행하기 위해서는 servlet이여야하기 때문에 HttpServlet을 상속받음.
@WebServlet("/")// 어떠한 경로를 입력하더라도 호출될 수있도록 설정
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    //v3. viewResolver 사용
    private List<ViewResolver> viewResolvers;

    //tomcat 이 HttpServlet을 싱글톤 , 객체 1개로 만드는데
    //그때 서블릿이 만들어지면서 아래의 init 메소드가 호출된다.
    //requestMappingHandlerMapping이
    /*
        void init(){
        mappings.put("/", new HomeController());
        // 어떤 경로가 없을경우 HomeController 실행
    }
     */
    // 해당 메서드를 실행하면서 Map을 초기화 하도록 하였다.
    @Override
    public void init() throws ServletException {
       //초기화
        requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.init();

        viewResolvers = Collections.singletonList(new JspViewResolver());//viewResolvers 초기화
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Dispatching request...");
        //v1 . String 으로 url을 넘기는 방법
        //다양한 http Method를 사용할 수 없음으로 HandlerKey 사용 -> v2
        //Controller handler = requestMappingHandlerMapping.findController(request.getRequestURI());

        //v2.HandlerKey 사용
        Controller handler = requestMappingHandlerMapping.findController(new HandlerKey(RequestMethod.valueOf(request.getMethod()),request.getRequestURI()));
        //요청 uri에 대한 작업을 service로 위임.
        try {
            String viewname = handler.handleRequest(request,response);
            log.info("viewname = {}", viewname);
//            RequestDispatcher dispatcher = request.getRequestDispatcher(viewname);
//            dispatcher.forward(request, response); // v3. JspView로 이동 why ?jsp로 화면을 forward하는

            //v3 .

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
