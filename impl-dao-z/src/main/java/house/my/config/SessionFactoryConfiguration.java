package house.my.config;

import house.my.utils.ZProperties;

import java.util.Properties;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@Log4j
public class SessionFactoryConfiguration {

  @Autowired
  private Environment environment;

  @Autowired
  private ZProperties zProperties;

  @Bean
  public LocalSessionFactoryBean sessionFactory() {

    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e1) {
      log.warn(e1);
    }

    if (zProperties.isDev()) {
      try {
        Class.forName("org.jdbcdslog.DriverLoggingProxy");
      } catch (ClassNotFoundException e1) {
        log.warn(e1);
      }
    }

    LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
    sf.setHibernateProperties(hibernateProperties());
    sf.setPackagesToScan("house.my.model");

    return sf;
  }

  final Properties hibernateProperties() {
    return new Properties() {

      {
        this.put("hibernate.connection.driver_class", environment.getProperty("db.driverClassName"));
        this.put("hibernate.connection.url", environment.getProperty("db.url"));
        this.put("hibernate.connection.username", environment.getProperty("db.username"));
        this.put("hibernate.connection.password", environment.getProperty("db.password"));
        this.put("hibernate.connection.provider_class",
            "org.hibernate.service.jdbc.connections.internal.C3P0ConnectionProvider");

        // Options
        this.put("hibernate.connection.useUnicode", "true");
        this.put("hibernate.connection.defaultNChar", "true");
        this.put("hibernate.connection.characterEncoding", "utf-8");
        this.put("hibernate.dialect", environment.getProperty("db.dialect"));
        this.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        this.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        this.put("hibernate.jdbc.use_streams_for_binary", "true");
        this.put("hibernate.generate_statistics", "false");

        // connection pool options
        this.put("hibernate.c3p0.max_size", "20");
        this.put("hibernate.c3p0.min_size", "3");
        this.put("hibernate.c3p0.timeout", "5000");
        this.put("hibernate.c3p0.max_statements", "0");
        this.put("hibernate.c3p0.idle_test_period", "300");
        this.put("hibernate.c3p0.acquire_increment", "2");

        // Cache configuration
        this.put("hibernate.cache.provider_class",
            "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        this.put("hibernate.cache.use_query_cache", "false");
        this.put("hibernate.cache.use_second_level_cache", "false");
        this.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");

      }
      private static final long serialVersionUID = 1659735152505175326L;
    };
  }

}