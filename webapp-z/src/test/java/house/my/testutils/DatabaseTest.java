package house.my.testutils;

import house.my.config.DaoConfiguration;
import house.my.config.PropertiesConfiguration;
import house.my.config.SessionFactoryConfiguration;
import house.my.config.UtilsConfiguration;
import house.my.config.WebConfig;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { DaoConfiguration.class, WebConfig.class,
    PropertiesConfiguration.class,
    SessionFactoryConfiguration.class,
    UtilsConfiguration.class })
@EnableAspectJAutoProxy
@EnableWebMvc
// utiliser plutôt -Dspring.profiles.active=common,dev/rec
// @ActiveProfiles("common")
@Ignore
public class DatabaseTest {

  @Autowired
  protected MessageSource messageSource;

  @Autowired
  protected SessionFactory sessionFactory;

  @Before
  public void beforeClass() {
    sessionFactory.openSession();
  }
}
