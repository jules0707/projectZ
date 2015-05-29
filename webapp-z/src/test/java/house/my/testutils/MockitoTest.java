package house.my.testutils;

import house.my.dao.MemberDao;
import house.my.dao.PropertyDao;
import house.my.dao.RoleDao;
import house.my.dao.impl.MemberDaoImpl;
import house.my.dao.impl.RoleDaoImpl;
import house.my.model.Property;
import house.my.services.ZUtilsService;
import house.my.services.impl.MemberServiceImpl;
import house.my.services.impl.PropertyServiceImpl;
import house.my.services.impl.ZUtilsServiceImpl;

import java.util.Date;

import lombok.extern.log4j.Log4j;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@Ignore
@Log4j
@SuppressWarnings("synthetic-access")
public class MockitoTest {

  protected static ZUtilsService zUtilsService = new
      ZUtilsServiceImpl();

  @InjectMocks
  protected static MemberServiceImpl memberServiceImpl = new MemberServiceImpl();
  @Mock
  protected static PropertyDao propertyDaoImpl;

  @Mock
  protected static MemberDao memberDaoImpl = new MemberDaoImpl();
  @Mock
  protected static RoleDao roleDaoImpl = new RoleDaoImpl();
  @Mock
  protected static PropertyServiceImpl propertyService = new PropertyServiceImpl();

  @InjectMocks
  protected static PropertyServiceImpl propertyServiceImpl = new PropertyServiceImpl();

  public static final Date DATE_NOW = new Date();

  // @SuppressWarnings("unchecked")
  @Before
  public void initMocks() {

    MockitoAnnotations.initMocks(this);

    propertyServiceImpl.setMemberService(memberServiceImpl);
    propertyServiceImpl.setPropertyDao(propertyDaoImpl);

    memberServiceImpl.setMemberDao(memberDaoImpl);
    memberServiceImpl.setRoleDao(roleDaoImpl);

    try {
      Mockito.when(memberDaoImpl.getMemberFromLogin(
          MockitoMember.JULES.getLogin())).
          thenReturn(MockitoMember.JULES);

      Mockito.when(
          propertyService.getPropertiesForMember(
              MockitoMember.JULES)).thenCallRealMethod();

      Mockito.when(memberDaoImpl.getMemberFromLogin(MockitoMember.BOB.getLogin())).
          thenReturn(MockitoMember.BOB);

      Mockito.when(
          propertyService.getPropertiesForMember(
              MockitoMember.BOB)).thenCallRealMethod();

    } catch (Exception e) {
      e.printStackTrace();
    }

    // Mockito.when(
    // propertyServiceImpl.getPropertiesForMember(
    // MockitoMember.BOB
    // )
    // ).thenCallRealMethod();

    // Mockito.when(
    // propertyServiceImpl.getPropertiesForMember(
    // Matchers.anyInt(),
    // Matchers.anyInt(),
    // Matchers.anyString(),
    // Matchers.anyString())).
    // thenCallRealMethod();

  }

  public PropertyBuilder newProperty() {
    try {
      return new PropertyBuilder(new Property());
    } catch (Exception e) {
      log.error(e);
    }
    return null;
  }
}
