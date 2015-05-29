package house.my.testutils;

import house.my.model.Property;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MockitoProperty {

  public static final Property PROPERTY_PARIS_01 = new Property();
  public static final Property PROPERTY_PARIS_02 = new Property();
  public static final Property PROPERTY_PARIS_03 = new Property();
  public static final Property PROPERTY_NANTES_04 = new Property();
  public static final Property PROPERTY_NANTES_05 = new Property();
  public static final Property PROPERTY_NANTES_06 = new Property();
  public static final Property PROPERTY_NULL = null;

  public static final List<Property> PROPERTY_LIST_01 = new ArrayList<Property>();
  public static final List<Property> PROPERTY_LIST_02 = new ArrayList<Property>();
  public static final List<Property> PROPERTY_LIST_03 = new ArrayList<Property>();
  public static final List<Property> PROPERTY_LIST_04 = new ArrayList<Property>();

  static {
    PROPERTY_PARIS_01.setAddress(MockitoAddress.ADDRESS_PARIS_75002);
    PROPERTY_PARIS_02.setAddress(MockitoAddress.ADDRESS_PARIS_75003);
    PROPERTY_PARIS_03.setAddress(MockitoAddress.ADDRESS_PARIS_75015);
    PROPERTY_NANTES_04.setAddress(MockitoAddress.ADDRESS_NANTES_44001);
    PROPERTY_NANTES_05.setAddress(MockitoAddress.ADDRESS_NANTES_44002);
    PROPERTY_NANTES_06.setAddress(MockitoAddress.ADDRESS_NANTES_44003);

    PROPERTY_PARIS_01.setPrice(new BigDecimal(2140404));
    PROPERTY_PARIS_02.setPrice(new BigDecimal(65470404));
    PROPERTY_PARIS_01.setPrice(new BigDecimal(24560404));

    PROPERTY_NANTES_04.setPrice(new BigDecimal(10404));
    PROPERTY_NANTES_05.setPrice(new BigDecimal(30404));
    PROPERTY_NANTES_06.setPrice(new BigDecimal(640404));

    PROPERTY_LIST_01.add(PROPERTY_PARIS_01);
    PROPERTY_LIST_02.addAll(new ArrayList<Property>() {

      {
        add(PROPERTY_PARIS_01);
        add(PROPERTY_PARIS_02);
        add(PROPERTY_PARIS_03);
      }
    });

    PROPERTY_PARIS_01.setMembers(MockitoMember.LIST_MEMBER_01);
    PROPERTY_PARIS_02.setMembers(MockitoMember.LIST_MEMBER_02);
  }
}
