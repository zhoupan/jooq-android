/* 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: http://www.jooq.org/licenses
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.jooq.util.jaxb.tools;

import java.math.BigDecimal;
import java.math.BigInteger;

/** The Class JaxbUtils. */
public class JaxbUtils {

  /**
   * Prints the base 64 binary.
   *
   * @param val the val
   * @return the string
   */
  public static String printBase64Binary(byte[] val) {
    return _printBase64Binary(val);
  }

  /**
   * Parses the base 64 binary.
   *
   * @param lexicalXSDBase64Binary the lexical XSD base 64 binary
   * @return the byte[]
   */
  public static byte[] parseBase64Binary(String lexicalXSDBase64Binary) {
    return _parseBase64Binary(lexicalXSDBase64Binary);
  }

  /**
   * Prints the unsigned short.
   *
   * @param val the val
   * @return the string
   */
  public static String printUnsignedShort(int val) {
    return String.valueOf(val);
  }

  /**
   * Prints the any simple type.
   *
   * @param val the val
   * @return the string
   */
  public static String printAnySimpleType(String val) {
    return val;
  }

  /** The Constant decodeMap. */
  // base64 decoder
  private static final byte[] decodeMap = initDecodeMap();

  /** The Constant PADDING. */
  private static final byte PADDING = 127;

  /**
   * Inits the decode map.
   *
   * @return the byte[]
   */
  private static byte[] initDecodeMap() {
    byte[] map = new byte[128];
    int i;
    for (i = 0; i < 128; i++) {
      map[i] = -1;
    }
    for (i = 'A'; i <= 'Z'; i++) {
      map[i] = (byte) (i - 'A');
    }
    for (i = 'a'; i <= 'z'; i++) {
      map[i] = (byte) ((i - 'a') + 26);
    }
    for (i = '0'; i <= '9'; i++) {
      map[i] = (byte) ((i - '0') + 52);
    }
    map['+'] = 62;
    map['/'] = 63;
    map['='] = PADDING;
    return map;
  }

  /**
   * computes the length of binary data speculatively.
   *
   * <p>Our requirement is to create byte[] of the exact length to store the binary data. If we do
   * this in a straight-forward way, it takes two passes over the data. Experiments show that this
   * is a non-trivial overhead (35% or so is spent on the first pass in calculating the length.)
   *
   * <p>So the approach here is that we compute the length speculatively, without looking at the
   * whole contents. The obtained speculative value is never less than the actual length of the
   * binary data, but it may be bigger. So if the speculation goes wrong, we'll pay the cost of
   * reallocation and buffer copying.
   *
   * <p>If the base64 text is tightly packed with no indentation nor illegal char (like what most
   * web services produce), then the speculation of this method will be correct, so we get the
   * performance benefit.
   *
   * @param text the text
   * @return the int
   */
  private static int guessLength(String text) {
    final int len = text.length();
    // compute the tail '=' chars
    int j = len - 1;
    for (; j >= 0; j--) {
      byte code = decodeMap[text.charAt(j)];
      if (code == PADDING) {
        continue;
      }
      if ( // most likely this base64 text is indented. go with the upper bound
      code == -1) {
        return (text.length() / 4) * 3;
      }
      break;
    }
    // text.charAt(j) is now at some base64 char, so +1 to make it the size
    j++;
    int padSize = len - j;
    if ( // something is wrong with base64. be safe and go with the upper bound
    padSize > 2) {
      return (text.length() / 4) * 3;
    }
    // so far this base64 looks like it's unindented tightly packed base64.
    // take a chance and create an array with the expected size
    return ((text.length() / 4) * 3) - padSize;
  }

  /**
   * Parses the base 64 binary.
   *
   * @param text base64Binary data is likely to be long, and decoding requires each character to be
   *     accessed twice (once for counting length, another for decoding.)
   *     <p>A benchmark showed that taking {@link String} is faster, presumably because JIT can
   *     inline a lot of string access (with data of 1K chars, it was twice as fast)
   * @return the byte[]
   */
  public static byte[] _parseBase64Binary(String text) {
    final int buflen = guessLength(text);
    final byte[] out = new byte[buflen];
    int o = 0;
    final int len = text.length();
    int i;
    final byte[] quadruplet = new byte[4];
    int q = 0;
    // convert each quadruplet to three bytes.
    for (i = 0; i < len; i++) {
      char ch = text.charAt(i);
      byte v = decodeMap[ch];
      if (v != -1) {
        quadruplet[q] = v;
        q++;
      }
      if (q == 4) {
        // quadruplet is now filled.
        out[o] = (byte) ((quadruplet[0] << 2) | (quadruplet[1] >> 4));
        o++;
        if (quadruplet[2] != PADDING) {
          out[o] = (byte) ((quadruplet[1] << 4) | (quadruplet[2] >> 2));
          o++;
        }
        if (quadruplet[3] != PADDING) {
          out[o] = (byte) ((quadruplet[2] << 6) | (quadruplet[3]));
          o++;
        }
        q = 0;
      }
    }
    if ( // speculation worked out to be OK
    buflen == o) {
      return out;
    }
    // we overestimated, so need to create a new buffer
    byte[] nb = new byte[o];
    System.arraycopy(out, 0, nb, 0, o);
    return nb;
  }

