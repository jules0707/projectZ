package house.my.testutils;

import house.my.model.Address;

public class MockitoAddress {

  public static final Address ADDRESS_PARIS_75015 = new Address();
  public static final Address ADDRESS_PARIS_75002 = new Address();
  public static final Address ADDRESS_PARIS_75003 = new Address();

  public static final Address ADDRESS_NANTES_44001 = new Address();
  public static final Address ADDRESS_NANTES_44002 = new Address();
  public static final Address ADDRESS_NANTES_44003 = new Address();

  static {
    ADDRESS_PARIS_75015.setLocality("75015");
    ADDRESS_PARIS_75002.setLocality("75002");
    ADDRESS_PARIS_75003.setLocality("75003");

    ADDRESS_NANTES_44001.setLocality("44001");
    ADDRESS_NANTES_44002.setLocality("44002");
    ADDRESS_NANTES_44003.setLocality("44003");

  }

}
