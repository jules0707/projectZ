package house.my.model.editors;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;

import house.my.support.ConstantsZ;

/*
 * Empêche la saise de dates bien formatées mais ambiguës. Exemple, le
 * 32/01/2010 est bien formaté, mais est convertie en un objet Date
 * correspondant au 01/02/2010.
 */
public class ZDateEditor extends CustomDateEditor {

  private final DateFormat dateFormat;

  public ZDateEditor(DateFormat dateFormat) {
    super(dateFormat, true);
    this.dateFormat = dateFormat;
  }

  public ZDateEditor() {
    super(ConstantsZ.SDF_yyyyMMdd, true);
    this.dateFormat = ConstantsZ.SDF_yyyyMMdd;
  }

  @Override
  public void setAsText(String text) {
    super.setAsText(text);
    if (StringUtils.hasText(text)) {
      if (!dateFormat.format(((Date) super.getValue())).equals(text)) {
        super.setValue(text);
        throw new IllegalArgumentException("Could not parse date: unknown date " + text);
      }
    }
  }
}
