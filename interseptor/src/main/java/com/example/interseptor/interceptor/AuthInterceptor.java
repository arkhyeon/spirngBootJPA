package com.example.interseptor.interceptor;

import com.example.interseptor.annotation.Auth;
import com.example.interseptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI()).query(request.getQueryString()).build().toUri();

        log.info("request url : {}",url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        // service default public but Auth checking
        if(hasAnnotation){
            // Auth Check
            String query = uri.getQuery();
            log.info("query : {}", query);
            if(query.equals("name=steve")){
                return true;
            }
            throw new AuthException();
        }

        return true;
    }

    public Boolean checkAnnotation(Object handler, Class clazz){
        // resource javascript html
        if( handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        // annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if(handlerMethod.getMethodAnnotation(clazz) != null || handlerMethod.getBeanType().getAnnotation(clazz) != null){
            // Auth annotation 이 있을 때는 true
            return true;
        }

        return false;

    }
}
