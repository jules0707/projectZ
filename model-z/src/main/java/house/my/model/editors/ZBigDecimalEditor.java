package house.my.model.editors;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.util.NumberUtils;

import house.my.support.ConstantsZ;

/*
 * Permet de convertir les décimaux saisis avec un séparateur "virgule" (Locale FRENCH) ou "point" (Locale ENGLISH)
 */
public class ZBigDecimalEditor extends CustomNumberEditor {

  private final String PATTERN = "0.##";
  private NumberFormat numberFormat;

  public ZBigDecimalEditor() {
    super(BigDecimal.class, true);
  }

  @Override
  public void setAsText(String text) {
    if (StringUtils.isBlank(text)) {
      // Treat empty String as null value.
      setValue(null);
    } else {
      NumberFormat nf;
      if (text.matches("-?\\d+[,\\.]?\\d*")) {
        DecimalFormatSymbols englishDFS = DecimalFormatSymbols.getInstance(ConstantsZ.LOCALE_ENGLISH);
        if (StringUtils.contains(text, englishDFS.getDecimalSeparator())) {
          nf = new DecimalFormat(PATTERN, englishDFS);
        } else {
          DecimalFormatSymbols frenchDFS = DecimalFormatSymbols.getInstance(ConstantsZ.LOCALE_FRENCH);
          nf = new DecimalFormat(PATTERN, frenchDFS);
        }
        this.numberFormat = nf;
        setValue(NumberUtils.parseNumber(text, BigDecimal.class, nf));
      } else {
        throw new IllegalArgumentException();
      }
    }
  }

  @Override
  public String getAsText() {
    Object value = getValue();
    if (value == null) {
      return "";
    }
    if (this.numberFormat != null) {
      return this.numberFormat.format(value);
    }
    return value.toString();
  }
}
