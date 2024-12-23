package org.example.mvc.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {

 private Object view;
 private Map<String, Object> model = new HashMap<String, Object>();

    public ModelAndView(String viewName) {
        view = viewName;
    }

    public Map<String,?> getModel() {
    return Collections.unmodifiableMap(model);//불변값으로 리턴
    }


    public String getViewName() {
        return (this.view instanceof String) ? (String) this.view : null;
    }
}
