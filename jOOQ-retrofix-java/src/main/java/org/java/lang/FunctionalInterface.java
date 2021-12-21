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
package org.java.lang;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An informative annotation type used to indicate that an interface type declaration is intended to
 * be a <i>functional interface</i> as defined by the Java Language Specification.
 *
 * <p>Conceptually, a functional interface has exactly one abstract method. Since {@linkplain
 * java.lang.reflect.Method#isDefault() default methods} have an implementation, they are not
 * abstract. If an interface declares an abstract method overriding one of the public methods of
 * {@code java.lang.Object}, that also does <em>not</em> count toward the interface's abstract
 * method count since any implementation of the interface will have an implementation from {@code
 * java.lang.Object} or elsewhere.
 *
 * <p>Note that instances of functional interfaces can be created with lambda expressions, method
 * references, or constructor references.
 *
 * <p>If a type is annotated with this annotation type, compilers are required to generate an error
 * message unless:
 *
 * <ul>
 *   <li>The type is an interface type and not an annotation type, enum, or class.
 *   <li>The annotated type satisfies the requirements of a functional interface.
 * </ul>
 *
 * <p>However, the compiler will treat any interface meeting the definition of a functional
 * interface as a functional interface regardless of whether or not a {@code FunctionalInterface}
 * annotation is present on the interface declaration.
 *
 * <p><br>
 * <b>See <em>The Java� Language Specification</em>:</b><br>
 * 4.3.2. The Class Object <br>
 * <b>See <em>The Java� Language Specification</em>:</b><br>
 * 9.8 Functional Interfaces <br>
 * <b>See <em>The Java� Language Specification</em>:</b><br>
 * 9.4.3 Interface Method Body
 *
 * @since 1.8
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FunctionalInterface {}
