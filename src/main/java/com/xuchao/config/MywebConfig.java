package com.xuchao.config;

import com.xuchao.filter.AccessControlAllowFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//表示这个类是配置类
public class MywebConfig {
    @Bean //下面方法当成bean对象
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new AccessControlAllowFilter());
        frBean.addUrlPatterns("/*");
        return frBean;
    }
}
