package org.csu.hoaserver.interceptor;

import context.UserContext;
import enumeration.ResponseCode;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String requestURI = request.getRequestURI();

        if (requestURI.contains("/login")) {
            log.info("登录请求，放行");
            return true;
        }
        if (requestURI.contains("/register")) {
            log.info("注册请求，放行");
            return true;
        }
        if(token == null || token.isEmpty()) {
            log.info("令牌为空，响应401");
            response.setStatus(ResponseCode.UNAUTHORIZED.getCode());
            return false;
        }

        try{
            Claims claims = JwtUtil.parseToken(token);
            Integer userId = (Integer) claims.get("id");
            log.info("当前员工id：[{}]", userId);
            UserContext.setCurrentId(userId);
        }catch(Exception e){
            log.info("令牌非法，响应401");
            response.setStatus(ResponseCode.UNAUTHORIZED.getCode());
            return false;
        }

        log.info("令牌合法，放行");
        return true;
    }
}
