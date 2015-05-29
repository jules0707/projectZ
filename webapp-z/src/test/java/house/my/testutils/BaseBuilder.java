package house.my.testutils;

import house.my.model.enums.GenderEnum;

public class BaseBuilder {

  private static final String[] NAME = { "Aminda", "Brava", "Petro", "Orabela", "Miĉjo", "Fiera", "Esperanta",
      "Rava", "Dezirinda", "Brava", "Adorinda", "Amika", "Ludoviko", "Juvela", "Orabela", "Fajra", "Amika", "Rubena",
      "Adorinda", "Fajra", "Tondra" };

  private static final String[] LASTNAME = { "Karesinda", "Stelara", "Pipra", "Mackenzie", "Chester", "Errol" };
  private static final GenderEnum[] GENDER = new GenderEnum[] { GenderEnum.MISS, GenderEnum.MS,
      GenderEnum.MISTER };

  private static final String[] ADDRESS = { "paris", "byron", "otawa", "sydney", "manchester", "bejin", "rome",
      "treillieres", "tel-aviv", "marseille", "nice" };

  static String getFakeLastname() {
    return LASTNAME[(int) (Math.random() * LASTNAME.length)];
  }

  static String getFakeName() {
    return NAME[(int) (Math.random() * NAME.length)];
  }

  static String getFakeLogin() {
    return NAME[(int) (Math.random() * NAME.length)] + (int) Math.random() * 10;
  }

  static GenderEnum getFakeGender() {
    return GENDER[(int) (Math.random() * GENDER.length)];
  }

  static String getFakeAddressString() {
    return ADDRESS[(int) (Math.random() * ADDRESS.length)];
  }

}
