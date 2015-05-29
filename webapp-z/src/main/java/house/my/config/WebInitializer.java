package house.my.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { WebConfig.class, DaoConfiguration.class, MessageConfiguration.class,
        PropertiesConfiguration.class, SecurityConfiguration.class,
        SessionFactoryConfiguration.class, UtilsConfiguration.class };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { WebConfig.class };
  }

}
