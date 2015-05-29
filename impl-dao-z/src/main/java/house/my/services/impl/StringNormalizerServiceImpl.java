package house.my.services.impl;

import java.text.Normalizer;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import house.my.services.StringNormalizerService;
import com.google.common.collect.ImmutableMap;

@Service
public class StringNormalizerServiceImpl implements StringNormalizerService {

  public boolean isValid(String txt, boolean withDigits) {
    boolean hasOnlyAuthorizedCharacters = false;
    if (StringUtils.isBlank(txt)) {
      return true;
    }
    if (withDigits) {
      hasOnlyAuthorizedCharacters = Pattern.matches("[\\p{Upper}\\p{Digit}\u0020]*", txt);
    } else if (!withDigits) {
      hasOnlyAuthorizedCharacters = Pattern.matches("[\\p{Upper}\u0020]*", txt);
    }

    return hasOnlyAuthorizedCharacters;
  }

  public String normalize(String str) {
    return normalize(str, false, false, false);
  }

  public String normalize(String str, boolean keepDigits, boolean keepPercent, boolean toLowerCase) {
    if (StringUtils.isBlank(str)) {
      return null;
    }
    if (!keepDigits) {
      str = str.replaceAll("[0-9]", "");
    }
    str = stripDiacritics(str);
    str = stripNonDiacritics(str, keepPercent);
    str = StringUtils.normalizeSpace(str);

    if (!toLowerCase) {
      str = StringUtils.upperCase(str);
    } else {
      str = StringUtils.lowerCase(str);
    }
    return str;
  }

  private static String stripNonDiacritics(String txt, boolean keepPercent) {
    if (StringUtils.isBlank(txt)) {
      return null;
    }
    // txt = txt.replaceAll("[" + authorizedPonctuation + "]", spaceCharacter);
    // return txt;
    StringBuffer ret = new StringBuffer();
    String lastchar = null;
    for (int i = 0; i < txt.length(); i++) {
      String source = txt.substring(i, i + 1);
      String replace = NONDIACRITICS.get(source);
      if ("%".equals(source) && keepPercent) {
        replace = null;
      }
      String toReplace = replace == null ? String.valueOf(source) : replace;
      if (DEFAULT_REPLACE.equals(lastchar) && DEFAULT_REPLACE.equals(toReplace)) {
        toReplace = "";
      } else {
        lastchar = toReplace;
      }
      ret.append(toReplace);
    }
    if (ret.length() > 0 && DEFAULT_REPLACE_CHAR == ret.charAt(ret.length() - 1)) {
      ret.deleteCharAt(ret.length() - 1);
    }
    return ret.toString();
  }

  /* ---------------------------- */

  /*
   * special regexp char ranges relevant for simplification -> see
   * http://docstore.mik.ua/orelly/perl/prog3/ch05_04.htm
   * InCombiningDiacriticalMarks: special marks that are part of "normal" ä, ö,
   * î etc.. IsSk: Symbol, Modifier see
   * http://www.fileformat.info/info/unicode/category/Sk/list.htm IsLm: Letter,
   * Modifier see http://www.fileformat.info/info/unicode/category/Lm/list.htm
   */
  private static final Pattern DIACRITICS_AND_FRIENDS = Pattern
      .compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

  private static String stripDiacritics(String str) {
    str = Normalizer.normalize(str, Normalizer.Form.NFD);
    str = DIACRITICS_AND_FRIENDS.matcher(str).replaceAll("");
    return str;
  }

