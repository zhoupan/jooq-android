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
package org.jooq.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Operations on {@link java.lang.String} that are <code>null</code> safe.
 *
 * <ul>
 *   <li><b>IsEmpty/IsBlank</b> - checks if a String contains text
 *   <li><b>Trim/Strip</b> - removes leading and trailing whitespace
 *   <li><b>Equals</b> - compares two strings null-safe
 *   <li><b>startsWith</b> - check if a String starts with a prefix null-safe
 *   <li><b>endsWith</b> - check if a String ends with a suffix null-safe
 *   <li><b>IndexOf/LastIndexOf/Contains</b> - null-safe index-of checks
 *   <li><b>IndexOfAny/LastIndexOfAny/IndexOfAnyBut/LastIndexOfAnyBut</b> - index-of any of a set of
 *       Strings
 *   <li><b>ContainsOnly/ContainsNone/ContainsAny</b> - does String contains only/none/any of these
 *       characters
 *   <li><b>Substring/Left/Right/Mid</b> - null-safe substring extractions
 *   <li><b>SubstringBefore/SubstringAfter/SubstringBetween</b> - substring extraction relative to
 *       other strings
 *   <li><b>Split/Join</b> - splits a String into an array of substrings and vice versa
 *   <li><b>Remove/Delete</b> - removes part of a String
 *   <li><b>Replace/Overlay</b> - Searches a String and replaces one String with another
 *   <li><b>Chomp/Chop</b> - removes the last part of a String
 *   <li><b>LeftPad/RightPad/Center/Repeat</b> - pads a String
 *   <li><b>UpperCase/LowerCase/SwapCase/Capitalize/Uncapitalize</b> - changes the case of a String
 *   <li><b>CountMatches</b> - counts the number of occurrences of one String in another
 *   <li><b>IsAlpha/IsNumeric/IsWhitespace/IsAsciiPrintable</b> - checks the characters in a String
 *   <li><b>DefaultString</b> - protects against a null input String
 *   <li><b>Reverse/ReverseDelimited</b> - reverses a String
 *   <li><b>Abbreviate</b> - abbreviates a string using ellipsis
 *   <li><b>Difference</b> - compares Strings and reports on their differences
 *   <li><b>LevensteinDistance</b> - the number of changes needed to change one String into another
 * </ul>
 *
 * <p>The <code>StringUtils</code> class defines certain words related to String handling.
 *
 * <ul>
 *   <li>null - <code>null</code>
 *   <li>empty - a zero-length string (<code>""</code>)
 *   <li>space - the space character (<code>' '</code>, char 32)
 *   <li>whitespace - the characters defined by {@link Character#isWhitespace(char)}
 *   <li>trim - the characters &lt;= 32 as in {@link String#trim()}
 * </ul>
 *
 * <p><code>StringUtils</code> handles <code>null</code> input Strings quietly. That is to say that
 * a <code>null</code> input will return <code>null</code>. Where a <code>boolean</code> or <code>
 * int</code> is being returned details vary by method.
 *
 * <p>A side effect of the <code>null</code> handling is that a <code>NullPointerException</code>
 * should be considered a bug in <code>StringUtils</code> (except for deprecated methods).
 *
 * <p>Methods in this class give sample code to explain their operation. The symbol <code>*</code>
 * is used to indicate any input including <code>null</code>.
 *
 * @see java.lang.String
 * @author Apache Software Foundation
 * @author <a href="http://jakarta.apache.org/turbine/">Apache Jakarta Turbine</a>
 * @author <a href="mailto:jon@latchkey.com">Jon S. Stevens</a>
 * @author Daniel L. Rall
 * @author <a href="mailto:gcoladonato@yahoo.com">Greg Coladonato</a>
 * @author <a href="mailto:ed@apache.org">Ed Korthof</a>
 * @author <a href="mailto:rand_mcneely@yahoo.com">Rand McNeely</a>
 * @author <a href="mailto:fredrik@westermarck.com">Fredrik Westermarck</a>
 * @author Holger Krauth
 * @author <a href="mailto:alex@purpletech.com">Alexander Day Chaffee</a>
 * @author <a href="mailto:hps@intermeta.de">Henning P. Schmiedehausen</a>
 * @author Arun Mammen Thomas
 * @author Gary Gregory
 * @author Phil Steitz
 * @author Al Chou
 * @author Michael Davey
 * @author Reuben Sivan
 * @author Chris Hyzer
 * @author Scott Johnson
 * @since 1.0
 * @version $Id: StringUtils.java 911986 2010-02-19 21:19:05Z niallp $
 */
public final class StringUtils {

  /**
   * The empty String {@code ""}.
   *
   * @since 2.0
   */
  public static final String EMPTY = "";

  /**
   * Represents a failed index search.
   *
   * @since 2.1
   */
  public static final int INDEX_NOT_FOUND = -1;

  /** The maximum size to which the padding constant(s) can expand. */
  private static final int PAD_LIMIT = 8192;

  // Defaults
  // -----------------------------------------------------------------------
  /**
   * Returns either the passed in String, or if the String is <code>null</code>, an empty String
   * ("").
   *
   * <pre>
   * StringUtils.defaultString(null)  = ""
   * StringUtils.defaultString("")    = ""
   * StringUtils.defaultString("bat") = "bat"
   * </pre>
   *
   * @see String#valueOf(Object)
   * @param str the String to check, may be null
   * @return the passed in String, or the empty String if it was <code>null</code>
   */
  public static String defaultString(String str) {
    return str == null ? "" : str;
  }

  /**
   * Returns either the passed in String, or if the String is <code>null</code>, the value of <code>
   * defaultStr</code>.
   *
   * <pre>
   * StringUtils.defaultString(null, "NULL")  = "NULL"
   * StringUtils.defaultString("", "NULL")    = ""
   * StringUtils.defaultString("bat", "NULL") = "bat"
   * </pre>
   *
   * @see String#valueOf(Object)
   * @param str the String to check, may be null
   * @param defaultStr the default String to return if the input is <code>null</code>, may be null
   * @return the passed in String, or the default if it was <code>null</code>
   */
  public static String defaultString(String str, String defaultStr) {
    return str == null ? defaultStr : str;
  }

  /**
   * Returns either the passed in String, or if the String is empty or <code>null</code>, the value
   * of <code>defaultStr</code>.
   *
   * <pre>
   * StringUtils.defaultIfEmpty(null, "NULL")  = "NULL"
   * StringUtils.defaultIfEmpty("", "NULL")    = "NULL"
   * StringUtils.defaultIfEmpty("bat", "NULL") = "bat"
   * </pre>
   *
   * @see StringUtils#defaultString(String, String)
   * @param str the String to check, may be null
   * @param defaultStr the default String to return if the input is empty ("") or <code>null</code>,
   *     may be null
   * @return the passed in String, or the default
   */
  public static String defaultIfEmpty(String str, String defaultStr) {
    return StringUtils.isEmpty(str) ? defaultStr : str;
  }

  /**
   * Returns either the passed in CharSequence, or if the CharSequence is whitespace, empty ("") or
   * {@code null}, the value of {@code defaultStr}.
   *
   * <pre>
   * StringUtils.defaultIfBlank(null, "NULL")  = "NULL"
   * StringUtils.defaultIfBlank("", "NULL")    = "NULL"
   * StringUtils.defaultIfBlank(" ", "NULL")   = "NULL"
   * StringUtils.defaultIfBlank("bat", "NULL") = "bat"
   * StringUtils.defaultIfBlank("", null)      = null
   * </pre>
   *
   * @param str the CharSequence to check, may be null
   * @param defaultStr the default CharSequence to return if the input is whitespace, empty ("") or
   *     {@code null}, may be null
   * @return the passed in CharSequence, or the default
   * @see StringUtils#defaultString(String, String)
   */
  public static String defaultIfBlank(String str, String defaultStr) {
    return StringUtils.isBlank(str) ? defaultStr : str;
  }

  // Empty checks
  // -----------------------------------------------------------------------
  /**
   * Checks if a String is empty ("") or null.
   *
   * <pre>
   * StringUtils.isEmpty(null)      = true
   * StringUtils.isEmpty("")        = true
   * StringUtils.isEmpty(" ")       = false
   * StringUtils.isEmpty("bob")     = false
   * StringUtils.isEmpty("  bob  ") = false
   * </pre>
   *
   * <p>NOTE: This method changed in Lang version 2.0. It no longer trims the String. That
   * functionality is available in isBlank().
   *
   * @param str the String to check, may be null
   * @return <code>true</code> if the String is empty or null
   */
  public static boolean isEmpty(String str) {
    return str == null || str.length() == 0;
  }

  /**
   * Checks if a String is whitespace, empty ("") or null.
   *
   * <pre>
   * StringUtils.isBlank(null)      = true
   * StringUtils.isBlank("")        = true
   * StringUtils.isBlank(" ")       = true
   * StringUtils.isBlank("bob")     = false
   * StringUtils.isBlank("  bob  ") = false
   * </pre>
   *
   * @param str the String to check, may be null
   * @return <code>true</code> if the String is null, empty or whitespace
   * @since 2.0
   */
  public static boolean isBlank(String str) {
    int strLen;
    if (str == null || (strLen = str.length()) == 0) {
      return true;
    }
    for (int i = 0; i < strLen; i++) {
      if ((Character.isWhitespace(str.charAt(i)) == false)) {
        return false;
      }
    }
    return true;
  }

  // Count matches
  // -----------------------------------------------------------------------
  /**
   * Counts how many times the substring appears in the larger String.
   *
   * <p>A <code>null</code> or empty ("") String input returns <code>0</code>.
   *
   * <pre>
   * StringUtils.countMatches(null, *)       = 0
   * StringUtils.countMatches("", *)         = 0
   * StringUtils.countMatches("abba", null)  = 0
   * StringUtils.countMatches("abba", "")    = 0
   * StringUtils.countMatches("abba", "a")   = 2
   * StringUtils.countMatches("abba", "ab")  = 1
   * StringUtils.countMatches("abba", "xxx") = 0
   * </pre>
   *
   * @param str the String to check, may be null
   * @param sub the substring to count, may be null
   * @return the number of occurrences, 0 if either String is <code>null</code>
   */
  public static int countMatches(String str, String sub) {
    if (isEmpty(str) || isEmpty(sub)) {
      return 0;
    }
    int count = 0;
    int idx = 0;
    while ((idx = str.indexOf(sub, idx)) != -1) {
      count++;
      idx += sub.length();
    }
    return count;
  }

  // Padding
  // -----------------------------------------------------------------------
  /**
   * Right pad a String with spaces (' ').
   *
   * <p>The String is padded to the size of <code>size</code>.
   *
   * <pre>
   * StringUtils.rightPad(null, *)   = null
   * StringUtils.rightPad("", 3)     = "   "
   * StringUtils.rightPad("bat", 3)  = "bat"
   * StringUtils.rightPad("bat", 5)  = "bat  "
   * StringUtils.rightPad("bat", 1)  = "bat"
   * StringUtils.rightPad("bat", -1) = "bat"
   * </pre>
   *
   * @param str the String to pad out, may be null
   * @param size the size to pad to
   * @return right padded String or original String if no padding is necessary, <code>null</code> if
   *     null String input
   */
  public static String rightPad(String str, int size) {
    return rightPad(str, size, ' ');
  }

  /**
   * Right pad a String with a specified character.
   *
   * <p>The String is padded to the size of <code>size</code>.
   *
   * <pre>
   * StringUtils.rightPad(null, *, *)     = null
   * StringUtils.rightPad("", 3, 'z')     = "zzz"
   * StringUtils.rightPad("bat", 3, 'z')  = "bat"
   * StringUtils.rightPad("bat", 5, 'z')  = "batzz"
   * StringUtils.rightPad("bat", 1, 'z')  = "bat"
   * StringUtils.rightPad("bat", -1, 'z') = "bat"
   * </pre>
   *
   * @param str the String to pad out, may be null
   * @param size the size to pad to
   * @param padChar the character to pad with
   * @return right padded String or original String if no padding is necessary, <code>null</code> if
   *     null String input
   * @since 2.0
   */
  public static String rightPad(String str, int size, char padChar) {
    if (str == null) {
      return null;
    }
    int pads = size - str.length();
    if (pads <= 0) {
      // returns original String when possible
      return str;
    }
    if (pads > PAD_LIMIT) {
      return rightPad(str, size, String.valueOf(padChar));
    }
    return str.concat(padding(pads, padChar));
  }

  /**
   * Right pad a String with a specified String.
   *
   * <p>The String is padded to the size of <code>size</code>.
   *
   * <pre>
   * StringUtils.rightPad(null, *, *)      = null
   * StringUtils.rightPad("", 3, "z")      = "zzz"
   * StringUtils.rightPad("bat", 3, "yz")  = "bat"
   * StringUtils.rightPad("bat", 5, "yz")  = "batyz"
   * StringUtils.rightPad("bat", 8, "yz")  = "batyzyzy"
   * StringUtils.rightPad("bat", 1, "yz")  = "bat"
   * StringUtils.rightPad("bat", -1, "yz") = "bat"
   * StringUtils.rightPad("bat", 5, null)  = "bat  "
   * StringUtils.rightPad("bat", 5, "")    = "bat  "
   * </pre>
   *
   * @param str the String to pad out, may be null
   * @param size the size to pad to
   * @param padStr the String to pad with, null or empty treated as single space
   * @return right padded String or original String if no padding is necessary, <code>null</code> if
   *     null String input
   */
  public static String rightPad(String str, int size, String padStr) {
    if (str == null) {
      return null;
    }
    if (isEmpty(padStr)) {
      padStr = " ";
    }
    int padLen = padStr.length();
    int strLen = str.length();
    int pads = size - strLen;
    if (pads <= 0) {
      // returns original String when possible
      return str;
    }
    if (padLen == 1 && pads <= PAD_LIMIT) {
      return rightPad(str, size, padStr.charAt(0));
    }
    if (pads == padLen) {
      return str.concat(padStr);
    } else if (pads < padLen) {
      return str.concat(padStr.substring(0, pads));
    } else {
      char[] padding = new char[pads];
      char[] padChars = padStr.toCharArray();
      for (int i = 0; i < pads; i++) {
        padding[i] = padChars[i % padLen];
      }
      return str.concat(new String(padding));
    }
  }

  /**
   * Left pad a String with spaces (' ').
   *
   * <p>The String is padded to the size of <code>size</code>.
   *
   * <pre>
   * StringUtils.leftPad(null, *)   = null
   * StringUtils.leftPad("", 3)     = "   "
   * StringUtils.leftPad("bat", 3)  = "bat"
   * StringUtils.leftPad("bat", 5)  = "  bat"
   * StringUtils.leftPad("bat", 1)  = "bat"
   * StringUtils.leftPad("bat", -1) = "bat"
   * </pre>
   *
   * @param str the String to pad out, may be null
   * @param size the size to pad to
   * @return left padded String or original String if no padding is necessary, <code>null</code> if
   *     null String input
   */
  public static String leftPad(String str, int size) {
    return leftPad(str, size, ' ');
  }

  /**
   * Left pad a String with a specified character.
   *
   * <p>Pad to a size of <code>size</code>.
   *
   * <pre>
   * StringUtils.leftPad(null, *, *)     = null
   * StringUtils.leftPad("", 3, 'z')     = "zzz"
   * StringUtils.leftPad("bat", 3, 'z')  = "bat"
   * StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
   * StringUtils.leftPad("bat", 1, 'z')  = "bat"
   * StringUtils.leftPad("bat", -1, 'z') = "bat"
   * </pre>
   *
   * @param str the String to pad out, may be null
   * @param size the size to pad to
   * @param padChar the character to pad with
   * @return left padded String or original String if no padding is necessary, <code>null</code> if
   *     null String input
   * @since 2.0
   */
  public static String leftPad(String str, int size, char padChar) {
    if (str == null) {
      return null;
    }
    int pads = size - str.length();
    if (pads <= 0) {
      // returns original String when possible
      return str;
    }
    if (pads > PAD_LIMIT) {
      return leftPad(str, size, String.valueOf(padChar));
    }
    return padding(pads, padChar).concat(str);
  }

  /**
   * Left pad a String with a specified String.
   *
   * <p>Pad to a size of <code>size</code>.
   *
   * <pre>
   * StringUtils.leftPad(null, *, *)      = null
   * StringUtils.leftPad("", 3, "z")      = "zzz"
   * StringUtils.leftPad("bat", 3, "yz")  = "bat"
   * StringUtils.leftPad("bat", 5, "yz")  = "yzbat"
   * StringUtils.leftPad("bat", 8, "yz")  = "yzyzybat"
   * StringUtils.leftPad("bat", 1, "yz")  = "bat"
   * StringUtils.leftPad("bat", -1, "yz") = "bat"
   * StringUtils.leftPad("bat", 5, null)  = "  bat"
   * StringUtils.leftPad("bat", 5, "")    = "  bat"
   * </pre>
   *
   * @param str the String to pad out, may be null
   * @param size the size to pad to
   * @param padStr the String to pad with, null or empty treated as single space
   * @return left padded String or original String if no padding is necessary, <code>null</code> if
   *     null String input
   */
  public static String leftPad(String str, int size, String padStr) {
    if (str == null) {
      return null;
    }
    if (isEmpty(padStr)) {
      padStr = " ";
    }
    int padLen = padStr.length();
    int strLen = str.length();
    int pads = size - strLen;
    if (pads <= 0) {
      // returns original String when possible
      return str;
    }
    if (padLen == 1 && pads <= PAD_LIMIT) {
      return leftPad(str, size, padStr.charAt(0));
    }
    if (pads == padLen) {
      return padStr.concat(str);
    } else if (pads < padLen) {
      return padStr.substring(0, pads).concat(str);
    } else {
      char[] padding = new char[pads];
      char[] padChars = padStr.toCharArray();
      for (int i = 0; i < pads; i++) {
        padding[i] = padChars[i % padLen];
      }
      return new String(padding).concat(str);
    }
  }

  /**
   * Returns padding using the specified delimiter repeated to a given length.
   *
   * <pre>
   * StringUtils.padding(0, 'e')  = ""
   * StringUtils.padding(3, 'e')  = "eee"
   * StringUtils.padding(-2, 'e') = IndexOutOfBoundsException
   * </pre>
   *
   * <p>Note: this method doesn't not support padding with <a
   * href="http://www.unicode.org/glossary/#supplementary_character">Unicode Supplementary
   * Characters</a> as they require a pair of <code>char</code>s to be represented. If you are
   * needing to support full I18N of your applications consider using {@link #repeat(String, int)}
   * instead.
   *
   * @param repeat number of times to repeat delim
   * @param padChar character to repeat
   * @return String with repeated character
   * @throws IndexOutOfBoundsException if <code>repeat &lt; 0</code>
   */
  private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
    if (repeat < 0) {
      throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
    }
    final char[] buf = new char[repeat];
    for (int i = 0; i < buf.length; i++) {
      buf[i] = padChar;
    }
    return new String(buf);
  }

  // Abbreviating
  // -----------------------------------------------------------------------
  /**
   * Abbreviates a String using ellipses. This will turn "Now is the time for all good men" into
   * "Now is the time for..."
   *
   * <p>Specifically:
   *
   * <ul>
   *   <li>If <code>str</code> is less than <code>maxWidth</code> characters long, return it.
   *   <li>Else abbreviate it to <code>(substring(str, 0, max-3) + "...")</code>.
   *   <li>If <code>maxWidth</code> is less than <code>4</code>, throw an <code>
   *       IllegalArgumentException</code>.
   *   <li>In no case will it return a String of length greater than <code>maxWidth</code>.
   * </ul>
   *
   * <pre>
   * StringUtils.abbreviate(null, *)      = null
   * StringUtils.abbreviate("", 4)        = ""
   * StringUtils.abbreviate("abcdefg", 6) = "abc..."
   * StringUtils.abbreviate("abcdefg", 7) = "abcdefg"
   * StringUtils.abbreviate("abcdefg", 8) = "abcdefg"
   * StringUtils.abbreviate("abcdefg", 4) = "a..."
   * StringUtils.abbreviate("abcdefg", 3) = IllegalArgumentException
   * </pre>
   *
   * @param str the String to check, may be null
   * @param maxWidth maximum length of result String, must be at least 4
   * @return abbreviated String, <code>null</code> if null String input
   * @throws IllegalArgumentException if the width is too small
   * @since 2.0
   */
  public static String abbreviate(String str, int maxWidth) {
    return abbreviate(str, 0, maxWidth);
  }

  /**
   * Abbreviates a String using ellipses. This will turn "Now is the time for all good men" into
   * "...is the time for..."
   *
   * <p>Works like <code>abbreviate(String, int)</code>, but allows you to specify a "left edge"
   * offset. Note that this left edge is not necessarily going to be the leftmost character in the
   * result, or the first character following the ellipses, but it will appear somewhere in the
   * result.
   *
   * <p>In no case will it return a String of length greater than <code>maxWidth</code>.
   *
   * <pre>
   * StringUtils.abbreviate(null, *, *)                = null
   * StringUtils.abbreviate("", 0, 4)                  = ""
   * StringUtils.abbreviate("abcdefghijklmno", -1, 10) = "abcdefg..."
   * StringUtils.abbreviate("abcdefghijklmno", 0, 10)  = "abcdefg..."
   * StringUtils.abbreviate("abcdefghijklmno", 1, 10)  = "abcdefg..."
   * StringUtils.abbreviate("abcdefghijklmno", 4, 10)  = "abcdefg..."
   * StringUtils.abbreviate("abcdefghijklmno", 5, 10)  = "...fghi..."
   * StringUtils.abbreviate("abcdefghijklmno", 6, 10)  = "...ghij..."
   * StringUtils.abbreviate("abcdefghijklmno", 8, 10)  = "...ijklmno"
   * StringUtils.abbreviate("abcdefghijklmno", 10, 10) = "...ijklmno"
   * StringUtils.abbreviate("abcdefghijklmno", 12, 10) = "...ijklmno"
   * StringUtils.abbreviate("abcdefghij", 0, 3)        = IllegalArgumentException
   * StringUtils.abbreviate("abcdefghij", 5, 6)        = IllegalArgumentException
   * </pre>
   *
   * @param str the String to check, may be null
   * @param offset left edge of source String
   * @param maxWidth maximum length of result String, must be at least 4
   * @return abbreviated String, <code>null</code> if null String input
   * @throws IllegalArgumentException if the width is too small
   * @since 2.0
   */
  public static String abbreviate(String str, int offset, int maxWidth) {
    if (str == null) {
      return null;
    }
    if (maxWidth < 4) {
      throw new IllegalArgumentException("Minimum abbreviation width is 4");
    }
    if (str.length() <= maxWidth) {
      return str;
    }
    if (offset > str.length()) {
      offset = str.length();
    }
    if ((str.length() - offset) < (maxWidth - 3)) {
      offset = str.length() - (maxWidth - 3);
    }
    if (offset <= 4) {
      return str.substring(0, maxWidth - 3) + "...";
    }
    if (maxWidth < 7) {
      throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
    }
    if ((offset + (maxWidth - 3)) < str.length()) {
      return "..." + abbreviate(str.substring(offset), maxWidth - 3);
    }
    return "..." + str.substring(str.length() - (maxWidth - 3));
  }

  // ContainsAny
  // -----------------------------------------------------------------------
  /**
   * Checks if the String contains any character in the given set of characters.
   *
   * <p>A <code>null</code> String will return <code>false</code>. A <code>null</code> or zero
   * length search array will return <code>false</code>.
   *
   * <pre>
   * StringUtils.containsAny(null, *)                = false
   * StringUtils.containsAny("", *)                  = false
   * StringUtils.containsAny(*, null)                = false
   * StringUtils.containsAny(*, [])                  = false
   * StringUtils.containsAny("zzabyycdxx",['z','a']) = true
   * StringUtils.containsAny("zzabyycdxx",['b','y']) = true
   * StringUtils.containsAny("aba", ['z'])           = false
   * </pre>
   *
   * @param str the String to check, may be null
   * @param searchChars the chars to search for, may be null
   * @return the <code>true</code> if any of the chars are found, <code>false</code> if no match or
   *     null input
   * @since 2.4
   */
  public static boolean containsAny(String str, char... searchChars) {
    if (str == null || str.length() == 0 || searchChars == null || searchChars.length == 0) {
      return false;
    }
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      for (int j = 0; j < searchChars.length; j++) {
        if (searchChars[j] == ch) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Replaces all occurrences of a String within another String.
   *
   * <p>A {@code null} reference passed to this method is a no-op.
   *
   * <pre>
   * StringUtils.replace(null, *, *)        = null
   * StringUtils.replace("", *, *)          = ""
   * StringUtils.replace("any", null, *)    = "any"
   * StringUtils.replace("any", *, null)    = "any"
   * StringUtils.replace("any", "", *)      = "any"
   * StringUtils.replace("aba", "a", null)  = "aba"
   * StringUtils.replace("aba", "a", "")    = "b"
   * StringUtils.replace("aba", "a", "z")   = "zbz"
   * </pre>
   *
   * @see #replace(String text, String searchString, String replacement, int max)
   * @param text text to search and replace in, may be null
   * @param searchString the String to search for, may be null
   * @param replacement the String to replace it with, may be null
   * @return the text with any replacements processed, {@code null} if null String input
   */
  public static String replace(String text, String searchString, String replacement) {
    return replace(text, searchString, replacement, -1);
  }

  /**
   * Replaces a String with another String inside a larger String, for the first {@code max} values
   * of the search String.
   *
   * <p>A {@code null} reference passed to this method is a no-op.
   *
   * <pre>
   * StringUtils.replace(null, *, *, *)         = null
   * StringUtils.replace("", *, *, *)           = ""
   * StringUtils.replace("any", null, *, *)     = "any"
   * StringUtils.replace("any", *, null, *)     = "any"
   * StringUtils.replace("any", "", *, *)       = "any"
   * StringUtils.replace("any", *, *, 0)        = "any"
   * StringUtils.replace("abaa", "a", null, -1) = "abaa"
   * StringUtils.replace("abaa", "a", "", -1)   = "b"
   * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
   * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
   * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
   * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
   * </pre>
   *
   * @param text text to search and replace in, may be null
   * @param searchString the String to search for, may be null
   * @param replacement the String to replace it with, may be null
   * @param max maximum number of values to replace, or {@code -1} if no maximum
   * @return the text with any replacements processed, {@code null} if null String input
   */
  public static String replace(String text, String searchString, String replacement, int max) {
    if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
      return text;
    }
    int start = 0;
    int end = text.indexOf(searchString, start);
    if (end == INDEX_NOT_FOUND) {
      return text;
    }
    int replLength = searchString.length();
    int increase = replacement.length() - replLength;
    increase = (increase < 0 ? 0 : increase);
    increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
    StringBuilder buf = new StringBuilder(text.length() + increase);
    while (end != INDEX_NOT_FOUND) {
      buf.append(text, start, end).append(replacement);
      start = end + replLength;
      if (--max == 0) {
        break;
      }
      end = text.indexOf(searchString, start);
    }
    buf.append(text, start, text.length());
    return buf.toString();
  }

  /**
   * Replaces all occurrences of Strings within another String.
   *
   * <p>A <code>null</code> reference passed to this method is a no-op, or if any "search string" or
   * "string to replace" is null, that replace will be ignored. This will not repeat. For repeating
   * replaces, call the overloaded method.
   *
   * <pre>
   *  StringUtils.replaceEach(null, *, *)        = null
   *  StringUtils.replaceEach("", *, *)          = ""
   *  StringUtils.replaceEach("aba", null, null) = "aba"
   *  StringUtils.replaceEach("aba", new String[0], null) = "aba"
   *  StringUtils.replaceEach("aba", null, new String[0]) = "aba"
   *  StringUtils.replaceEach("aba", new String[]{"a"}, null)  = "aba"
   *  StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{""})  = "b"
   *  StringUtils.replaceEach("aba", new String[]{null}, new String[]{"a"})  = "aba"
   *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"})  = "wcte"
   *  (example of how it does not repeat)
   *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"})  = "dcte"
   * </pre>
   *
   * @param text text to search and replace in, no-op if null
   * @param searchList the Strings to search for, no-op if null
   * @param replacementList the Strings to replace them with, no-op if null
   * @return the text with any replacements processed, <code>null</code> if null String input
   * @throws IndexOutOfBoundsException if the lengths of the arrays are not the same (null is ok,
   *     and/or size 0)
   * @since 2.4
   */
  public static String replaceEach(String text, String[] searchList, String[] replacementList) {
    return replaceEach(text, searchList, replacementList, false, 0);
  }

  /**
   * Replaces all occurrences of Strings within another String.
   *
   * <p>A <code>null</code> reference passed to this method is a no-op, or if any "search string" or
   * "string to replace" is null, that replace will be ignored.
   *
   * <pre>
   *  StringUtils.replaceEach(null, *, *, *) = null
   *  StringUtils.replaceEach("", *, *, *) = ""
   *  StringUtils.replaceEach("aba", null, null, *) = "aba"
   *  StringUtils.replaceEach("aba", new String[0], null, *) = "aba"
   *  StringUtils.replaceEach("aba", null, new String[0], *) = "aba"
   *  StringUtils.replaceEach("aba", new String[]{"a"}, null, *) = "aba"
   *  StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{""}, *) = "b"
   *  StringUtils.replaceEach("aba", new String[]{null}, new String[]{"a"}, *) = "aba"
   *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}, *) = "wcte"
   *  (example of how it repeats)
   *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}, false) = "dcte"
   *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}, true) = "tcte"
   *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"}, *) = IllegalArgumentException
   * </pre>
   *
   * @param text text to search and replace in, no-op if null
   * @param searchList the Strings to search for, no-op if null
   * @param replacementList the Strings to replace them with, no-op if null
   * @param repeat if true, then replace repeatedly until there are no more possible replacements or
   *     timeToLive &lt; 0
   * @param timeToLive if less than 0 then there is a circular reference and endless loop
   * @return the text with any replacements processed, <code>null</code> if null String input
   * @throws IllegalArgumentException if the search is repeating and there is an endless loop due to
   *     outputs of one being inputs to another
   * @throws IndexOutOfBoundsException if the lengths of the arrays are not the same (null is ok,
   *     and/or size 0)
   * @since 2.4
   */
  private static String replaceEach(
      String text, String[] searchList, String[] replacementList, boolean repeat, int timeToLive) {
    // mchyzer Performance note: This creates very few new objects (one major goal)
    // let me know if there are performance requests, we can create a harness to measure
    if (text == null
        || text.length() == 0
        || searchList == null
        || searchList.length == 0
        || replacementList == null
        || replacementList.length == 0) {
      return text;
    }
    // if recursing, this shouldnt be less than 0
    if (timeToLive < 0) {
      throw new IllegalStateException("TimeToLive of " + timeToLive + " is less than 0: " + text);
    }
    int searchLength = searchList.length;
    int replacementLength = replacementList.length;
    // make sure lengths are ok, these need to be equal
    if (searchLength != replacementLength) {
      throw new IllegalArgumentException(
          "Search and Replace array lengths don't match: "
              + searchLength
              + " vs "
              + replacementLength);
    }
    // keep track of which still have matches
    boolean[] noMoreMatchesForReplIndex = new boolean[searchLength];
    // index on index that the match was found
    int textIndex = -1;
    int replaceIndex = -1;
    int tempIndex = -1;
    // index of replace array that will replace the search string found
    // NOTE: logic duplicated below START
    for (int i = 0; i < searchLength; i++) {
      if (noMoreMatchesForReplIndex[i]
          || searchList[i] == null
          || searchList[i].length() == 0
          || replacementList[i] == null) {
        continue;
      }
      tempIndex = text.indexOf(searchList[i]);
      // see if we need to keep searching for this
      if (tempIndex == -1) {
        noMoreMatchesForReplIndex[i] = true;
      } else {
        if (textIndex == -1 || tempIndex < textIndex) {
          textIndex = tempIndex;
          replaceIndex = i;
        }
      }
    }
    // NOTE: logic mostly below END
    // no search strings found, we are done
    if (textIndex == -1) {
      return text;
    }
    int start = 0;
    // get a good guess on the size of the result buffer so it doesnt have to double if it goes over
    // a bit
    int increase = 0;
    // count the replacement text elements that are larger than their corresponding text being
    // replaced
    for (int i = 0; i < searchList.length; i++) {
      int greater = replacementList[i].length() - searchList[i].length();
      if (greater > 0) {
        // assume 3 matches
        increase += 3 * greater;
      }
    }
    // have upper-bound at 20% increase, then let Java take over
    increase = Math.min(increase, text.length() / 5);
    StringBuffer buf = new StringBuffer(text.length() + increase);
    while (textIndex != -1) {
      for (int i = start; i < textIndex; i++) {
        buf.append(text.charAt(i));
      }
      buf.append(replacementList[replaceIndex]);
      start = textIndex + searchList[replaceIndex].length();
      textIndex = -1;
      replaceIndex = -1;
      tempIndex = -1;
      // find the next earliest match
      // NOTE: logic mostly duplicated above START
      for (int i = 0; i < searchLength; i++) {
        if (noMoreMatchesForReplIndex[i]
            || searchList[i] == null
            || searchList[i].length() == 0
            || replacementList[i] == null) {
          continue;
        }
        tempIndex = text.indexOf(searchList[i], start);
        // see if we need to keep searching for this
        if (tempIndex == -1) {
          noMoreMatchesForReplIndex[i] = true;
        } else {
          if (textIndex == -1 || tempIndex < textIndex) {
            textIndex = tempIndex;
            replaceIndex = i;
          }
        }
      }
      // NOTE: logic duplicated above END
    }
    int textLength = text.length();
    for (int i = start; i < textLength; i++) {
      buf.append(text.charAt(i));
    }
    String result = buf.toString();
    if (!repeat) {
      return result;
    }
    return replaceEach(result, searchList, replacementList, repeat, timeToLive - 1);
  }

  // Joining
  // -----------------------------------------------------------------------
  /**
   * Joins the elements of the provided array into a single String containing the provided list of
   * elements.
   *
   * <p>No separator is added to the joined String. Null objects or empty strings within the array
   * are represented by empty strings.
   *
   * <pre>
   * StringUtils.join(null)            = null
   * StringUtils.join([])              = ""
   * StringUtils.join([null])          = ""
   * StringUtils.join(["a", "b", "c"]) = "abc"
   * StringUtils.join([null, "", "a"]) = "a"
   * </pre>
   *
   * @param <T> the specific type of values to join together
   * @param elements the values to join together, may be null
   * @return the joined String, {@code null} if null array input
   * @since 2.0
   * @since 3.0 Changed signature to use varargs
   */
  @SafeVarargs
  public static <T> String join(T... elements) {
    return join(elements, null);
  }

  /**
   * Joins the elements of the provided array into a single String containing the provided list of
   * elements.
   *
   * <p>No delimiter is added before or after the list. Null objects or empty strings within the
   * array are represented by empty strings.
   *
   * <pre>
   * StringUtils.join(null, *)               = null
   * StringUtils.join([], *)                 = ""
   * StringUtils.join([null], *)             = ""
   * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
   * StringUtils.join(["a", "b", "c"], null) = "abc"
   * StringUtils.join([null, "", "a"], ';')  = ";;a"
   * </pre>
   *
   * @param array the array of values to join together, may be null
   * @param separator the separator character to use
   * @return the joined String, {@code null} if null array input
   * @since 2.0
   */
  public static String join(Object[] array, char separator) {
    if (array == null) {
      return null;
    }
    return join(array, separator, 0, array.length);
  }

  /**
   * Joins the elements of the provided array into a single String containing the provided list of
   * elements.
   *
   * <p>No delimiter is added before or after the list. Null objects or empty strings within the
   * array are represented by empty strings.
   *
   * <pre>
   * StringUtils.join(null, *)               = null
   * StringUtils.join([], *)                 = ""
   * StringUtils.join([null], *)             = ""
   * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
   * StringUtils.join(["a", "b", "c"], null) = "abc"
   * StringUtils.join([null, "", "a"], ';')  = ";;a"
   * </pre>
   *
   * @param array the array of values to join together, may be null
   * @param separator the separator character to use
   * @param startIndex the first index to start joining from. It is an error to pass in an end index
   *     past the end of the array
   * @param endIndex the index to stop joining from (exclusive). It is an error to pass in an end
   *     index past the end of the array
   * @return the joined String, {@code null} if null array input
   * @since 2.0
   */
  public static String join(Object[] array, char separator, int startIndex, int endIndex) {
    if (array == null) {
      return null;
    }
    int noOfItems = (endIndex - startIndex);
    if (noOfItems <= 0) {
      return EMPTY;
    }
    StringBuilder buf = new StringBuilder(noOfItems * 16);
    for (int i = startIndex; i < endIndex; i++) {
      if (i > startIndex) {
        buf.append(separator);
      }
      if (array[i] != null) {
        buf.append(array[i]);
      }
    }
    return buf.toString();
  }

  /**
   * Joins the elements of the provided array into a single String containing the provided list of
   * elements.
   *
   * <p>No delimiter is added before or after the list. A {@code null} separator is the same as an
   * empty String (""). Null objects or empty strings within the array are represented by empty
   * strings.
   *
   * <pre>
   * StringUtils.join(null, *)                = null
   * StringUtils.join([], *)                  = ""
   * StringUtils.join([null], *)              = ""
   * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
   * StringUtils.join(["a", "b", "c"], null)  = "abc"
   * StringUtils.join(["a", "b", "c"], "")    = "abc"
   * StringUtils.join([null, "", "a"], ',')   = ",,a"
   * </pre>
   *
   * @param array the array of values to join together, may be null
   * @param separator the separator character to use, null treated as ""
   * @return the joined String, {@code null} if null array input
   */
  public static String join(Object[] array, String separator) {
    if (array == null) {
      return null;
    }
    return join(array, separator, 0, array.length);
  }

  /**
   * Joins the elements of the provided array into a single String containing the provided list of
   * elements.
   *
   * <p>No delimiter is added before or after the list. A {@code null} separator is the same as an
   * empty String (""). Null objects or empty strings within the array are represented by empty
   * strings.
   *
   * <pre>
   * StringUtils.join(null, *)                = null
   * StringUtils.join([], *)                  = ""
   * StringUtils.join([null], *)              = ""
   * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
   * StringUtils.join(["a", "b", "c"], null)  = "abc"
   * StringUtils.join(["a", "b", "c"], "")    = "abc"
   * StringUtils.join([null, "", "a"], ',')   = ",,a"
   * </pre>
   *
   * @param array the array of values to join together, may be null
   * @param separator the separator character to use, null treated as ""
   * @param startIndex the first index to start joining from. It is an error to pass in an end index
   *     past the end of the array
   * @param endIndex the index to stop joining from (exclusive). It is an error to pass in an end
   *     index past the end of the array
   * @return the joined String, {@code null} if null array input
   */
  public static String join(Object[] array, String separator, int startIndex, int endIndex) {
    if (array == null) {
      return null;
    }
    if (separator == null) {
      separator = EMPTY;
    }
    // endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
    // (Assuming that all Strings are roughly equally long)
    int noOfItems = (endIndex - startIndex);
    if (noOfItems <= 0) {
      return EMPTY;
    }
    StringBuilder buf = new StringBuilder(noOfItems * 16);
    for (int i = startIndex; i < endIndex; i++) {
      if (i > startIndex) {
        buf.append(separator);
      }
      if (array[i] != null) {
        buf.append(array[i]);
      }
    }
    return buf.toString();
  }

  private StringUtils() {}

  // -------------------------------------------------------------------------
  // XXX: The following methods are taken from ObjectUtils
  // -------------------------------------------------------------------------
  /**
   * Compares two strings for equality, where either one or both objects may be {@code null}.
   *
   * <pre>
   * ObjectUtils.equals(null, null)                  = true
   * ObjectUtils.equals(null, "")                    = false
   * ObjectUtils.equals("", null)                    = false
   * ObjectUtils.equals("", "")                      = true
   * </pre>
   *
   * @param o1 the first object, may be {@code null}
   * @param o2 the second object, may be {@code null}
   * @return {@code true} if the values of both objects are the same
   */
  public static boolean equals(String o1, String o2) {
    return o1 == null ? o2 == null : o1.equals(o2);
  }

  /**
   * Compares two objects for deep equality, where either one or both objects may be {@code null}.
   *
   * <pre>
   * ObjectUtils.equals(null, null)                  = true
   * ObjectUtils.equals(null, "")                    = false
   * ObjectUtils.equals("", null)                    = false
   * ObjectUtils.equals("", "")                      = true
   * ObjectUtils.equals(Boolean.TRUE, null)          = false
   * ObjectUtils.equals(Boolean.TRUE, "true")        = false
   * ObjectUtils.equals(Boolean.TRUE, Boolean.TRUE)  = true
   * ObjectUtils.equals(Boolean.TRUE, Boolean.FALSE) = false
   * </pre>
   *
   * @param o1 the first object, may be {@code null}
   * @param o2 the second object, may be {@code null}
   * @return {@code true} if the values of both objects are the same
   */
  public static boolean equals(Object o1, Object o2) {
    if (o1 == o2) return true;
    else if ((o1 == null) || (o2 == null)) return false;
    else if (o1.getClass().isArray())
      if (o1 instanceof Object[] && o2 instanceof Object[])
        return Arrays.deepEquals((Object[]) o1, (Object[]) o2);
      else if (o1 instanceof byte[] && o2 instanceof byte[])
        return Arrays.equals((byte[]) o1, (byte[]) o2);
      else if (o1 instanceof short[] && o2 instanceof short[])
        return Arrays.equals((short[]) o1, (short[]) o2);
      else if (o1 instanceof int[] && o2 instanceof int[])
        return Arrays.equals((int[]) o1, (int[]) o2);
      else if (o1 instanceof long[] && o2 instanceof long[])
        return Arrays.equals((long[]) o1, (long[]) o2);
      else if (o1 instanceof float[] && o2 instanceof float[])
        return Arrays.equals((float[]) o1, (float[]) o2);
      else if (o1 instanceof double[] && o2 instanceof double[])
        return Arrays.equals((double[]) o1, (double[]) o2);
      else if (o1 instanceof char[] && o2 instanceof char[])
        return Arrays.equals((char[]) o1, (char[]) o2);
      else if (o1 instanceof boolean[] && o2 instanceof boolean[])
        return Arrays.equals((boolean[]) o1, (boolean[]) o2);
      else return false;
    else return o1.equals(o2);
  }

  /**
   * Returns a default value if the object passed is {@code null}.
   *
   * <pre>
   * ObjectUtils.defaultIfNull(null, null)      = null
   * ObjectUtils.defaultIfNull(null, "")        = ""
   * ObjectUtils.defaultIfNull(null, "zz")      = "zz"
   * ObjectUtils.defaultIfNull("abc", *)        = "abc"
   * ObjectUtils.defaultIfNull(Boolean.TRUE, *) = Boolean.TRUE
   * </pre>
   *
   * @param <T> the type of the object
   * @param object the {@code Object} to test, may be {@code null}
   * @param defaultValue the default value to return, may be {@code null}
   * @return {@code object} if it is not {@code null}, defaultValue otherwise
   */
  public static <T> T defaultIfNull(T object, T defaultValue) {
    return object != null ? object : defaultValue;
  }

  /**
   * Returns the first non-{@code null} argument.
   *
   * @param <T> the type of the objects
   * @param objects the elements to test, may not be {@code null} but empty
   * @return first non-{@code null} element in {@code objects}, otherwise {@code null}
   */
  @SafeVarargs
  public static <T> T firstNonNull(T... objects) {
    for (T object : objects) {
      if (object != null) return object;
    }
    return null;
  }

  // -------------------------------------------------------------------------
  // XXX: The following methods are not part of Apache's commons-lang library
  // -------------------------------------------------------------------------
  /** Convert a string to camel case */
  public static String toCamelCase(String string) {
    StringBuilder result = new StringBuilder();
    // [#2515] - Keep trailing underscores
    for (String word : string.split("_", -1)) {
      // Uppercase first letter of a word
      if (word.length() > 0) {
        // [#82] - If a word starts with a digit, prevail the
        // underscore to prevent naming clashes
        if (Character.isDigit(word.charAt(0))) {
          result.append("_");
        }
        result.append(word.substring(0, 1).toUpperCase());
        result.append(word.substring(1).toLowerCase());
      } else // If no letter exists, prevail the underscore (e.g. leading
      // underscores)
      {
        result.append("_");
      }
    }
    return result.toString();
  }

  /** Convert a string to camel case starting with a lower case letter */
  public static String toCamelCaseLC(String string) {
    return toLC(toCamelCase(string));
  }

  /** Change a string's first letter to lower case */
  public static String toLC(String string) {
    if (string == null || string.isEmpty()) return string;
    return Character.toLowerCase(string.charAt(0)) + string.substring(1);
  }

  /** Change a string's first letter to upper case */
  public static String toUC(String string) {
    if (string == null || string.isEmpty()) return string;
    return Character.toUpperCase(string.charAt(0)) + string.substring(1);
  }

  /**
   * A custom adaptation of {@link Pattern#split(CharSequence, int)}.
   *
   * <p>This is useful if the matched split-tokens should be returned as well. For example: <code>
   * <pre>
   * split("e", "hello world")    // ["h", "e", "llo world"]
   * split("o", "hello world")    // ["hell", "o", " w", "o", "rld"]
   * split("[eo]", "hello world") // ["h", "e", "ll", "o", " w", "o", "rld"]
   * </pre></code>
   *
   * <p>The result will always be an odd-length array.
   */
  public static String[] split(String regex, CharSequence input) {
    int index = 0;
    ArrayList<String> matchList = new ArrayList<>();
    Matcher m = Pattern.compile(regex).matcher(input);
    // Add segments before each match found
    while (m.find()) {
      matchList.add(input.subSequence(index, m.start()).toString());
      matchList.add(input.subSequence(m.start(), m.end()).toString());
      index = m.end();
    }
    // If no match was found, return this
    if (index == 0) return new String[] {input.toString()};
    // Add remaining segment
    matchList.add(input.subSequence(index, input.length()).toString());
    // Construct result
    Iterator<String> it = matchList.iterator();
    while (it.hasNext()) {
      if ("".equals(it.next())) {
        it.remove();
      }
    }
    String[] result = new String[matchList.size()];
    return matchList.toArray(result);
  }
}
