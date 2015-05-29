package house.my.model.enums.controller;

public enum UrlEnum {

  SEARCH("search"), //
  ADD_PROPERTY_FORM("add_property"), //
  BASE_FORM("base_form"), //
  SAVE("save"), //
  HOME("home"), //
  CONTACT("contact"), //
  MYPROPERTIES("/myProperties"), //

  ADD("add"), //
  VALIDATION("validation"), //

  REDIRECT("redirect:"), //
  DOT_DOT("../"), //

  LOGIN("login"), //
  LOGOUT("logout"), //
  SIGNIN("signin"), //

  INIT("init"), //
  CONFIRM("confirm"), //

  INDEX("index"); //

  private String url;

  UrlEnum(String url) {
    this.url = url;
  }

  public String url() {
    return url;
  }
}
