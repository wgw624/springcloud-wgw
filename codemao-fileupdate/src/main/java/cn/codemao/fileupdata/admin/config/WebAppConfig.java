package cn.codemao.fileupdata.admin.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: springcloud-wgw
 * @classname: WebAppConfig
 * @description: web资源访问
 * @author: wgw  weiguangwei@codemao.cn
 * @date: 2020/12/9 10:15 上午
 * @Version 1.0
 **/
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AppInterceptor()).addPathPatterns("/**").excludePathPatterns("");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/test/**").addResourceLocations("classpath:/source/");
    }
}
