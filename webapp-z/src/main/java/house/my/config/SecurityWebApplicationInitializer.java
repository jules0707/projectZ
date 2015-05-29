package house.my.config;

import house.my.web.filter.SpringSecurityFilterUser;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

  @Override
  protected void afterSpringSecurityFilterChain(ServletContext
      servletContext) {
    insertFilters(servletContext, new SpringSecurityFilterUser());
  }

}