  /** The Constant encodeMap. */
  private static final char[] encodeMap = initEncodeMap();

  /**
   * Inits the encode map.
   *
   * @return the char[]
   */
  private static char[] initEncodeMap() {
    char[] map = new char[64];
    int i;
    for (i = 0; i < 26; i++) {
      map[i] = (char) ('A' + i);
    }
    for (i = 26; i < 52; i++) {
      map[i] = (char) ('a' + (i - 26));
    }
    for (i = 52; i < 62; i++) {
      map[i] = (char) ('0' + (i - 52));
    }
    map[62] = '+';
    map[63] = '/';
    return map;
  }

  /**
   * Encode.
   *
   * @param i the i
   * @return the char
   */
  public static char encode(int i) {
    return encodeMap[i & 0x3F];
  }

  /**
   * Encode byte.
   *
   * @param i the i
   * @return the byte
   */
  public static byte encodeByte(int i) {
    return (byte) encodeMap[i & 0x3F];
  }

  /**
   * Prints the base 64 binary.
   *
   * @param input the input
   * @return the string
   */
  public static String _printBase64Binary(byte[] input) {
    return _printBase64Binary(input, 0, input.length);
  }

  /**
   * Prints the base 64 binary.
   *
   * @param input the input
   * @param offset the offset
   * @param len the len
   * @return the string
   */
  public static String _printBase64Binary(byte[] input, int offset, int len) {
    char[] buf = new char[((len + 2) / 3) * 4];
    int ptr = _printBase64Binary(input, offset, len, buf, 0);
    assert ptr == buf.length;
    return new String(buf);
  }

  /**
   * Encodes a byte array into a char array by doing base64 encoding.
   *
   * <p>The caller must supply a big enough buffer.
   *
   * @param input the input
   * @param offset the offset
   * @param len the len
   * @param buf the buf
   * @param ptr the ptr
   * @return the value of {@code ptr+((len+2)/3)*4}, which is the new offset in the output buffer
   *     where the further bytes should be placed.
   */
  public static int _printBase64Binary(byte[] input, int offset, int len, char[] buf, int ptr) {
    // encode elements until only 1 or 2 elements are left to encode
    int remaining = len;
    int i;
    for (i = offset; remaining >= 3; remaining -= 3, i += 3) {
      buf[ptr] = encode(input[i] >> 2);
      ptr++;
      buf[ptr] = encode(((input[i] & 0x3) << 4) | ((input[i + 1] >> 4) & 0xF));
      ptr++;
      buf[ptr] = encode(((input[i + 1] & 0xF) << 2) | ((input[i + 2] >> 6) & 0x3));
      ptr++;
      buf[ptr] = encode(input[i + 2] & 0x3F);
      ptr++;
    }
    // encode when exactly 1 element (left) to encode
    if (remaining == 1) {
      buf[ptr] = encode(input[i] >> 2);
      ptr++;
      buf[ptr] = encode(((input[i]) & 0x3) << 4);
      ptr++;
      buf[ptr] = '=';
      ptr++;
      buf[ptr] = '=';
      ptr++;
    }
    // encode when exactly 2 elements (left) to encode
    if (remaining == 2) {
      buf[ptr] = encode(input[i] >> 2);
      ptr++;
      buf[ptr] = encode(((input[i] & 0x3) << 4) | ((input[i + 1] >> 4) & 0xF));
      ptr++;
      buf[ptr] = encode((input[i + 1] & 0xF) << 2);
      ptr++;
      buf[ptr] = '=';
      ptr++;
    }
    return ptr;
  }

