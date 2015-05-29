package house.my.model.enums.controller;

public enum FieldEnum {

  ADRESSE_PAYS("adresse.pays"), //
  ADRESSE_VILLE("adresse.ville"), //
  ADRESSE_CODE_POSTAL("adresse.codePostal"), //
  ADRESSE_VOIE("adresse.voie"), //

  PRIX("prix"), //
  DATE_CREATION_FROM("dateCreationFrom"), //
  PRENOM("prenom"), //
  IDENTIFIANT("identifiant"), //
  MOT_DE_PASSE("mdp"), //
  NOM("nom"), //
  CIVILITE("civilite"), //
  EMAIL("email");

  private String field;

  FieldEnum(String field) {
    this.field = field;
  }

  public String field() {
    return field;
  }
}
