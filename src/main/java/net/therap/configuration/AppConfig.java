package net.therap.configuration;

import net.therap.interceptor.AuthenticationInterceptor;
import net.therap.interceptor.CacheInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author sadia.afroz
 * @since 5/18/21
 */
@Configuration
@EnableWebMvc
@ComponentScan({"net.therap"})
public class AppConfig extends WebMvcConfigurationSupport {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/WEB-INF/views/");
        vr.setSuffix(".jsp");
        return vr;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}
