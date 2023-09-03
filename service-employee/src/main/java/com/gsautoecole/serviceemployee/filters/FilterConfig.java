package com.gsautoecole.serviceemployee.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

//    @Autowired
//    private MyFilter fakeAutoEcoleFilter;
    
//    @Autowired
//    private AutoEcoleIdFilter filterID;
//    @Bean
//    public FilterRegistrationBean<MyFilter> fakeAutoEcoleFilterRegistration() {
//        FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(fakeAutoEcoleFilter);
//        registration.addUrlPatterns("/tkhrbi9a/*"); // Ajoutez ici les URL que vous souhaitez filtrer
//        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return registration;
//    }
//    
    
    
    @Bean
    public FilterRegistrationBean<AutoEcoleIdFilter> customAutoEcoleIdFilter() {
        FilterRegistrationBean<AutoEcoleIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AutoEcoleIdFilter());
        registrationBean.addUrlPatterns("/"); // Replace "/api/" with the appropriate URL pattern
        return registrationBean;
    }


}
