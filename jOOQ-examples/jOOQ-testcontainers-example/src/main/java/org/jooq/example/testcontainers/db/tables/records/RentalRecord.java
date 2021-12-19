/*
 * This file is generated by jOOQ.
 */
package org.jooq.example.testcontainers.db.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.example.testcontainers.db.tables.Rental;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RentalRecord extends UpdatableRecordImpl<RentalRecord> implements Record7<Long, LocalDateTime, Long, Long, LocalDateTime, Long, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.rental.rental_id</code>.
     */
    public void setRentalId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.rental.rental_id</code>.
     */
    public Long getRentalId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.rental.rental_date</code>.
     */
    public void setRentalDate(LocalDateTime value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.rental.rental_date</code>.
     */
    public LocalDateTime getRentalDate() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>public.rental.inventory_id</code>.
     */
    public void setInventoryId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.rental.inventory_id</code>.
     */
    public Long getInventoryId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.rental.customer_id</code>.
     */
    public void setCustomerId(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.rental.customer_id</code>.
     */
    public Long getCustomerId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>public.rental.return_date</code>.
     */
    public void setReturnDate(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.rental.return_date</code>.
     */
    public LocalDateTime getReturnDate() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>public.rental.staff_id</code>.
     */
    public void setStaffId(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.rental.staff_id</code>.
     */
    public Long getStaffId() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>public.rental.last_update</code>.
     */
    public void setLastUpdate(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.rental.last_update</code>.
     */
    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, LocalDateTime, Long, Long, LocalDateTime, Long, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Long, LocalDateTime, Long, Long, LocalDateTime, Long, LocalDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Rental.RENTAL.RENTAL_ID;
    }

    @Override
    public Field<LocalDateTime> field2() {
        return Rental.RENTAL.RENTAL_DATE;
    }

    @Override
    public Field<Long> field3() {
        return Rental.RENTAL.INVENTORY_ID;
    }

    @Override
    public Field<Long> field4() {
        return Rental.RENTAL.CUSTOMER_ID;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return Rental.RENTAL.RETURN_DATE;
    }

    @Override
    public Field<Long> field6() {
        return Rental.RENTAL.STAFF_ID;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return Rental.RENTAL.LAST_UPDATE;
    }

    @Override
    public Long component1() {
        return getRentalId();
    }

    @Override
    public LocalDateTime component2() {
        return getRentalDate();
    }

    @Override
    public Long component3() {
        return getInventoryId();
    }

    @Override
    public Long component4() {
        return getCustomerId();
    }

    @Override
    public LocalDateTime component5() {
        return getReturnDate();
    }

    @Override
    public Long component6() {
        return getStaffId();
    }

    @Override
    public LocalDateTime component7() {
        return getLastUpdate();
    }

    @Override
    public Long value1() {
        return getRentalId();
    }

    @Override
    public LocalDateTime value2() {
        return getRentalDate();
    }

    @Override
    public Long value3() {
        return getInventoryId();
    }

    @Override
    public Long value4() {
        return getCustomerId();
    }

    @Override
    public LocalDateTime value5() {
        return getReturnDate();
    }

    @Override
    public Long value6() {
        return getStaffId();
    }

    @Override
    public LocalDateTime value7() {
        return getLastUpdate();
    }

    @Override
    public RentalRecord value1(Long value) {
        setRentalId(value);
        return this;
    }

    @Override
    public RentalRecord value2(LocalDateTime value) {
        setRentalDate(value);
        return this;
    }

    @Override
    public RentalRecord value3(Long value) {
        setInventoryId(value);
        return this;
    }

    @Override
    public RentalRecord value4(Long value) {
        setCustomerId(value);
        return this;
    }

    @Override
    public RentalRecord value5(LocalDateTime value) {
        setReturnDate(value);
        return this;
    }

    @Override
    public RentalRecord value6(Long value) {
        setStaffId(value);
        return this;
    }

    @Override
    public RentalRecord value7(LocalDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    public RentalRecord values(Long value1, LocalDateTime value2, Long value3, Long value4, LocalDateTime value5, Long value6, LocalDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RentalRecord
     */
    public RentalRecord() {
        super(Rental.RENTAL);
    }

    /**
     * Create a detached, initialised RentalRecord
     */
    public RentalRecord(Long rentalId, LocalDateTime rentalDate, Long inventoryId, Long customerId, LocalDateTime returnDate, Long staffId, LocalDateTime lastUpdate) {
        super(Rental.RENTAL);

        setRentalId(rentalId);
        setRentalDate(rentalDate);
        setInventoryId(inventoryId);
        setCustomerId(customerId);
        setReturnDate(returnDate);
        setStaffId(staffId);
        setLastUpdate(lastUpdate);
    }
}
