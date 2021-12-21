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

/**
 * Processes white space normalization.
 *
 * @since 1.0
 */
abstract class WhiteSpaceProcessor {

  // benchmarking (see test/src/ReplaceTest.java in the CVS Attic)
  // showed that this code is slower than the current code.
  //
  // public static String replace(String text) {
  // final int len = text.length();
  // StringBuffer result = new StringBuffer(len);
  //
  // for (int i = 0; i < len; i++) {
  // char ch = text.charAt(i);
  // if (isWhiteSpace(ch))
  // result.append(' ');
  // else
  // result.append(ch);
  // }
  //
  // return result.toString();
  // }
  /**
   * Replace.
   *
   * @param text the text
   * @return the string
   */
  public static String replace(String text) {
    return replace((CharSequence) text).toString();
  }

  /**
   * Replace.
   *
   * @param text the text
   * @return the char sequence
   * @since 2.0
   */
  public static CharSequence replace(CharSequence text) {
    int i = text.length() - 1;
    // look for the first whitespace char.
    while ((i >= 0) && !isWhiteSpaceExceptSpace(text.charAt(i))) {
      i--;
    }
    if (i < 0) {
      // no such whitespace. replace(text)==text.
      return text;
    }
    // we now know that we need to modify the text.
    // allocate a char array to do it.
    StringBuilder buf = new StringBuilder(text);
    buf.setCharAt(i, ' ');
    i--;
    for (; i >= 0; i--) {
      if (isWhiteSpaceExceptSpace(buf.charAt(i))) {
        buf.setCharAt(i, ' ');
      }
    }
    return new String(buf);
  }

  /**
   * Equivalent of {@link String#trim()}.
   *
   * @param text the text
   * @return the char sequence
   * @since 2.0
   */
  public static CharSequence trim(CharSequence text) {
    int len = text.length();
    int start = 0;
    while ((start < len) && isWhiteSpace(text.charAt(start))) {
      start++;
    }
    int end = len - 1;
    while ((end > start) && isWhiteSpace(text.charAt(end))) {
      end--;
    }
    if ((start == 0) && (end == (len - 1))) {
      // no change
      return text;
    }
    return text.subSequence(start, end + 1);
  }

  /**
   * Collapse.
   *
   * @param text the text
   * @return the string
   */
  public static String collapse(String text) {
    return collapse((CharSequence) text).toString();
  }

  /**
   * This is usually the biggest processing bottleneck.
   *
   * @param text the text
   * @return the char sequence
   * @since 2.0
   */
  public static CharSequence collapse(CharSequence text) {
    int len = text.length();
    // most of the texts are already in the collapsed form.
    // so look for the first whitespace in the hope that we will
    // never see it.
    int s = 0;
    while (s < len) {
      if (isWhiteSpace(text.charAt(s))) {
        break;
      }
      s++;
    }
    if (s == len) {
      // the input happens to be already collapsed.
      return text;
    }
    // we now know that the input contains spaces.
    // let's sit down and do the collapsing normally.
    StringBuilder result = new StringBuilder(len);
    if (s != 0) {
      for (int i = 0; i < s; i++) {
        result.append(text.charAt(i));
      }
      result.append(' ');
    }
    boolean inStripMode = true;
    for (int i = s + 1; i < len; i++) {
      char ch = text.charAt(i);
      boolean b = isWhiteSpace(ch);
      if (inStripMode && b) {
        // skip this character
        continue;
      }
      inStripMode = b;
      if (inStripMode) {
        result.append(' ');
      } else {
        result.append(ch);
      }
    }
    // remove trailing whitespaces
    len = result.length();
    if ((len > 0) && (result.charAt(len - 1) == ' ')) {
      result.setLength(len - 1);
      // whitespaces are already collapsed,
      // so all we have to do is to remove the last one character
      // if it's a whitespace.
    }
    return result;
  }

  /**
   * Returns true if the specified string is all whitespace.
   *
   * @param s the s
   * @return true, if is white space
   */
  public static final boolean isWhiteSpace(CharSequence s) {
    for (int i = s.length() - 1; i >= 0; i--) {
      if (!isWhiteSpace(s.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * returns true if the specified char is a white space character.
   *
   * @param ch the ch
   * @return true, if is white space
   */
  public static final boolean isWhiteSpace(char ch) {
    // most of the characters are non-control characters.
    // so check that first to quickly return false for most of the cases.
    if (ch > 0x20) {
      return false;
    }
    // other than we have to do four comparisons.
    return (ch == 0x9) || (ch == 0xA) || (ch == 0xD) || (ch == 0x20);
  }

  /**
   * Returns true if the specified char is a white space character but not 0x20.
   *
   * @param ch the ch
   * @return true, if is white space except space
   */
  protected static final boolean isWhiteSpaceExceptSpace(char ch) {
    // most of the characters are non-control characters.
    // so check that first to quickly return false for most of the cases.
    if (ch >= 0x20) {
      return false;
    }
    // other than we have to do four comparisons.
    return (ch == 0x9) || (ch == 0xA) || (ch == 0xD);
  }
}
