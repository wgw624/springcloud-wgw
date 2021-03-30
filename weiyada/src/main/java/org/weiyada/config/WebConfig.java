package org.weiyada.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.weiyada.config.enumclass.IgnoreUriEnum;
import org.weiyada.config.inteceptor.JwtInterceptor;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String []path = new String[]{
                "/user/login","/*.html","/error/**","/swagger-resources/**",
                "/webjars/**","/swagger-ui.html/**","/doc.html/**"
            };
        List<String> ignoreList = Lists.newArrayList(path);
        List<IgnoreUriEnum> enumList = IgnoreUriEnum.getIgnoreUriEnum();
        List<String> enumUriList = enumList.stream().map(x->{
           return  x.getUri();
        }).collect(Collectors.toList());

        if(!ObjectUtils.isEmpty(enumList)){
            ignoreList.addAll(enumUriList);
        }

        //拦截路径可自行配置多个 可用 ，分隔开
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(ignoreList);

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
