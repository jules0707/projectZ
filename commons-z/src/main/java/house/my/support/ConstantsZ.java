package house.my.support;

import house.my.model.enums.LanguageEnum;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTime;

public final class ConstantsZ {

  public static final String USR_LOCAL_BIN = "/usr/local/bin/";

  public static final String MESSAGES = "messages";
  public static final String ERRORS = "errors";
  public static final String HELPS = "helps";
  public static final String WARNINGS = "warnings";

  public static final String REGEX_LETTER_2_TO_255 = "^\\p{L}[\\p{L}'][ \\p{L}-']{0,255}$";
  private static final String REGEX_START = "^\\p{Alnum}[ \\p{L}\\-'\\-\\d]{1,";
  public static final String REGEX_ADRESSE = REGEX_START + "512}$";
  public static final String REGEX_VILLE = REGEX_START + "255}$";
  public static final String REGEX_DATE = "[\\d]{8}";

  public static final Locale LOCALE_FRENCH = Locale.FRENCH;
  public static final Locale LOCALE_ENGLISH = Locale.ENGLISH;
  public static final Locale LOCALE_SPANISH = new Locale(LanguageEnum.es.name());
  public static final Locale LOCALE_ITALIAN = Locale.ITALIAN;
  public static final Locale LOCALE_GERMAN = Locale.GERMAN;
  public static final Locale LOCALE_JAPANESE = Locale.JAPANESE;

  // public static final Locale LOCALE_ARABIC = new Locale("ar");
  public static final String LIBELLE_DOT = "libelle.";

  public static final String DATE_NAISSANCE_NULLE = "01/01/1970";

  public static final int NB_ELEMENTS_PER_PAGE = 50;

  public static final String PATTERN_PRIX = "0.##";

  // CHECKSTYLE:OFF
  public static final String PATTERN_HSSF_m_d_yy_h_mm = "m/d/yy h:mm";
  public static final String PATTERN_HSSF_m_d_yy = "m/d/yy";
  public static final String PATTERN_dd_MM_yyyy_SLASH = "dd/MM/yyyy";
  public static final String PATTERN_MM_dd_yyyy_SLASH = "MM/dd/yyyy";
  public static final String PATTERN_dd_MMM_yyyy_SPACE = "dd MMM yyyy";
  private static final String PATTERN_MMMMM_yyyy_SPACE = "MMMMM yyyy";
  private static final String PATTERN_yyMMdd = "yyMMdd";
  private static final String PATTERN_MMMMM = "MMMMM";
  private static final String PATTERN_MMMMM_dd_yyyy_SPACE = "MMMMM dd, yyyy";
  private static final String PATTERN_dd_MMMMM_yyyy_SPACE = "dd MMMMM yyyy";
  private static final String PATTERN_yyyyMMdd = "yyyyMMdd";
  private static final String PATTERN_dd_MM_yy_UNDERSCORE = "dd_MM_yy";
  private static final String PATTERN_dd_MM_yy_DOT = "dd.MM.yy";
  private static final String PATTERN_MM_yyyy_DOT = "MM.yyyy";
  private static final String PATTERN_yyyy_MM_dd_DASH = "yyyy-MM-dd";
  private static final String PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";
  private static final String PATTERN_dd_MM_yyyy_DASH = "dd-MM-yyyy";
  private static final String PATTERN_ddMMyy = "ddMMyy";
  private static final String PATTERN_dd_MM_yy_SLASH = "dd/MM/yy";
  public static final String PATTERN_MMyyyy = "MMyyyy";
  public static final String PATTERN_yyyyMM = "yyyyMM";

  public static final SimpleDateFormat SDF_dd_MM_yyyy_SLASH = new SimpleDateFormat(PATTERN_dd_MM_yyyy_SLASH);
  public static final SimpleDateFormat SDF_MM_dd_yyyy_SLASH = new SimpleDateFormat(PATTERN_MM_dd_yyyy_SLASH);

