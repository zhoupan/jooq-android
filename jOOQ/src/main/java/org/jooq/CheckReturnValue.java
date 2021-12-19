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

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.jetbrains.annotations.Contract;

/**
 * Simple version of the JSR 305 annotation that allows for inspecting jOOQ code and detect
 * accidentally omitted calls to {@link Query#execute()} and the likes in IntelliJ.
 *
 * <p>This annotation is {@link Internal}. Clients should not rely on its presence. The annotation
 * may be replaced with a more suitable one by JetBrains, e.g. the {@link Contract} annotation, when
 * a favourable use-case can be found.
 *
 * @author Lukas Eder
 * @see <a href=
 *     "https://github.com/jOOQ/jOOQ/issues/11718">https://github.com/jOOQ/jOOQ/issues/11718</a>
 * @see <a href=
 *     "https://youtrack.jetbrains.com/issue/IDEA-265263">https://youtrack.jetbrains.com/issue/IDEA-265263</a>
 */
@Documented
@Target(METHOD)
@Retention(CLASS)
@Internal
public @interface CheckReturnValue {}
