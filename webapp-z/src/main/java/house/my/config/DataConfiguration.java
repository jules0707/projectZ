package house.my.config;

import javax.servlet.ServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
@EnableJpaRepositories("house.my")
public class DataConfiguration {

  @Autowired
  private Environment environment;

  @Autowired
  ServletRequest servletRequest;

  @Autowired
  private SessionFactoryConfiguration sfc;

  @Bean
  public DataSource dataSource() {
    MysqlDataSource mysqlDataSource = createDataSource();
    return (DataSource) mysqlDataSource;
  }

  @Bean
  public HibernateTransactionManager transactionManager() {
    return new HibernateTransactionManager(sfc.sessionFactory().getObject());
  }

  private MysqlDataSource createDataSource() {

    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setUser(environment.getProperty("db.username"));
    mysqlDataSource.setPassword(environment.getProperty("db.password"));
    mysqlDataSource.setUrl(environment.getProperty("db.url"));
    return mysqlDataSource;
  }

}