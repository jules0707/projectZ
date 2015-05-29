package house.my.config;

import house.my.utils.ZProperties;
import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;


@Configuration
public class PropertiesConfiguration {

  @Autowired
  private Environment environment;

  @Bean
  public ZProperties zProperties() {
    return new ZProperties();
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
    pspc.setLocation(new ClassPathResource("."));
    return pspc;
  }

  // common est mis ici car c'est la seule fois qu'il le sera lors des tests unitaires
  @Configuration
  @Profile("common")
  @PropertySource({ "classpath:/common.properties" })
  @Log4j
  public static class Common {

    public Common() {
      log.info("Loading common.properties");
    }
  }
  
  @Configuration
  @Profile("dev")
  @PropertySource({ "classpath:/dev.properties" })
  @Log4j
  public static class Dev {

    public Dev() {
      log.info("Loading dev.properties");
    }
  }

  @Configuration
  @Profile("stage")
  @PropertySource({ "classpath:/stage.properties" })
  @Log4j
  public static class Stage {

    public Stage() {
      log.info("Loading stage.properties");
    }
  }

  @Configuration
  @Profile("prod")
  @PropertySource({ "classpath:/prod.properties" })
  @Log4j
  public static class Prod {

    public Prod() {
      log.info("Loading prod.properties");
    }
  }

}