  /**
   * Encodes a byte array into another byte array by first doing base64 encoding then encoding the
   * result in ASCII.
   *
   * <p>The caller must supply a big enough buffer.
   *
   * @param input the input
   * @param offset the offset
   * @param len the len
   * @param out the out
   * @param ptr the ptr
   * @return the value of {@code ptr+((len+2)/3)*4}, which is the new offset in the output buffer
   *     where the further bytes should be placed.
   */
  public static int _printBase64Binary(byte[] input, int offset, int len, byte[] out, int ptr) {
    byte[] buf = out;
    int remaining = len;
    int i;
    for (i = offset; remaining >= 3; remaining -= 3, i += 3) {
      buf[ptr] = encodeByte(input[i] >> 2);
      ptr++;
      buf[ptr] = encodeByte(((input[i] & 0x3) << 4) | ((input[i + 1] >> 4) & 0xF));
      ptr++;
      buf[ptr] = encodeByte(((input[i + 1] & 0xF) << 2) | ((input[i + 2] >> 6) & 0x3));
      ptr++;
      buf[ptr] = encodeByte(input[i + 2] & 0x3F);
      ptr++;
    }
    // encode when exactly 1 element (left) to encode
    if (remaining == 1) {
      buf[ptr] = encodeByte(input[i] >> 2);
      ptr++;
      buf[ptr] = encodeByte(((input[i]) & 0x3) << 4);
      ptr++;
      buf[ptr] = '=';
      ptr++;
      buf[ptr] = '=';
      ptr++;
    }
    // encode when exactly 2 elements (left) to encode
    if (remaining == 2) {
      buf[ptr] = encodeByte(input[i] >> 2);
      ptr++;
      buf[ptr] = encodeByte(((input[i] & 0x3) << 4) | ((input[i + 1] >> 4) & 0xF));
      ptr++;
      buf[ptr] = encodeByte((input[i + 1] & 0xF) << 2);
      ptr++;
      buf[ptr] = '=';
      ptr++;
    }
    return ptr;
  }

  /**
   * Removes the optional plus.
   *
   * @param s the s
   * @return the char sequence
   */
  private static CharSequence removeOptionalPlus(CharSequence s) {
    int len = s.length();
    if ((len <= 1) || (s.charAt(0) != '+')) {
      return s;
    }
    s = s.subSequence(1, len);
    char ch = s.charAt(0);
    if (('0' <= ch) && (ch <= '9')) {
      return s;
    }
    if ('.' == ch) {
      return s;
    }
    throw new NumberFormatException();
  }

  /**
   * Checks if is digit or period or sign.
   *
   * @param ch the ch
   * @return true, if is digit or period or sign
   */
  private static boolean isDigitOrPeriodOrSign(char ch) {
    if (('0' <= ch) && (ch <= '9')) {
      return true;
    }
    if ((ch == '+') || (ch == '-') || (ch == '.')) {
      return true;
    }
    return false;
  }

  /**
   * Parses the string.
   *
   * @param lexicalXSDString the lexical XSD string
   * @return the string
   */
  public String parseString(String lexicalXSDString) {
    return lexicalXSDString;
  }

  /**
   * Parses the integer.
   *
   * @param lexicalXSDInteger the lexical XSD integer
   * @return the big integer
   */
  public BigInteger parseInteger(String lexicalXSDInteger) {
    return _parseInteger(lexicalXSDInteger);
  }

  /**
   * Parses the integer.
   *
   * @param s the s
   * @return the big integer
   */
  public static BigInteger _parseInteger(CharSequence s) {
    return new BigInteger(removeOptionalPlus(WhiteSpaceProcessor.trim(s)).toString());
  }

  /**
   * Prints the integer.
   *
   * @param val the val
   * @return the string
   */
  public String printInteger(BigInteger val) {
    return _printInteger(val);
  }

  /**
   * Prints the integer.
   *
   * @param val the val
   * @return the string
   */
  public static String _printInteger(BigInteger val) {
    return val.toString();
  }

  /**
   * Parses the int.
   *
   * @param s the s
   * @return the int
   */
  public int parseInt(String s) {
    return _parseInt(s);
  }