  public static final SimpleDateFormat SDF_yyyy_MM_dd_DASH = new SimpleDateFormat(PATTERN_yyyy_MM_dd_DASH);
  public static final SimpleDateFormat SDF_dd_MM_yy_UNDERSCORE = new SimpleDateFormat(PATTERN_dd_MM_yy_UNDERSCORE);
  public static final SimpleDateFormat SDF_yyMMdd = new SimpleDateFormat(PATTERN_yyMMdd);
  public static final SimpleDateFormat SDF_yyyyMMdd = new SimpleDateFormat(PATTERN_yyyyMMdd);
  public static final SimpleDateFormat SDF_MMMMM_FRENCH = new SimpleDateFormat(PATTERN_MMMMM, Locale.FRENCH);
  public static final SimpleDateFormat SDF_MMMMM_ENGLISH = new SimpleDateFormat(PATTERN_MMMMM, Locale.ENGLISH);
  public static final SimpleDateFormat SDF_dd_MMMMM_yyyy_SPACE_FRENCH = //
  new SimpleDateFormat(PATTERN_dd_MMMMM_yyyy_SPACE, Locale.FRENCH);
  public static final SimpleDateFormat SDF_MMMMM_dd_yyyy_SPACE_ENGLISH = //
  new SimpleDateFormat(PATTERN_MMMMM_dd_yyyy_SPACE, Locale.ENGLISH);
  public static final SimpleDateFormat SDF_MMyyyy = new SimpleDateFormat(PATTERN_MMyyyy);
  public static final SimpleDateFormat SDF_yyyyMM = new SimpleDateFormat(PATTERN_yyyyMM);

  public static final SimpleDateFormat SDF_MMMMM_yyyy_SPACE_FRENCH = new SimpleDateFormat(PATTERN_MMMMM_yyyy_SPACE,
      Locale.FRENCH);
  public static final SimpleDateFormat SDF_MMMMM_yyyy_SPACE_ENGLISH = new SimpleDateFormat(PATTERN_MMMMM_yyyy_SPACE,
      Locale.ENGLISH);
  public static final SimpleDateFormat SDF_dd_MM_yy_SLASH = new SimpleDateFormat(PATTERN_dd_MM_yy_SLASH);
  public static final SimpleDateFormat SDF_ddMMyy = new SimpleDateFormat(PATTERN_ddMMyy);
  public static final SimpleDateFormat SDF_dd_MM_yy_DOT = new SimpleDateFormat(PATTERN_dd_MM_yy_DOT);
  public static final SimpleDateFormat SDF_MM_yyyy_DOT = new SimpleDateFormat(PATTERN_MM_yyyy_DOT);
  public static final SimpleDateFormat SDF_dd_MM_yyyy_DASH = new SimpleDateFormat(PATTERN_dd_MM_yyyy_DASH);
  public static final SimpleDateFormat SDF_yyyyMMddHHmmss = new SimpleDateFormat(PATTERN_yyyyMMddHHmmss);
  public static final SimpleDateFormat SDF_yyyyMMddHHmmss_UTC = new SimpleDateFormat(PATTERN_yyyyMMddHHmmss);
  // CHECKSTYLE:ON

  public static final DecimalFormat DF_MONTANT = new DecimalFormat("#.##");
  public static final DecimalFormat DF_MONTANT_SANS_DECIMALES = new DecimalFormat("#");
  public static final DecimalFormat DF_MONTANT_MILLIERS_SEPARES = new DecimalFormat("##,###.##");
  public static final Date DATE_REVEILLON = new DateTime().withMonthOfYear(12).withDayOfMonth(31).toDate();

  public static final String CHARSET_UTF_8 = "UTF-8";
  public static final String CHARSET_CP1252 = "CP1252";

  public static final String CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset="
      + CHARSET_UTF_8.toLowerCase();
  public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";
  public static final String CONTENT_TYPE_TEXT_HTML = "text/html";
  public static final String CONTENT_TYPE_APPLICATION_PDF = "application/pdf";
  public static final String CONTENT_TYPE_APPLICATION_ZIP = "application/zip";
  public static final String CONTENT_TYPE_APPLICATION_EXCEL = "application/vnd.ms-excel";

  public static final String NO_ACCENTS = "AAAAAACEEEEIIIINOOOOOUUUUYaaaaaaceeeeiiiinooooouuuuyy";
  public static final String ALL_ACCENTS = "ÁÀÂÄÃÅÇÉÈÊËÍÏÎÌÑÓÒÔÖÕÚÙÛÜÝáàâäãåçéèêëíìîïñóòôöõúùûüýÿ";

  public static final BigDecimal CENT = new BigDecimal("100");

  static {
    DF_MONTANT.setMinimumFractionDigits(2);
    DecimalFormatSymbols format = new DecimalFormatSymbols();
    format.setDecimalSeparator('.');
    DF_MONTANT.setDecimalFormatSymbols(format);

    DF_MONTANT_MILLIERS_SEPARES.setMinimumFractionDigits(2);
    DecimalFormatSymbols formatSpace = new DecimalFormatSymbols();
    formatSpace.setDecimalSeparator('.');
    formatSpace.setGroupingSeparator(' ');
    DF_MONTANT_MILLIERS_SEPARES.setDecimalFormatSymbols(formatSpace);

    SDF_yyyyMMddHHmmss_UTC.setTimeZone(TimeZone.getTimeZone("UTC"));
  }
}
