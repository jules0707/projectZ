package house.my.model.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import house.my.support.ConstantsZ;

public enum LanguageEnum {
  fr, en, ja, es, de, it; //

  public static Map<String, String> getAsHashMap() {
    Map<String, String> list = new HashMap<String, String>();
    for (LanguageEnum language : LanguageEnum.values()) {
      list.put(language.name(), language.name());
    }
    return list;
  }

  public static Locale getLocaleFrom(String language) {
    Locale l = null;
    if (LanguageEnum.fr.name().equalsIgnoreCase(language)) {
      l = ConstantsZ.LOCALE_FRENCH;
    } else if (LanguageEnum.en.name().equalsIgnoreCase(language)) {
      l = ConstantsZ.LOCALE_ENGLISH;
    } else if (LanguageEnum.ja.name().equalsIgnoreCase(language)) {
      l = ConstantsZ.LOCALE_JAPANESE;
    } else if (LanguageEnum.es.name().equalsIgnoreCase(language)) {
      l = ConstantsZ.LOCALE_SPANISH;
    } else if (LanguageEnum.de.name().equalsIgnoreCase(language)) {
      l = ConstantsZ.LOCALE_GERMAN;
    } else if (LanguageEnum.it.name().equalsIgnoreCase(language)) {
      l = ConstantsZ.LOCALE_ITALIAN;
    }
    return l;
  }
}
