package com.srn.api.interceptor;

import com.srn.api.model.SrnResponse;
import com.srn.api.model.dto.SrnErrorDto;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

@Component
public class RestInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String queryParam = request.getQueryString();
        if (!uri.contains("provision") && !uri.contains("ping") ) {
            //String s = URLDecoder.decode(queryParam.split("\\?")[1].split("=")[1], "UTF-8");
            String s = URLDecoder.decode(queryParam.split("=")[1], "UTF-8");
            boolean valid = SecurityUtils.getInstance().isSessionValid(s);
            if (!valid) {

                SrnErrorDto e = new SrnErrorDto();
                e.setErrorMessage(SrnErrorDto.ERROR_MESSAGE_FORBIDDEN);

                SrnResponse<String> errorResponse = new SrnResponse<>();
                errorResponse.setTimestamp(FormatterUtils.getLongCurrentTimestamp());
                errorResponse.setData(SecurityUtils.getInstance().setData(e).setMethod(SecurityUtils.Method.DATA_ENCRYPT).build());
                response.getWriter().write(errorResponse.toString());
                response.setStatus(HttpStatus.SC_FORBIDDEN);
                LOGGER.info("[INFO] - "+ getClass().getSimpleName() +" - invalid session [{}]", s);

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