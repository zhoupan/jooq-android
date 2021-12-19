/*
 * This file is generated by jOOQ.
 */
package org.jooq.example.chart.db.routines;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.example.chart.db.Public;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LastDay extends AbstractRoutine<LocalDate> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>public.last_day.RETURN_VALUE</code>.
     */
    public static final Parameter<LocalDate> RETURN_VALUE = Internal.createParameter("RETURN_VALUE", SQLDataType.LOCALDATE, false, false);

    /**
     * The parameter <code>public.last_day._1</code>.
     */
    public static final Parameter<LocalDateTime> _1 = Internal.createParameter("_1", SQLDataType.LOCALDATETIME(0), false, true);

    /**
     * Create a new routine call instance
     */
    public LastDay() {
        super("last_day", Public.PUBLIC, SQLDataType.LOCALDATE);

        setReturnParameter(RETURN_VALUE);
        addInParameter(_1);
    }

    /**
     * Set the <code>_1</code> parameter IN value to the routine
     */
    public void set__1(LocalDateTime value) {
        setValue(_1, value);
    }

    /**
     * Set the <code>_1</code> parameter to the function to be used with a
     * {@link org.jooq.Select} statement
     */
    public void set__1(Field<LocalDateTime> field) {
        setField(_1, field);
    }
}
