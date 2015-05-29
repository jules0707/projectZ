package house.my.testutils;

import house.my.model.Address;
import house.my.model.Member;
import house.my.model.Property;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class PropertyBuilder extends BaseBuilder {

  private Property property;
  static final Date NOW = MockitoTest.DATE_NOW;

  public PropertyBuilder(Property property) {
    this.property = property;
  }

  public PropertyBuilder withAddress() {
    property.setAddress(new Address(getFakeAddressString()));
    return this;
  }

  public PropertyBuilder withMembersList(List<Member> members) {
    property.setMembers(members);
    return this;
  }

  public PropertyBuilder withPrice() {
    property.setPrice(new BigDecimal(Math.random() * 100000));
    return this;
  }

  private static List<Member> getMembers() {
    List<Member> members = new ArrayList<Member>();
    int loopSize = (int) Math.random() * 10;
    for (int i = 0; i < loopSize; i++) {
      members.add(new Member(getFakeName()));
    }
    return members;
  }

}
