package com.mycoffee.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ServletConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		MultipartConfigElement multipartConfig =
				new MultipartConfigElement("/", 20971520, 41943040, 20971520);
		registration.setMultipartConfig(multipartConfig);
	}

//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		super.onStartup(servletContext);
//		FilterRegistration.Dynamic filter = servletContext.addFilter("checkAuth", AuthorizationFilter.class);
//		filter.addMappingForUrlPatterns(null, false, "/admin/*");
//	}

//	@Override
//	protected Filter[] getServletFilters() {
//		return new Filter[] {new AuthorizationFilter()};
//	}
	
}
