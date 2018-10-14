package com.srn.api.interceptor;

import com.srn.api.model.SrnResponse;
import com.srn.api.utils.SecurityUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

@Component
public class RestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String queryParam = request.getQueryString();
        if (!uri.contains("provision") && queryParam != null) {
            String s = URLDecoder.decode(queryParam.split("=")[1], "UTF-8");
            boolean valid = SecurityUtils.getInstance().isSessionValid(s);
            if (!valid) {
                response.getWriter().write(new SrnResponse<String>().toString());
                response.setStatus(HttpStatus.SC_FORBIDDEN);
            }
            return valid;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}