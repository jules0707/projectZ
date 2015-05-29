package house.my.model;

import java.util.List;

import lombok.Data;

@Data
public class FormValue {

  public enum ValueType {
    TEXT, LIST
  }

  private String libelle;

  private String textValue;

  @SuppressWarnings("rawtypes")
  private List listValues;

  private ValueType type;

  private String selectedValue;

  public FormValue() {
    super();
  }

  public FormValue(String textValue) {
    super();
    this.textValue = textValue;
    this.type = ValueType.TEXT;
  }

  public FormValue(@SuppressWarnings("rawtypes") List list) {
    super();
    this.listValues = list;
    this.type = ValueType.LIST;
  }
}
