package org.example.mvc.view;

public interface ViewResolver {
    View resolveView(String viewName);
}//view name 을 받아서 view를 결정하는 역할.
