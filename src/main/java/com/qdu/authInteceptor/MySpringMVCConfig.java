package com.qdu.authInteceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Alpha-LGC on 2019/2/13.
 */
/*@SpringBootConfiguration*/
public class MySpringMVCConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthInterceptor au;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(au).addPathPatterns("/**");
    }
}
