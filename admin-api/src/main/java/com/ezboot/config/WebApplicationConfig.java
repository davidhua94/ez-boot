package com.ezboot.config;

import com.ezboot.core.CurrentAdminArgumentResolver;
import com.ezboot.interceptor.AdminTokenInterceptor;
import com.ezboot.interceptor.TraceIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author David hua
 * @date 2019-08-16 22:00
 */
@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {
    @Autowired
    private TraceIdInterceptor traceIdInterceptor;

    @Autowired
    private AdminTokenInterceptor adminTokenInterceptor;

    @Autowired
    private CurrentAdminArgumentResolver currentAdminArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 这个顺序在最前面
        registry.addInterceptor(traceIdInterceptor).addPathPatterns("/**");

        // token拦截
        registry.addInterceptor(adminTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/info")
                .excludePathPatterns("/admin/logout");

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentAdminArgumentResolver);
    }

    /**
     * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
     * https://blog.csdn.net/xqnode/article/details/81382160
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
