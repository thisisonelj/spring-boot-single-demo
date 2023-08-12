package com.example.spring.boot.demo.account.interceptor;

import com.example.spring.boot.demo.account.entity.power.AccountUserDO;
import com.example.spring.boot.demo.account.service.power.AccountUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author liujun
 * @Date 2023/8/10 16:32
 * @Description
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private AccountUserService accountUserService;
    @Qualifier("redisOperation")
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginToken = request.getHeader("token");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Expose-Headers", "errorInfo");
        if (StringUtils.isEmpty(loginToken) || "undefined".equals(loginToken)|| "null".equals(loginToken)) {
            return true;
        } else {
            AccountUserDO accountUserDO = (AccountUserDO) redisTemplate.opsForValue().get(loginToken);
            if (accountUserDO == null) {
                response.setHeader("errorInfo", "token diff");
                return false;
            }
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