  /**
   * Faster but less robust String->int conversion.
   *
   * <p>Note that:
   *
   * <ol>
   *   <li>XML Schema allows '+', but {@link Integer#valueOf(String)} is not.
   *   <li>XML Schema allows leading and trailing (but not in-between) whitespaces. {@link
   *       Integer#valueOf(String)} doesn't allow any.
   * </ol>
   *
   * @param s the s
   * @return the int
   */
  public static int _parseInt(CharSequence s) {
    int len = s.length();
    int sign = 1;
    int r = 0;
    for (int i = 0; i < len; i++) {
      char ch = s.charAt(i);
      if (WhiteSpaceProcessor.isWhiteSpace(ch)) {
        // skip whitespace
      } else if (('0' <= ch) && (ch <= '9')) {
        r = (r * 10) + (ch - '0');
      } else if (ch == '-') {
        sign = -1;
      } else if (ch == '+') {
        // noop
      } else {
        throw new NumberFormatException("Not a number: " + s);
      }
    }
    return r * sign;
  }

  /**
   * Parses the long.
   *
   * @param lexicalXSLong the lexical XS long
   * @return the long
   */
  public long parseLong(String lexicalXSLong) {
    return _parseLong(lexicalXSLong);
  }

  /**
   * Parses the long.
   *
   * @param s the s
   * @return the long
   */
  public static long _parseLong(CharSequence s) {
    return Long.parseLong(removeOptionalPlus(WhiteSpaceProcessor.trim(s)).toString());
  }

  /**
   * Parses the short.
   *
   * @param lexicalXSDShort the lexical XSD short
   * @return the short
   */
  public short parseShort(String lexicalXSDShort) {
    return _parseShort(lexicalXSDShort);
  }

  /**
   * Parses the short.
   *
   * @param s the s
   * @return the short
   */
  public static short _parseShort(CharSequence s) {
    return (short) _parseInt(s);
  }

  /**
   * Prints the short.
   *
   * @param val the val
   * @return the string
   */
  public String printShort(short val) {
    return _printShort(val);
  }

  /**
   * Prints the short.
   *
   * @param val the val
   * @return the string
   */
  public static String _printShort(short val) {
    return String.valueOf(val);
  }

  /**
   * Parses the decimal.
   *
   * @param content the content
   * @return the big decimal
   */
  public BigDecimal parseDecimal(String content) {
    return _parseDecimal(content);
  }

  /**
   * Parses the decimal.
   *
   * @param content the content
   * @return the big decimal
   */
  public static BigDecimal _parseDecimal(CharSequence content) {
    content = WhiteSpaceProcessor.trim(content);
    if (content.length() <= 0) {
      return null;
    }
    return new BigDecimal(content.toString());
    // from purely XML Schema perspective,
    // this implementation has a problem, since
    // in xs:decimal "1.0" and "1" is equal whereas the above
    // code will return different values for those two forms.
    //
    // the code was originally using com.sun.msv.datatype.xsd.NumberType.load,
    // but a profiling showed that the process of normalizing "1.0" into "1"
    // could take non-trivial time.
    //
    // also, from the user's point of view, one might be surprised if
    // 1 (not 1.0) is returned from "1.000"
  }

  /**
   * Parses the float.
   *
   * @param lexicalXSDFloat the lexical XSD float
   * @return the float
   */
  public float parseFloat(String lexicalXSDFloat) {
    return _parseFloat(lexicalXSDFloat);
  }

  /**
   * Parses the float.
   *
   * @param _val the val
   * @return the float
   */
  public static float _parseFloat(CharSequence _val) {
    String s = WhiteSpaceProcessor.trim(_val).toString();
    /*
     * Incompatibilities of XML Schema's float "xfloat" and Java's float "jfloat"
     *
     * jfloat.valueOf ignores leading and trailing whitespaces, whereas this is not
     * allowed in xfloat. jfloat.valueOf allows "float type suffix" (f, F) to be
     * appended after float literal (e.g., 1.52e-2f), whereare this is not the case
     * of xfloat.
     *
     * gray zone --------- jfloat allows ".523". And there is no clear statement
     * that mentions this case in xfloat. Although probably this is allowed.
     *
     */
    if (s.equals("NaN")) {
      return Float.NaN;
    }
    if (s.equals("INF")) {
      return Float.POSITIVE_INFINITY;
    }
    if (s.equals("-INF")) {
      return Float.NEGATIVE_INFINITY;
    }
    if ((s.length() == 0)
        || !isDigitOrPeriodOrSign(s.charAt(0))
        || !isDigitOrPeriodOrSign(s.charAt(s.length() - 1))) {
      throw new NumberFormatException();
    }
    // these screening process is necessary due to the wobble of Float.valueOf
    // method
    return Float.parseFloat(s);
  }

  /**
   * Prints the float.
   *
   * @param v the v
   * @return the string
   */
  public String printFloat(float v) {
    return _printFloat(v);
  }

