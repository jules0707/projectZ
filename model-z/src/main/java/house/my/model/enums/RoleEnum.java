package house.my.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {

  ROLE_USER, //
  ROLE_ADMIN; //

  public static Map<String, String> getAsHashMap() {
    Map<String, String> list = new HashMap<String, String>();
    for (RoleEnum role : RoleEnum.values()) {
      list.put(role.name(), role.name());
    }
    return list;
  }
}
