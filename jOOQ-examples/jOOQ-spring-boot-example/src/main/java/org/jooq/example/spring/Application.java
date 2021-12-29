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
package org.jooq.example.spring;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;

/**
 * The spring boot application.
 *
 * <p>Starting from jOOQ 3.15, jOOQ supports {@link DSLContext} with a configured R2DBC {@link
 * Configuration#connectionFactory()} out of the box. Up until Spring Boot 2.5, Spring Boot is not
 * aware of this, and may auto configure an R2DBC connection rather than a JDBC connection. To work
 * around this, use {@link SpringBootApplication#exclude()} to explicitly exclude the {@link
 * R2dbcAutoConfiguration}.
 *
 * @author Thomas Darimont
 * @author Lukas Eder
 */
@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
