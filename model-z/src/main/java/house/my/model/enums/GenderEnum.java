package house.my.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum GenderEnum {

  MISTER(0), MISS(1), MS(2);

  private Integer code;

  private GenderEnum(int code) {
    this.code = code;
  }

  public Integer code() {
    return code;
  }

  public static Map<String, String> getAsHashMap() {
    Map<String, String> list = new HashMap<String, String>();
    for (GenderEnum gender : GenderEnum.values()) {
      list.put(gender.name(), gender.name());
    }
    return list;
  }
}
