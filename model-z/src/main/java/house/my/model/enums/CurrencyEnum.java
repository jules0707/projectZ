package house.my.model.enums;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public enum CurrencyEnum {

  EUR("978", "&euro;", "€", "#\u0020##0.00 [$\u20ac-40C];-#\u0020##0.00 [$\u20ac-40C]"), //
  USD("840", "$", "$", "[$$-409]# ##0.00;[RED]-[$$-409]# ##0.00"), //
  CHF("756", "CHF", "CHF", "[$sFr.-100C] # ##0,00;[RED][$sFr.-100C] -# ##0,00"), //
  GBP("826", "&pound;", "£", "[$£-809]# ##0,00;[RED]-[$£-809]# ##0,00"); //

  private String codeIso;
  private String html;
  private String symbol;
  private String xlsFormat;

  private CurrencyEnum(String codeIso, String html, String symbol, String xlsFormat) {
    this.codeIso = codeIso;
    this.html = html;
    this.symbol = symbol;
    this.xlsFormat = xlsFormat;
  }

  public String codeIso() {
    return codeIso;
  }

  public Currency currency() {
    return Currency.getInstance(this.name());
  }

  // get obligatoire pour freemarker
  public String getHtml() {
    return html;
  }

  public String getXlsFormat() {
    return xlsFormat;
  }

  public String getSymbol() {
    return symbol;
  }

  public static CurrencyEnum getFromCodeIso(String code) {
    for (CurrencyEnum devise : CurrencyEnum.values()) {
      if (devise.codeIso().equals(code)) {
        return devise;
      }
    }
    return null;
  }
  
  public static CurrencyEnum getFromName(String name) {
    for (CurrencyEnum devise : CurrencyEnum.values()) {
      if (devise.name().equals(name.toUpperCase())) {
        return devise;
      }
    }
    return null;
  }

  public static Map<String, String> getAsHashMap() {
    Map<String, String> list = new HashMap<String, String>();
    for (CurrencyEnum devise : CurrencyEnum.values()) {
      list.put(devise.name(), devise.name());
    }
    return list;
  }
}
