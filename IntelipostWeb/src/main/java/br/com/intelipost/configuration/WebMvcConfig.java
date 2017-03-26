package br.com.intelipost.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		if (!registry.hasMappingForPattern("/resources/**")) {
			registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
		}
		if (!registry.hasMappingForPattern("/static/**")) {
			registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		}
	/*	registry.addResourceHandler("/css/**").addResourceLocations("/css/**");
        registry.addResourceHandler("/img/**").addResourceLocations("/img/**");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/**");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/**");
*/	}

}