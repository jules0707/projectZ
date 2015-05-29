package house.my.support;

import lombok.AllArgsConstructor;
import lombok.Data;

import house.my.model.enums.EMWEnum;

@Data
@AllArgsConstructor
public class EMWLineArguments implements Marshallable {

  private EMWEnum error;

  private int line;

  private Object[] args;

  @Override
  public String toString() {
    return (-1 == line ? "" : "Ligne [" + line + "]: ") + String.format(error.text(), args);
  }
}