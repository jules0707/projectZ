package house.my.model.enums;

public enum ModelAttributeEnum {

  LOCALE("locale"), //
  MEMBER("member"), //
  HAS_RIGHT_TO_REMOVE_PROPERTY("has_right_to_remove_property"), //
  // HOME("home"), //

  REQUEST_PERSISTENT_ERROR_MESSAGE("requestPersistentErrorMessage"), //
  REQUEST_PERSISTENT_INFO_MESSAGE("requestPersistentInfoMessage"), //
  REQUEST_PERSISTENT_WARNING_MESSAGE("requestPersistentWarningMessage"), //
  MESSAGE("message"), //
  // MESSAGES("messages"), //
  // WARNINGS("warnings"), //
  // ERRORS("errors"), //
  // HELPS("helps"), //
  RESULT("result"), //
  CONTENT_TYPE("Content-Type"), //

  ACTION("action"), //
  DESTINATION("DESTINATION"), //
  EMAIL_FORM("emailForm"), //
  FORM("form"), //
  FORM_VALUES("formValues"), //
  LISTE("liste"), //
  PARTIAL_LIST("partialList"), //
  PAGESIZE("pagesize"), //
  REQUEST_URL("requestURL"), //
  SIZE("size"), //
  PRICE("price"), //
  PROPERTY("property"), //
  MY_PROPERTIES_LIST("myPropertiesList"), //

  STEP("step"), //
  ROLES("roles"), //

  FORM_DETAILS("formDetails"), //
  EMAIL("email"), //
  PRIX("prix"), //
  ERREUR("erreur"), //
  CHAINE_PARAM("chaineParam"), //
  INFO("info"), //
  HMAC("hmac"), //
  HMAC_CALCULE("hmacCalcule") //
  ;//

  private String attribute;

  ModelAttributeEnum(String attribute) {
    this.attribute = attribute;
  }

  public String attribute() {
    return attribute;
  }
}