  /**
   * Prints the float.
   *
   * @param v the v
   * @return the string
   */
  public static String _printFloat(float v) {
    if (Float.isNaN(v)) {
      return "NaN";
    }
    if (v == Float.POSITIVE_INFINITY) {
      return "INF";
    }
    if (v == Float.NEGATIVE_INFINITY) {
      return "-INF";
    }
    return String.valueOf(v);
  }

  /**
   * Parses the double.
   *
   * @param lexicalXSDDouble the lexical XSD double
   * @return the double
   */
  public double parseDouble(String lexicalXSDDouble) {
    return _parseDouble(lexicalXSDDouble);
  }

  /**
   * Parses the double.
   *
   * @param _val the val
   * @return the double
   */
  public static double _parseDouble(CharSequence _val) {
    String val = WhiteSpaceProcessor.trim(_val).toString();
    if (val.equals("NaN")) {
      return Double.NaN;
    }
    if (val.equals("INF")) {
      return Double.POSITIVE_INFINITY;
    }
    if (val.equals("-INF")) {
      return Double.NEGATIVE_INFINITY;
    }
    if ((val.length() == 0)
        || !isDigitOrPeriodOrSign(val.charAt(0))
        || !isDigitOrPeriodOrSign(val.charAt(val.length() - 1))) {
      throw new NumberFormatException(val);
    }
    // these screening process is necessary due to the wobble of Float.valueOf
    // method
    return Double.parseDouble(val);
  }

  /**
   * Parses the boolean.
   *
   * @param lexicalXSDBoolean the lexical XSD boolean
   * @return true, if successful
   */
  public boolean parseBoolean(String lexicalXSDBoolean) {
    Boolean b = _parseBoolean(lexicalXSDBoolean);
    return (b == null) ? false : b.booleanValue();
  }

  /**
   * Parses the boolean.
   *
   * @param literal the literal
   * @return the boolean
   */
  public static Boolean _parseBoolean(CharSequence literal) {
    if (literal == null) {
      return null;
    }
    int i = 0;
    int len = literal.length();
    char ch;
    boolean value = false;
    if (literal.length() <= 0) {
      return null;
    }
    do {
      ch = literal.charAt(i);
      i++;
    } while (WhiteSpaceProcessor.isWhiteSpace(ch) && (i < len));
    int strIndex = 0;
    switch (ch) {
      case '1':
        value = true;
        break;
      case '0':
        value = false;
        break;
      case 't':
        String strTrue = "rue";
        do {
          ch = literal.charAt(i);
          i++;
        } while ((strTrue.charAt(strIndex++) == ch) && (i < len) && (strIndex < 3));
        if (strIndex != 3) {
          return false;
        }
        // throw new IllegalArgumentException("String \"" + literal + "\" is not valid
        // boolean value.");
        value = true;
        break;
      case 'f':
        String strFalse = "alse";
        do {
          ch = literal.charAt(i);
          i++;
        } while ((strFalse.charAt(strIndex++) == ch) && (i < len) && (strIndex < 4));
        if (strIndex != 4) {
          return false;
        }
        // throw new IllegalArgumentException("String \"" + literal + "\" is not valid
        // boolean value.");
        value = false;
        break;
    }
    if (i < len) {
      do {
        ch = literal.charAt(i);
        i++;
      } while (WhiteSpaceProcessor.isWhiteSpace(ch) && (i < len));
    }
    if (i == len) {
      return value;
    }
    return null;
  }

  /**
   * Prints the boolean.
   *
   * @param val the val
   * @return the string
   */
  public String printBoolean(boolean val) {
    return val ? "true" : "false";
  }

  /**
   * Prints the boolean.
   *
   * @param val the val
   * @return the string
   */
  public static String _printBoolean(boolean val) {
    return val ? "true" : "false";
  }

  /**
   * Parses the byte.
   *
   * @param lexicalXSDByte the lexical XSD byte
   * @return the byte
   */
  public byte parseByte(String lexicalXSDByte) {
    return _parseByte(lexicalXSDByte);
  }

  /**
   * Parses the byte.
   *
   * @param literal the literal
   * @return the byte
   */
  public static byte _parseByte(CharSequence literal) {
    return (byte) _parseInt(literal);
  }

  /**
   * Prints the byte.
   *
   * @param val the val
   * @return the string
   */
  public String printByte(byte val) {
    return _printByte(val);
  }

  /**
   * Prints the byte.
   *
   * @param val the val
   * @return the string
   */
  public static String _printByte(byte val) {
    return String.valueOf(val);
  }
}
