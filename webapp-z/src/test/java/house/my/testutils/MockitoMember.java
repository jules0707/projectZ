package house.my.testutils;

import house.my.model.Member;
import house.my.model.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("serial")
public class MockitoMember {

  public static final Member ADMIN = new Member();
  public static final Member BOB = new Member();
  public static final Member max = new Member();
  public static final Member paul = new Member();
  public static final Member teo = new Member();
  public static final Member JULES = new Member();
  public static final Member mike = new Member();
  public static final Member NULL = null;

  public static final List<Member> LIST_MEMBER_01 = new ArrayList<Member>();
  public static final List<Member> LIST_MEMBER_02 = new ArrayList<Member>();

  static {
    ADMIN.setCreationDate(new Date());
    ADMIN.setIdMember(01);
    ADMIN.setLogin("admin01");
    ADMIN.setName("admin");
    ADMIN.setBlocked(false);
    ADMIN.setLocalePreference(Locale.FRANCE);
    ADMIN.setPswd("0101");
    ADMIN.setRoles(new ArrayList<Role>() {
      {
        this.add(0, Role.ROLE_ADMIN);
      }
    }
        );

  }

  static {
    JULES.setCreationDate(new Date());
    JULES.setIdMember(635);
    JULES.setLogin("jules07");
    JULES.setName("jules");
    JULES.setProperties(MockitoProperty.PROPERTY_LIST_01);
    JULES.setBlocked(false);
    JULES.setLocalePreference(Locale.FRANCE);
    JULES.setPswd("0707");
    JULES.setRoles(new ArrayList<Role>() {
      {
        this.add(0, Role.ROLE_USER);
      }
    }
        );

    BOB.setCreationDate(new Date());
    BOB.setIdMember(35);
    BOB.setLogin("bob01");
    BOB.setName("bob");
    BOB.setProperties(MockitoProperty.PROPERTY_LIST_02);
    BOB.setBlocked(false);
    BOB.setLocalePreference(Locale.FRANCE);
    BOB.setPswd("0101");
    BOB.setRoles(new ArrayList<Role>() {
      {
        this.add(0, Role.ROLE_USER);
      }
    }
        );

  }

  static {
    LIST_MEMBER_02.add(MockitoMember.BOB);
  };

  static {
    LIST_MEMBER_01.add(MockitoMember.JULES);
    LIST_MEMBER_01.add(MockitoMember.BOB);

  }

}
