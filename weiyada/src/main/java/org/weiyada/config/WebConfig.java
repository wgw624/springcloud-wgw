package org.weiyada.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.weiyada.config.inteceptor.JwtInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String []path = new String[]{
                "/login","/*.html","/swagger-resources/**",
                "/webjars/**","/swagger-ui.html/**"
            };
        //拦截路径可自行配置多个 可用 ，分隔开
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(path);

    }

    /**
     * 跨域支持
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
                .maxAge(3600 * 24);
    }
}
