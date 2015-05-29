package house.my.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import house.my.config.DaoConfiguration;
import house.my.config.PropertiesConfiguration;
import house.my.config.SessionFactoryConfiguration;
import house.my.config.UtilsConfiguration;
import house.my.config.WebConfig;
import house.my.model.Address;
import house.my.model.Property;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
    DaoConfiguration.class,
    WebConfig.class,
    PropertiesConfiguration.class,
    SessionFactoryConfiguration.class,
    UtilsConfiguration.class })
@EnableAspectJAutoProxy
@EnableWebMvc
// @Ignore
public class PropertyDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  protected PropertyDao propertyDao;

  @Ignore
  @Test
  public void testFindHomes() {
    fail("Not yet implemented");
  }

  @Ignore
  @Test
  public void testCountProperty() {
    assertEquals(50, super.countRowsInTable("property"));
  }

  @Test
  public void assertPropertyLocation() {
    List<Property> properties = this.propertyDao.getAll();
    Property property1 = properties.get(0);
    assertEquals(new Address("treillieres"), property1.getAddress());
  }

  @Ignore
  @Test
  public void testUpdatePrice() {
    fail("Not yet implemented");
  }

}
