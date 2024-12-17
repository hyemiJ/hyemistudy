package org.example.mvc;

import org.example.mvc.adapter.AnnotationHandlerAdapter;
import org.example.mvc.adapter.SimpleControllerHandlerAdapter;
import org.example.mvc.controller.Controller;
import org.example.mvc.adapter.HandlerAdapter;
import org.example.mvc.controller.HandlerKey;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.ModelAndView;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//톰캣이 실행하기 위해서는 servlet이여야하기 때문에 HttpServlet을 상속받음.
@WebServlet("/")// 어떠한 경로를 입력하더라도 호출될 수있도록 설정
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    //v5. RequestMappingHandlerMapping -> HandlerMapping을 상속받아 타입을 변경
    //private RequestMappingHandlerMapping requestMappingHandlerMapping;
    //v6. AnnotationHandlerMapping추가 후 리스트 타입으로 변경
    //리스트로 받는 이유 ? :AnnotationHandlerMapping 과 RequestMappingHandlerMapping을 가질것이기 때문에.
    //private HandlerMapping handlerMapping;
    private List<HandlerMapping> handlerMappings;
    //v3. viewResolver 사용
    private List<ViewResolver> viewResolvers;

    //v4. HandlerAdapter 사용
    private List<HandlerAdapter> handlerAdapters;

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
        //상위 인터페이스로 변경해서 선언하였음으로 , 새로이 선언하고 대입시키도록 변경
//        requestMappingHandlerMapping = new RequestMappingHandlerMapping();
//        requestMappingHandlerMapping.init();
        RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();//새로이 선언
        requestMappingHandlerMapping.init();
        AnnotationHandlerMapping annotationHandlerMapping = new AnnotationHandlerMapping("org.examle");
        handlerMappings = List.of(requestMappingHandlerMapping,annotationHandlerMapping);//대입

        viewResolvers = Collections.singletonList(new JspViewResolver());//viewResolvers 초기화

        handlerAdapters = List.of(new SimpleControllerHandlerAdapter(), new AnnotationHandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Dispatching request...");
        //v1 . String 으로 url을 넘기는 방법
        //다양한 http Method를 사용할 수 없음으로 HandlerKey 사용 -> v2
        //Controller handler = requestMappingHandlerMapping.findController(request.getRequestURI());


        //요청 uri에 대한 작업을 service로 위임.

        String requestUri = request.getRequestURI();
        RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());

        try {
            //v2.HandlerKey 사용  v5.requestMappingHandlerMapping.findController -> handlerMapping.findController
            //Controller handler = requestMappingHandlerMapping.findController(new HandlerKey(RequestMethod.valueOf(request.getMethod()),request.getRequestURI()));
            Object handler = handlerMappings.stream()
                    .filter(hm -> hm.findController(new HandlerKey(requestMethod,requestUri))!=null)
                    .map(hm -> hm.findController(new HandlerKey(requestMethod,requestUri)))
                    .findFirst()
                    .orElseThrow(()->new ServletException("no handler ! method :"+requestMethod+", uri:"+requestUri));
            //String viewname = handler.handleRequest(request,response);
            //log.info("viewname = {}", viewname);

            //v4.HandlerAdapter활용
            HandlerAdapter adapter =handlerAdapters.stream()
                    .filter(ad -> ad.supports(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No adapter found for handler: " + handler));

            ModelAndView modelAndView = adapter.handle(request, response, handler);
//            RequestDispatcher dispatcher = request.getRequestDispatcher(viewname);
//            dispatcher.forward(request, response); // v3. JspView로 이동 why ?jsp로 화면을 forward하는

            //v3 .viewResolver 를 통한 뷰를 받아오기.
           for (ViewResolver viewResolver : viewResolvers) {
               View view = viewResolver.resolveView(modelAndView.getViewName());
               view.render(modelAndView.getModel(), request, response);
           }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