  private static final char DEFAULT_REPLACE_CHAR = ' ';
  private static final String DEFAULT_REPLACE = String.valueOf(DEFAULT_REPLACE_CHAR);
  private static final ImmutableMap<String, String> NONDIACRITICS = ImmutableMap
      .<String, String> builder()
      // remove crap strings with no sematics
      .put("\"", "")
      // keep relevant characters as separation
      .put(".", DEFAULT_REPLACE)
      .put("-", DEFAULT_REPLACE)
      .put("'", DEFAULT_REPLACE)
      .put(" ", DEFAULT_REPLACE)
      .put("]", DEFAULT_REPLACE)
      .put("[", DEFAULT_REPLACE)
      .put(")", DEFAULT_REPLACE)
      .put("(", DEFAULT_REPLACE)
      .put("=", DEFAULT_REPLACE)
      .put("!", DEFAULT_REPLACE)
      .put("/", DEFAULT_REPLACE)
      .put("\\", DEFAULT_REPLACE)
      .put("&", DEFAULT_REPLACE)
      .put(",", DEFAULT_REPLACE)
      .put("?", DEFAULT_REPLACE)
      .put("°", DEFAULT_REPLACE)
      .put("$", DEFAULT_REPLACE)
      .put("|", DEFAULT_REPLACE)
      .put("<", DEFAULT_REPLACE)
      .put(">", DEFAULT_REPLACE)
      .put(";", DEFAULT_REPLACE)
      .put(":", DEFAULT_REPLACE)
      .put("_", DEFAULT_REPLACE)
      .put("#", DEFAULT_REPLACE)
      .put("~", DEFAULT_REPLACE)
      .put("+", DEFAULT_REPLACE)
      .put("*", DEFAULT_REPLACE)
      .put("{", DEFAULT_REPLACE)
      .put("@", DEFAULT_REPLACE)
      .put("}", DEFAULT_REPLACE)
      .put("£", DEFAULT_REPLACE)
      .put("%", DEFAULT_REPLACE)
      .put("§", DEFAULT_REPLACE)
      // replace non-diacritics as their equivalent chars
      .put("\u0141", "l")
      // BiaLystock
      .put("\u0142", "l")
      // Bialystock
      .put("ß", "ss")
      .put("æ", "ae")
      .put("ø", "o")
      .put("©", "c")
      .put("\u00D0", "d")
      // all Ð ð from http://de.wikipedia.org/wiki/%C3%90
      .put("\u00F0", "d")
      .put("\u0110", "d")
      .put("\u0111", "d")
      .put("\u0189", "d")
      .put("\u0256", "d")
      .put("\u00DE", "th")
      // thorn Þ
      .put("\u00FE", "th")
      // thorn þ
      .put("\u24B6", "A")
      .put("\uFF21", "A")
      .put("\u023A", "A")
      .put("\u2C6F", "A")
      //
      .put("\uA732", "AA")
      //
      .put("\u00C6", "AE")
      .put("\u01FC", "AE")
      .put("\u01E2", "AE")
      //
      .put("\uA734", "AO")
      //
      .put("\uA736", "AU")
      //
      .put("\uA738", "AV")
      .put("\uA73A", "AV")
      //
      .put("\uA73C", "AY")
      //
      .put("\u24B7", "B")
      .put("\uFF22", "B")
      .put("\u0243", "B")
      .put("\u0182", "B")
      .put("\u0181", "B")
      //
      .put("\u24B8", "C")
      .put("\uFF23", "C")
      .put("\u0187", "C")
      .put("\u023B", "C")
      .put("\uA73E", "C")
      //
      .put("\u24B9", "D")
      .put("\uFF24", "D")
      .put("\u018B", "D")
      .put("\u018A", "D")
      .put("\uA779", "D")
      //
      .put("\u01F1", "DZ")
      .put("\u01C4", "DZ")
      .put("\u01F2", "Dz")
      .put("\u01C5", "Dz")
      //
      .put("\u24BA", "E")
      .put("\uFF25", "E")
      .put("\u0190", "E")
      .put("\u018E", "E")
      //
      .put("\u24BB", "F")
      .put("\uFF26", "F")
      .put("\u0191", "F")
      .put("\uA77B", "F")
      //
      .put("\u24BC", "G")
      .put("\uFF27", "G")
      .put("\u01E4", "G")
      .put("\u0193", "G")
      .put("\uA7A0", "G")
      .put("\uA77D", "G")
      .put("\uA77E", "G")
      //
      .put("\u24BD", "H")
      .put("\uFF28", "H")
      .put("\u0126", "H")
      .put("\u2C67", "H")
      .put("\u2C75", "H")
      .put("\uA78D", "H")
      //
      .put("\u24BE", "I")
      .put("\uFF29", "I")
      .put("\u0197", "I")
      //
      .put("\u24BF", "J")
      .put("\uFF2A", "J")
      .put("\u0248", "J")
      //
      .put("\u24C0", "K")
      .put("\uFF2B", "K")
      .put("\u0198", "K")
      .put("\u2C69", "K")
      .put("\uA740", "K")
      .put("\uA742", "K")
      .put("\uA744", "K")
      .put("\uA7A2", "K")
      //
      .put("\u24C1", "L")
      .put("\uFF2C", "L")
      .put("\u013F", "L")
      .put("\u023D", "L")
      .put("\u2C62", "L")
      .put("\u2C60", "L")
      .put("\uA748", "L")
      .put("\uA746", "L")
      .put("\uA780", "L")
      //
      .put("\u01C7", "LJ")
      .put("\u01C8", "Lj")
      //
      .put("\u24C2", "M")
      .put("\uFF2D", "M")
      .put("\u2C6E", "M")
      .put("\u019C", "M")
      //
      .put("\u24C3", "N")
      .put("\uFF2E", "N")
      .put("\u0220", "N")
      .put("\u019D", "N")
      .put("\uA790", "N")
      .put("\uA7A4", "N")
      //
      .put("\u01CA", "NJ")
      .put("\u01CB", "Nj")
      //
      .put("\u24C4", "O")
      .put("\uFF2F", "O")
      .put("\u00D8", "O")
      .put("\u01FE", "O")
      .put("\u0186", "O")
      .put("\u019F", "O")
      .put("\uA74A", "O")
      .put("\uA74C", "O")
      //
      .put("\u01A2", "OI")
      //
      .put("\uA74E", "OO")
      //
      .put("\u0222", "OU")
      //
      .put("\u24C5", "P")
      .put("\uFF30", "P")
      .put("\u01A4", "P")
      .put("\u2C63", "P")
      .put("\uA750", "P")
      .put("\uA752", "P")
      .put("\uA754", "P")
      //
      .put("\u24C6", "Q")
      .put("\uFF31", "Q")
      .put("\uA756", "Q")
      .put("\uA758", "Q")
      .put("\u024A", "")
      //
      .put("\u24C7", "R")
      .put("\uFF32", "R")
      .put("\u024C", "R")
      .put("\u2C64", "R")
      .put("\uA75A", "R")
      .put("\uA7A6", "R")
      .put("\uA782", "R")
      //
      .put("\u24C8", "S")
      .put("\uFF33", "S")
      .put("\u1E9E", "S")
      .put("\u2C7E", "S")
      .put("\uA7A8", "S")
      .put("\uA784", "S")
      //
      .put("\u24C9", "T")
      .put("\uFF34", "T")
      .put("\u0166", "T")
      .put("\u01AC", "T")
      .put("\u01AE", "T")
      .put("\u023E", "T")
      .put("\uA786", "T")
      //
      .put("\uA728", "TZ")
      //
      .put("\u24CA", "U")
      .put("\uFF35", "U")
      .put("\u0244", "U")
      //
      .put("\u24CB", "V")
      .put("\uFF36", "V")
      .put("\u01B2", "V")
      .put("\uA75E", "V")
      .put("\u0245", "V")
      //
      .put("\uA760", "VY")
      //
      .put("\u24CC", "W")
      .put("\uFF37", "W")
      .put("\u2C72", "W")
      //
      .put("\u24CD", "X")
      .put("\uFF38", "X")
      //
      .put("\u24CE", "Y")
      .put("\uFF39", "Y")
      .put("\u01B3", "Y")
      .put("\u024E", "Y")
      .put("\u1EFE", "Y")
      //
      .put("\u24CF", "Z")
      .put("\uFF3A", "Z")
      .put("\u01B5", "Z")
      .put("\u0224", "Z")
      .put("\u2C7F", "Z")
      .put("\u2C6B", "Z")
      .put("\uA762", "Z")
      //
      .put("\u24D0", "a")
      .put("\uFF41", "a")
      .put("\u1E9A", "a")
      .put("\u2C65", "a")
      .put("\u0250", "a")
      //
      .put("\uA733", "aa")
      //
      .put("\uA735", "ao")
      //
      .put("\uA737", "au")
      //
      .put("\uA739", "av")
      .put("\uA73B", "av")
      //
      .put("\uA73D", "ay")
      //
      .put("\u24D1", "b")
      .put("\uFF42", "b")
      .put("\u0180", "b")
      .put("\u0183", "b")
      .put("\u0253", "b")
      //
      .put("\u24D2", "c")
      .put("\uFF43", "c")
      .put("\u0188", "c")
      .put("\u023C", "c")
      .put("\uA73F", "c")
      .put("\u2184", "c")
      //
      .put("\u24D3", "d")
      .put("\uFF44", "d")
      .put("\u018C", "d")
      .put("\u0257", "d")
      .put("\uA77A", "d")
      //
      .put("\u01F3", "dz")
      .put("\u01C6", "dz")
      //
      .put("\u24D4", "e")
      .put("\uFF45", "e")
      .put("\u0247", "e")
      .put("\u025B", "e")
      .put("\u01DD", "e")
      //
      .put("\u24D5", "f")
      .put("\uFF46", "f")
      .put("\u0192", "f")
      .put("\uA77C", "f")
      //
      .put("\u24D6", "g")
      .put("\uFF47", "g")
      .put("\u01E5", "g")
      .put("\u0260", "g")
      .put("\uA7A1", "g")
      .put("\u1D79", "g")
      .put("\uA77F", "g")
      //
      .put("\u24D7", "h")
      .put("\uFF48", "h")
      .put("\u0127", "h")
      .put("\u2C68", "h")
      .put("\u2C76", "h")
      .put("\u0265", "h")
      //
      .put("\u0195", "hv")
      //
      .put("\u24D8", "i")
      .put("\uFF49", "i")
      .put("\u0268", "i")
      //
      .put("\u24D9", "j")
      .put("\uFF4A", "j")
      .put("\u0249", "j")
      //
      .put("\u24DA", "k")
      .put("\uFF4B", "k")
      .put("\u0199", "k")
      .put("\u2C6A", "k")
      .put("\uA741", "k")
      .put("\uA743", "k")
      .put("\uA745", "k")
      .put("\uA7A3", "k")
      //
      .put("\u24DB", "l")
      .put("\uFF4C", "l")
      .put("\u0140", "l")
      .put("\u017F", "l")
      .put("\u019A", "l")
      .put("\u026B", "l")
      .put("\u2C61", "l")
      .put("\uA749", "l")
      .put("\uA781", "l")
      .put("\uA747", "l")
      //
      .put("\u01C9", "lj")
      //
      .put("\u24DC", "m")
      .put("\uFF4D", "m")
      .put("\u0271", "m")
      .put("\u026F", "m")
      //
      .put("\u24DD", "n")
      .put("\uFF4E", "n")
      .put("\u1E49", "n")
      .put("\u019E", "n")
      .put("\u0272", "n")
      .put("\u0149", "n")
      .put("\uA791", "n")
      .put("\uA7A5", "n")
      //
      .put("\u01CC", "nj")
      //
      .put("\u24DE", "o")
      .put("\uFF4F", "o")
      .put("\u0254", "o")
      .put("\uA74B", "o")
      .put("\uA74D", "o")
      .put("\u0275", "o")
      //
      .put("\u01A3", "oi")
      //
      .put("\u0223", "ou")
      //
      .put("\uA74F", "oo")
      //
      .put("\u24DF", "p").put("\uFF50", "p").put("\u01A5", "p")
      .put("\u1D7D", "p")
      .put("\uA751", "p")
      .put("\uA753", "p")
      .put("\uA755", "p")
      //
      .put("\u24E0", "q").put("\uFF51", "q")
      .put("\u024B", "q")
      .put("\uA757", "q")
      .put("\uA759", "q")
      //
      .put("\u24E1", "r").put("\uFF52", "r").put("\u024D", "r").put("\u027D", "r")
      .put("\uA75B", "r")
      .put("\uA7A7", "r")
      .put("\uA783", "r")
      //
      .put("\u24E2", "s").put("\uFF53", "s").put("\u023F", "s").put("\uA7A9", "s")
      .put("\uA785", "s")
      .put("\u1E9B", "s")
      //
      .put("\u24E3", "t").put("\uFF54", "t").put("\u0167", "t").put("\u01AD", "t").put("\u0288", "t")
      .put("\u2C66", "t").put("\uA787", "t")
      //
      .put("\uA729", "tz")
      //
      .put("\u24E4", "u").put("\uFF55", "u").put("\u0289", "u")
      //
      .put("\u24E5", "v").put("\uFF56", "v").put("\u028B", "v").put("\uA75F", "v").put("\u028C", "v")
      //
      .put("\uA761", "vy")
      //
      .put("\u24E6", "w").put("\uFF57", "w").put("\u2C73", "w")
      //
      .put("\u24E7", "x").put("\uFF58", "x")
      //
      .put("\u24E8", "y").put("\uFF59", "y").put("\u01B4", "y").put("\u024F", "y").put("\u1EFF", "y")
      //
      .put("\u24E9", "z").put("\uFF5A", "z").put("\u01B6", "z").put("\u0225", "z").put("\u0240", "z")
      .put("\u2C6C", "z").put("\uA763", "z") //

      .build();
}
