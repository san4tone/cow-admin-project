package com.cosmoplat.cowfarm.interceptor;

import com.cosmoplat.cowfarm.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 前后端约定：前端请求微服务时需要添加头信息Authorization ,内容为Bearer+空格+token
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("经过了拦截器");
        //无论如何都放行，具体能不能操作还是再具体的操作中去判断。
        //拦截器只是负责把请求头中包含token的令牌进行一个解析认证
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String token = authHeader.substring(7);//the part after "Bearer "
            try {
                Claims claims = jwtUtil.parseJWT(token);
                if (claims != null) {
                    String roles = (String) claims.get("roles");
                    System.out.println(roles);
                    if (roles.contains("factory")){
                        request.setAttribute("cz_claims",claims);
                    }
                    if (roles.contains("Breeder")){
                        request.setAttribute("syy_claims",claims);
                    }
                    if (roles.contains("Cleaners")){
                        request.setAttribute("qjy_claims",claims);
                    }
                    if (roles.contains("veterinary")){
                        request.setAttribute("sy_claims",claims);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("令牌不正确！");
            }
        }
        return true;
    }
}
