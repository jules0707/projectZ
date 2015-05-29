package house.my.model.enums.controller;

public enum PageEnum {

  SEARCH("search"), //
  ADD_PROPERTY_FORM("add_property"), //
  BASE_FORM("base_form"), //
  SAVE("save"), //
  HOME("home"), //
  TEST("test_jquery"), //
  CONTACT("contact"), //
  MY_PROPERTIES("my_properties"), //
  ERROR("500"); //

  private String jsp;

  private PageEnum(String jsp) {
    this.jsp = jsp;
  }

  public String jsp() {
    return jsp;
  }
}
