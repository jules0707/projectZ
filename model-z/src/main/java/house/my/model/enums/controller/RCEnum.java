package house.my.model.enums.controller;

public enum RCEnum {

  ENGINE_LABEL_LOCALITY("engine.label.locality"),

  ERREUR_FORMAT_DATE_CREATIONFROM("erreur.format.dateCreationfrom") //
  ;

  private String rc;

  RCEnum(String rc) {
    this.rc = rc;
  }

  public String rc() {
    return rc;
  }
}
