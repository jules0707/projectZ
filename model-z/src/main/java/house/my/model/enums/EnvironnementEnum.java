package house.my.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnvironnementEnum {

  prod, stage, dev;

  public static Map<String, String> getAsHashMap() {
    Map<String, String> list = new HashMap<String, String>();
    for (EnvironnementEnum environnement : EnvironnementEnum.values()) {
      list.put(environnement.name(), environnement.name());
    }
    return list;
  }
}
