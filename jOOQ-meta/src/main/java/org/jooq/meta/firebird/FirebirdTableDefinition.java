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
package org.jooq.meta.firebird;

import static org.jooq.impl.DSL.inline;
import static org.jooq.impl.DSL.trim;
import static org.jooq.meta.firebird.FirebirdDatabase.CHARACTER_LENGTH;
import static org.jooq.meta.firebird.FirebirdDatabase.FIELD_SCALE;
import static org.jooq.meta.firebird.FirebirdDatabase.FIELD_TYPE;
import static org.jooq.meta.firebird.rdb.Tables.RDB$FIELDS;
import static org.jooq.meta.firebird.rdb.Tables.RDB$RELATION_FIELDS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.jooq.Record;
import org.jooq.TableOptions.TableType;
import org.jooq.impl.DSL;
import org.jooq.meta.AbstractTableDefinition;
import org.jooq.meta.ColumnDefinition;
import org.jooq.meta.DefaultColumnDefinition;
import org.jooq.meta.DefaultDataTypeDefinition;
import org.jooq.meta.SchemaDefinition;
import org.jooq.meta.firebird.rdb.tables.Rdb$fields;
import org.jooq.meta.firebird.rdb.tables.Rdb$relationFields;

/** @author Sugiharto Lim - Initial contribution */
public class FirebirdTableDefinition extends AbstractTableDefinition {

  private static final Pattern P_DEFAULT = Pattern.compile("DEFAULT\\s+");

  public FirebirdTableDefinition(SchemaDefinition schema, String name, String comment) {
    super(schema, name, comment);
  }

  public FirebirdTableDefinition(
      SchemaDefinition schema, String name, String comment, TableType tableType, String source) {
    super(schema, name, comment, tableType, source);
  }

  @Override
  protected List<ColumnDefinition> getElements0() throws SQLException {
    List<ColumnDefinition> result = new ArrayList<>();
    Rdb$relationFields r = RDB$RELATION_FIELDS.as("r");
    Rdb$fields f = RDB$FIELDS.as("f");
    // Inspiration for the below query was taken from jaybird's
    // DatabaseMetaData implementation
    for (Record record :
        create()
            .select(
                trim(r.RDB$FIELD_NAME).as(r.RDB$FIELD_NAME),
                r.RDB$DESCRIPTION,
                r.RDB$DEFAULT_VALUE,
                DSL.bitOr(
                        r.RDB$NULL_FLAG.nvl(inline((short) 0)),
                        f.RDB$NULL_FLAG.nvl(inline((short) 0)))
                    .as(r.RDB$NULL_FLAG),
                r.RDB$DEFAULT_SOURCE,
                r.RDB$FIELD_POSITION, // [#3342] FIELD_LENGTH should be ignored for LOBs
                CHARACTER_LENGTH(f).as("CHAR_LEN"),
                f.RDB$FIELD_PRECISION,
                FIELD_SCALE(f).as("FIELD_SCALE"),
                FIELD_TYPE(f).as("FIELD_TYPE"),
                trim(f.RDB$FIELD_NAME).as("DOMAIN_NAME"),
                r.RDB$DESCRIPTION,
                (((FirebirdDatabase) getDatabase()).is30()
                        ? r.RDB$IDENTITY_TYPE
                        : inline((short) 0))
                    .as(r.RDB$IDENTITY_TYPE))
            .from(r)
            .leftOuterJoin(f)
            .on(r.RDB$FIELD_SOURCE.eq(f.RDB$FIELD_NAME))
            .where(r.RDB$RELATION_NAME.eq(getName()))
            .orderBy(r.RDB$FIELD_POSITION)) {
      // [#9411] Firebird reports the DEFAULT keyword in this column, which
      // we do not want to reproduce in generated code.
      String defaultValue = record.get(r.RDB$DEFAULT_SOURCE);
      if (defaultValue != null) defaultValue = P_DEFAULT.matcher(defaultValue).replaceFirst("");
      DefaultDataTypeDefinition type =
          new DefaultDataTypeDefinition(
              getDatabase(),
              getSchema(),
              record.get("FIELD_TYPE", String.class),
              record.get("CHAR_LEN", short.class),
              record.get(f.RDB$FIELD_PRECISION),
              record.get("FIELD_SCALE", Integer.class),
              record.get(r.RDB$NULL_FLAG) == 0,
              defaultValue,
              record.get("DOMAIN_NAME") == null
                  ? null
                  : DSL.name(record.get("DOMAIN_NAME", String.class)));
      result.add(
          new DefaultColumnDefinition(
              getDatabase().getTable(getSchema(), getName()),
              record.get(r.RDB$FIELD_NAME),
              result.size() + 1,
              type,
              record.get(r.RDB$IDENTITY_TYPE, boolean.class),
              record.get(r.RDB$DESCRIPTION)));
    }
    return result;
  }
}
