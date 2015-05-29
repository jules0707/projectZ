package house.my.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import house.my.model.Address;
import house.my.model.Member;
import house.my.model.Property;
import house.my.testutils.MockitoMember;
import house.my.testutils.MockitoProperty;
import house.my.testutils.MockitoTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

//@Ignore
public class PropertyServiceImplTest extends MockitoTest {

  // @Autowired
  // PropertyService propertyServiceImpl = new PropertyServiceImpl();
  // @Autowired
  // MemberService memberServiceImpl = new MemberServiceImpl();

  @Test
  public void isAuthorizedToRemovePropertyTest() {

    // assertFalse(propertyServiceImpl
    // .isAuthorizedToRemoveProperty(MockitoProperty.PROPERTY_NANTES_04,
    // MockitoMember.NULL));
    // assertFalse(propertyServiceImpl.isAuthorizedToRemoveProperty(null,
    // MockitoMember.ADMIN));
    // assertTrue(propertyServiceImpl
    // .isAuthorizedToRemoveProperty(MockitoProperty.PROPERTY_PARIS_01,
    // MockitoMember.ADMIN));
    assertTrue(propertyServiceImpl.isAuthorizedToRemoveProperty(MockitoProperty.PROPERTY_PARIS_02,
        MockitoMember.BOB));
    assertFalse(propertyServiceImpl.isAuthorizedToRemoveProperty(MockitoProperty.PROPERTY_PARIS_01,
        MockitoMember.BOB));
    assertFalse(propertyServiceImpl
        .isAuthorizedToRemoveProperty(MockitoProperty.PROPERTY_PARIS_01,
            MockitoMember.JULES));
  }

  @Ignore
  @Test
  public void getPropertiesForMemberTest() {

    Address treillieres = new Address("treillieres");
    Address paris = new Address("paris");
    Address nantes = new Address("nantes");
    //
    Member bob = new Member("bob");
    Member max = new Member("max");
    Member carl = new Member("carl");
    Member gill = new Member("gill");
    Member eliot = new Member("eliot");
    Member jules = new Member("jules");
    jules.setLogin("jules07");
    jules.setIdMember(65);

    List<Member> membersForParisAddress = new ArrayList<Member>();
    membersForParisAddress.add(carl);
    membersForParisAddress.add(max);
    membersForParisAddress.add(bob);
    membersForParisAddress.add(gill);

    List<Member> membersForNantesAddress = new ArrayList<Member>();
    membersForNantesAddress.add(carl);
    membersForNantesAddress.add(eliot);
    membersForNantesAddress.add(bob);

    List<Member> membersForTreillieresAddress = new ArrayList<Member>();
    membersForTreillieresAddress.add(jules);

    final Property propertyParis = new Property(2, new BigDecimal(11090000), paris, membersForParisAddress, null);
    final Property propertyNantes = new Property(65, new BigDecimal(311090), nantes, membersForNantesAddress, null);
    final Property propertyTreillieres = new Property(32, new BigDecimal(4311090), treillieres,
        membersForTreillieresAddress, null);
    Set propertiesJulesSet = new HashSet<Property>() {
      {
        add(propertyTreillieres);
      }
    };

    // jules.setProperties(propertiesJulesSet);

    assertEquals("jules07", MockitoMember.JULES.getLogin());
    assertEquals("jules", MockitoMember.JULES.getName());
    assertEquals(new Integer(635), MockitoMember.JULES.getIdMember());
    // assertEquals(jules, membersForTreillieresAddress.get(0));
    // assertEquals(4, membersForParisAddress.size());
    // assertEquals(3, membersForNantesAddress.size());
    // assertTrue(propertyTreillieres.getMembers().contains(jules));
    assertEquals(1, MockitoMember.JULES.getProperties().size());
    assertEquals(1,
        propertyServiceImpl.getPropertiesForMember(MockitoMember.JULES).size());
    // assertEquals(3,
    // propertyServiceImpl.getPropertiesForMember(MockitoMember.BOB).size());
  }

}
