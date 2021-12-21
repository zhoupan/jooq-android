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
package io.r2dbc.spi;

/**
 * Represents the metadata for readable object, for example a column of the results returned from a
 * query or {@code OUT} parameter as result of running a stored procedure. The implementation of all
 * methods except {@link #getName()} is optional for drivers. Metadata is optionally available as
 * by-product of statement execution on a best-effort basis.
 *
 * @see ColumnMetadata
 * @see OutParameterMetadata
 * @since 0.9
 */
public interface ReadableMetadata {

  /**
   * Returns the primary Java {@link Class type}. This type can be considered the native
   * representation that is used to exchange values with the least loss in precision.
   *
   * <p><strong>Implementation notes</strong> Drivers should implement this method. The method
   * should return the actual type and refrain from returning {@code Object.class}. The default
   * implementation returns {@code null}. Drivers may need to inspect the value and perform parts of
   * decoding to determine the native Java type. This method should be expected to run in
   * non-constant time.
   *
   * @return the primary Java {@link Class type} or {@code null} if the type is not available.
   * @see Row#get
   * @see OutParameters#get
   */
  @Nullable
  default Class<?> getJavaType() {
    return null;
  }

  /**
   * Returns the database {@link Type}.
   *
   * @return the database {@link Type} descriptor.
   * @since 0.9
   */
  Type getType();

  /**
   * Returns the name.
   *
   * <p>The name does not necessarily reflect the names how they are in the underlying tables but
   * rather how results are represented (e.g. aliased) in the result.
   *
   * @return the name of the item
   */
  String getName();

  /**
   * Returns the native type descriptor that potentially exposes more metadata.
   *
   * <p><strong>Implementation notes</strong> Drivers should implement this method if they can
   * expose a driver-specific type metadata object exposing additional information. The default
   * implementation returns {@code null}.
   *
   * @return the native type descriptor that potentially exposes more metadata or {@code null} if no
   *     native type descriptor is available.
   */
  @Nullable
  default Object getNativeTypeMetadata() {
    return null;
  }

  /**
   * Returns the nullability of values.
   *
   * <p><strong>Implementation notes</strong> Implementation of this method is optional. The default
   * implementation returns {@link Nullability#UNKNOWN}.
   *
   * @return the nullability of values.
   * @see Nullability
   */
  default Nullability getNullability() {
    return Nullability.UNKNOWN;
  }

  /**
   * Returns the precision.
   *
   * <p>For numeric data, this is the maximum precision. For character data, this is the length in
   * characters. For datetime data types, this is the length in bytes required to represent the
   * value (assuming the maximum allowed precision of the fractional seconds component). For binary
   * data, this is the length in bytes. Returns {@code null} for data types where data type size is
   * not applicable or if the precision cannot be provided.
   *
   * <p><strong>Implementation notes</strong> Implementation of this method is optional. The default
   * implementation returns {@code null}.
   *
   * @return the precision or {@code null} if the precision is not available.
   */
  @Nullable
  default Integer getPrecision() {
    return null;
  }

  /**
   * Returns the scale.
   *
   * <p>This is the number of digits to right of the decimal point. Returns {@code null} for data
   * types where the scale is not applicable or if the scale cannot be provided.
   *
   * <p><strong>Implementation notes</strong> Implementation of this method is optional. The default
   * implementation returns {@code null}.
   *
   * @return the scale or {@code null} if the scale is not available.
   */
  @Nullable
  default Integer getScale() {
    return null;
  }
}
