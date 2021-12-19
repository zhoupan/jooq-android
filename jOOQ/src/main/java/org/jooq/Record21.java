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
package org.jooq;

import org.jetbrains.annotations.NotNull;

/**
 * A model type for a records with degree <code>21</code>
 *
 * @see Row21
 * @author Lukas Eder
 */
public interface Record21<
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16,
        T17,
        T18,
        T19,
        T20,
        T21>
    extends Record {

  // ------------------------------------------------------------------------
  // Row value expressions
  // ------------------------------------------------------------------------

  /** Get this record's fields as a {@link Row21}. */
  @NotNull
  @Override
  Row21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      fieldsRow();

  /** Get this record's values as a {@link Row21}. */
  @NotNull
  @Override
  Row21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      valuesRow();

  // ------------------------------------------------------------------------
  // Field accessors
  // ------------------------------------------------------------------------

  /** Get the first field. */
  @NotNull
  Field<T1> field1();

  /** Get the second field. */
  @NotNull
  Field<T2> field2();

  /** Get the third field. */
  @NotNull
  Field<T3> field3();

  /** Get the fourth field. */
  @NotNull
  Field<T4> field4();

  /** Get the fifth field. */
  @NotNull
  Field<T5> field5();

  /** Get the sixth field. */
  @NotNull
  Field<T6> field6();

  /** Get the seventh field. */
  @NotNull
  Field<T7> field7();

  /** Get the eighth field. */
  @NotNull
  Field<T8> field8();

  /** Get the ninth field. */
  @NotNull
  Field<T9> field9();

  /** Get the tenth field. */
  @NotNull
  Field<T10> field10();

  /** Get the eleventh field. */
  @NotNull
  Field<T11> field11();

  /** Get the twelfth field. */
  @NotNull
  Field<T12> field12();

  /** Get the thirteenth field. */
  @NotNull
  Field<T13> field13();

  /** Get the fourteenth field. */
  @NotNull
  Field<T14> field14();

  /** Get the fifteenth field. */
  @NotNull
  Field<T15> field15();

  /** Get the sixteenth field. */
  @NotNull
  Field<T16> field16();

  /** Get the seventeenth field. */
  @NotNull
  Field<T17> field17();

  /** Get the eighteenth field. */
  @NotNull
  Field<T18> field18();

  /** Get the ninteenth field. */
  @NotNull
  Field<T19> field19();

  /** Get the twentieth field. */
  @NotNull
  Field<T20> field20();

  /** Get the twenty-first field. */
  @NotNull
  Field<T21> field21();

  // ------------------------------------------------------------------------
  // Value accessors
  // ------------------------------------------------------------------------

  /** Get the first value. */
  T1 value1();

  /** Get the second value. */
  T2 value2();

  /** Get the third value. */
  T3 value3();

  /** Get the fourth value. */
  T4 value4();

  /** Get the fifth value. */
  T5 value5();

  /** Get the sixth value. */
  T6 value6();

  /** Get the seventh value. */
  T7 value7();

  /** Get the eighth value. */
  T8 value8();

  /** Get the ninth value. */
  T9 value9();

  /** Get the tenth value. */
  T10 value10();

  /** Get the eleventh value. */
  T11 value11();

  /** Get the twelfth value. */
  T12 value12();

  /** Get the thirteenth value. */
  T13 value13();

  /** Get the fourteenth value. */
  T14 value14();

  /** Get the fifteenth value. */
  T15 value15();

  /** Get the sixteenth value. */
  T16 value16();

  /** Get the seventeenth value. */
  T17 value17();

  /** Get the eighteenth value. */
  T18 value18();

  /** Get the ninteenth value. */
  T19 value19();

  /** Get the twentieth value. */
  T20 value20();

  /** Get the twenty-first value. */
  T21 value21();

  /** Set the first value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value1(T1 value);

  /** Set the second value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value2(T2 value);

  /** Set the third value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value3(T3 value);

  /** Set the fourth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value4(T4 value);

  /** Set the fifth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value5(T5 value);

  /** Set the sixth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value6(T6 value);

  /** Set the seventh value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value7(T7 value);

  /** Set the eighth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value8(T8 value);

  /** Set the ninth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value9(T9 value);

  /** Set the tenth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value10(T10 value);

  /** Set the eleventh value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value11(T11 value);

  /** Set the twelfth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value12(T12 value);

  /** Set the thirteenth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value13(T13 value);

  /** Set the fourteenth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value14(T14 value);

  /** Set the fifteenth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value15(T15 value);

  /** Set the sixteenth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value16(T16 value);

  /** Set the seventeenth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value17(T17 value);

  /** Set the eighteenth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value18(T18 value);

  /** Set the ninteenth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value19(T19 value);

  /** Set the twentieth value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value20(T20 value);

  /** Set the twenty-first value. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      value21(T21 value);

  /** Set all values. */
  @NotNull
  Record21<
          T1,
          T2,
          T3,
          T4,
          T5,
          T6,
          T7,
          T8,
          T9,
          T10,
          T11,
          T12,
          T13,
          T14,
          T15,
          T16,
          T17,
          T18,
          T19,
          T20,
          T21>
      values(
          T1 t1,
          T2 t2,
          T3 t3,
          T4 t4,
          T5 t5,
          T6 t6,
          T7 t7,
          T8 t8,
          T9 t9,
          T10 t10,
          T11 t11,
          T12 t12,
          T13 t13,
          T14 t14,
          T15 t15,
          T16 t16,
          T17 t17,
          T18 t18,
          T19 t19,
          T20 t20,
          T21 t21);

  /** {@inheritDoc} */
  @NotNull
  @Override
  <T>
      Record21<
              T1,
              T2,
              T3,
              T4,
              T5,
              T6,
              T7,
              T8,
              T9,
              T10,
              T11,
              T12,
              T13,
              T14,
              T15,
              T16,
              T17,
              T18,
              T19,
              T20,
              T21>
          with(Field<T> field, T value);

  /** {@inheritDoc} */
  @NotNull
  @Override
  <T, U>
      Record21<
              T1,
              T2,
              T3,
              T4,
              T5,
              T6,
              T7,
              T8,
              T9,
              T10,
              T11,
              T12,
              T13,
              T14,
              T15,
              T16,
              T17,
              T18,
              T19,
              T20,
              T21>
          with(Field<T> field, U value, Converter<? extends T, ? super U> converter);

  // ------------------------------------------------------------------------
  // Value accessors for record destructuring in Kotlin
  // ------------------------------------------------------------------------

  /**
   * Get the first value.
   *
   * <p>This is the same as {@link #value1()}.
   */
  T1 component1();

  /**
   * Get the second value.
   *
   * <p>This is the same as {@link #value2()}.
   */
  T2 component2();

  /**
   * Get the third value.
   *
   * <p>This is the same as {@link #value3()}.
   */
  T3 component3();

  /**
   * Get the fourth value.
   *
   * <p>This is the same as {@link #value4()}.
   */
  T4 component4();

  /**
   * Get the fifth value.
   *
   * <p>This is the same as {@link #value5()}.
   */
  T5 component5();

  /**
   * Get the sixth value.
   *
   * <p>This is the same as {@link #value6()}.
   */
  T6 component6();

  /**
   * Get the seventh value.
   *
   * <p>This is the same as {@link #value7()}.
   */
  T7 component7();

  /**
   * Get the eighth value.
   *
   * <p>This is the same as {@link #value8()}.
   */
  T8 component8();

  /**
   * Get the ninth value.
   *
   * <p>This is the same as {@link #value9()}.
   */
  T9 component9();

  /**
   * Get the tenth value.
   *
   * <p>This is the same as {@link #value10()}.
   */
  T10 component10();

  /**
   * Get the eleventh value.
   *
   * <p>This is the same as {@link #value11()}.
   */
  T11 component11();

  /**
   * Get the twelfth value.
   *
   * <p>This is the same as {@link #value12()}.
   */
  T12 component12();

  /**
   * Get the thirteenth value.
   *
   * <p>This is the same as {@link #value13()}.
   */
  T13 component13();

  /**
   * Get the fourteenth value.
   *
   * <p>This is the same as {@link #value14()}.
   */
  T14 component14();

  /**
   * Get the fifteenth value.
   *
   * <p>This is the same as {@link #value15()}.
   */
  T15 component15();

  /**
   * Get the sixteenth value.
   *
   * <p>This is the same as {@link #value16()}.
   */
  T16 component16();

  /**
   * Get the seventeenth value.
   *
   * <p>This is the same as {@link #value17()}.
   */
  T17 component17();

  /**
   * Get the eighteenth value.
   *
   * <p>This is the same as {@link #value18()}.
   */
  T18 component18();

  /**
   * Get the ninteenth value.
   *
   * <p>This is the same as {@link #value19()}.
   */
  T19 component19();

  /**
   * Get the twentieth value.
   *
   * <p>This is the same as {@link #value20()}.
   */
  T20 component20();

  /**
   * Get the twenty-first value.
   *
   * <p>This is the same as {@link #value21()}.
   */
  T21 component21();
}
