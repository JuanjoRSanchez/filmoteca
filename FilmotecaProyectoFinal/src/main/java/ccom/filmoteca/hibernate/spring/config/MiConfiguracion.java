package ccom.filmoteca.hibernate.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MiConfiguracion {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		
		return new  WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
	//			registry.addMapping("filmania/v1/**")
	//				.allowedOrigins("http://localhost:9013")
	//				.allowedMethods("GET", "POST","PUT", "DELETE")
	//				.maxAge(3600);
			}
			
			
		};
	}
	
	 
}
