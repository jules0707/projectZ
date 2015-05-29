package house.my.model.enums;

public enum ServiceEnum {

  ANTISLASH("\\"), //
  APOS("'"), //
  CLOSE_CURLY_BRACKET("}"), //
  CLOSE_PARENTHESIS(")"), //
  CLOSE_SQUARE_BRACKET("]"), //
  COLON(":"), //
  SEMICOLON(";"), //
  COMMA(","), //
  CR("\r"), //
  CR_EOL("\r\n"), //
  DASH("-"), //
  DOT("."), //
  EMPTY(""), //
  EOL("\n"), //
  EQUAL("="), //
  AMPERSAND("&"), //
  EXTENSION_CONFLUENCE(".confluence"), //
  EXTENSION_DBF(".Dbf"), //
  EXTENSION_DITAA(".ditaa"), //
  EXTENSION_DOT(".dot"), //
  EXTENSION_FTL(".ftl"), //
  EXTENSION_HTML(".html"), //
  EXTENSION_JRXML(".jrxml"), //
  EXTENSION_MM(".mm"), //
  EXTENSION_MM_TXT(".mm.txt"), //
  EXTENSION_PDF(".pdf"), //
  EXTENSION_PNG(".png"), //
  EXTENSION_PNM(".pnm"), //
  EXTENSION_SVG(".svg"), //
  EXTENSION_TMP(".tmp"), //
  EXTENSION_TXT(".txt"), //
  EXTENSION_XLS(".xls"), //
  EXTENSION_ZIP(".zip"), //
  FORMAT_CONFLUENCE("confluence"), //
  FORMAT_XHTML("xhtml"), //
  GT(">"), //
  HASH("#"), //
  LT("<"), //
  OPEN_CURLY_BRACKET("{"), //
  OPEN_PARENTHESIS("("), //
  OPEN_SQUARE_BRACKET("["), //
  PIPE("|"), //
  PLUS("+"), //
  PSTRING("%s"), //
  QUESTION_MARK("?"), //
  SLASH("/"), //
  SPACE(" "), //
  SPACE_NBSP(" "), //
  STAR("*"), //
  TAB("\t"), //
  AT("@"), //
  UNDERSCORE("_");//

  private String value;

  private ServiceEnum(final String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

  public static ServiceEnum fromValue(String value) {
    ServiceEnum serviceEnum = null;
    for (ServiceEnum se : ServiceEnum.values()) {
      if (se.value().equals(value)) {
        serviceEnum = se;
        break;
      }
    }
    return serviceEnum;
  }
}
