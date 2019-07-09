package in.co.indusnet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import in.co.indusnet.config.interceptor.InterceptorService;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
//	
//	@Autowired
//	private InterceptorService interceptorService;
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(interceptorService);
//	}
}
	