package org.weiyada.config.inteceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.weiyada.base.exception.CustomException;
import org.weiyada.config.enumclass.ExceptionEnum;
import org.weiyada.config.enumclass.IgnoreUriEnum;
import org.weiyada.entity.Audience;
import org.weiyada.entity.UserAuth;
import org.weiyada.util.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@Slf4j
public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private Audience audience;

    @Autowired
    private UserAuth userAuth;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
//            if (jwtIgnore != null) {
//                return true;
//            }
        }

        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        // 获取请求头信息authorization信息
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        log.info("## authHeader= {}", authHeader);

        if (StringUtils.isBlank(authHeader)) {
            log.info(request.getRequestURI()+"### 用户未登录，请先登录 ###");
//            throw new CustomException(ResultCode.USER_NOT_LOGGED_IN);
            throw new CustomException(ExceptionEnum.USER_NOT_LOGGED_IN);
        }
        // 获取token
        final String token = authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)?authHeader.substring(7):authHeader;
        if (audience == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            audience = (Audience) factory.getBean("audience");
        }

        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
        Claims claims = JwtTokenUtil.parseJWT(token, audience.getBase64Secret());
        userAuth = new UserAuth();
        userAuth.setUserId(claims.get("userId",Long.class));
        userAuth.setLoginName(claims.get("loginName",String.class));
        return true;

    }




}
