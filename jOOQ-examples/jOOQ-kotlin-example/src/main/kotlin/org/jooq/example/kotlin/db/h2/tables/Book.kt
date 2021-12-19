/*
 * This file is generated by jOOQ.
 */
package org.jooq.example.kotlin.db.h2.tables


import java.time.LocalDateTime

import kotlin.collections.List

import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Name
import org.jooq.Record
import org.jooq.Row11
import org.jooq.Schema
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.example.kotlin.db.h2.Public
import org.jooq.example.kotlin.db.h2.keys.FK_T_BOOK_AUTHOR_ID
import org.jooq.example.kotlin.db.h2.keys.FK_T_BOOK_CO_AUTHOR_ID
import org.jooq.example.kotlin.db.h2.keys.PK_T_BOOK
import org.jooq.example.kotlin.db.h2.tables.records.BookRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class Book(
    alias: Name,
    child: Table<out Record>?,
    path: ForeignKey<out Record, BookRecord>?,
    aliased: Table<BookRecord>?,
    parameters: Array<Field<*>?>?
): TableImpl<BookRecord>(
    alias,
    Public.PUBLIC,
    child,
    path,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table()
) {
    companion object {

        /**
         * The reference instance of <code>PUBLIC.BOOK</code>
         */
        val BOOK: Book = Book()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<BookRecord> = BookRecord::class.java

    /**
     * The column <code>PUBLIC.BOOK.ID</code>.
     */
    val ID: TableField<BookRecord, Int?> = createField(DSL.name("ID"), SQLDataType.INTEGER.nullable(false).identity(true), this, "")

    /**
     * The column <code>PUBLIC.BOOK.AUTHOR_ID</code>.
     */
    val AUTHOR_ID: TableField<BookRecord, Int?> = createField(DSL.name("AUTHOR_ID"), SQLDataType.INTEGER.nullable(false), this, "")

    /**
     * The column <code>PUBLIC.BOOK.CO_AUTHOR_ID</code>.
     */
    val CO_AUTHOR_ID: TableField<BookRecord, Int?> = createField(DSL.name("CO_AUTHOR_ID"), SQLDataType.INTEGER, this, "")

    /**
     * The column <code>PUBLIC.BOOK.DETAILS_ID</code>.
     */
    val DETAILS_ID: TableField<BookRecord, Int?> = createField(DSL.name("DETAILS_ID"), SQLDataType.INTEGER, this, "")

    /**
     * The column <code>PUBLIC.BOOK.TITLE</code>.
     */
    val TITLE: TableField<BookRecord, String?> = createField(DSL.name("TITLE"), SQLDataType.VARCHAR(400).nullable(false), this, "")

    /**
     * The column <code>PUBLIC.BOOK.PUBLISHED_IN</code>.
     */
    val PUBLISHED_IN: TableField<BookRecord, Int?> = createField(DSL.name("PUBLISHED_IN"), SQLDataType.INTEGER, this, "")

    /**
     * The column <code>PUBLIC.BOOK.LANGUAGE_ID</code>.
     */
    val LANGUAGE_ID: TableField<BookRecord, Int?> = createField(DSL.name("LANGUAGE_ID"), SQLDataType.INTEGER, this, "")

    /**
     * The column <code>PUBLIC.BOOK.CONTENT_TEXT</code>.
     */
    val CONTENT_TEXT: TableField<BookRecord, String?> = createField(DSL.name("CONTENT_TEXT"), SQLDataType.CLOB, this, "")

    /**
     * The column <code>PUBLIC.BOOK.CONTENT_PDF</code>.
     */
    val CONTENT_PDF: TableField<BookRecord, ByteArray?> = createField(DSL.name("CONTENT_PDF"), SQLDataType.BLOB, this, "")

    /**
     * The column <code>PUBLIC.BOOK.REC_VERSION</code>.
     */
    val REC_VERSION: TableField<BookRecord, Int?> = createField(DSL.name("REC_VERSION"), SQLDataType.INTEGER, this, "")

    /**
     * The column <code>PUBLIC.BOOK.REC_TIMESTAMP</code>.
     */
    val REC_TIMESTAMP: TableField<BookRecord, LocalDateTime?> = createField(DSL.name("REC_TIMESTAMP"), SQLDataType.LOCALDATETIME(6), this, "")

    private constructor(alias: Name, aliased: Table<BookRecord>?): this(alias, null, null, aliased, null)
    private constructor(alias: Name, aliased: Table<BookRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, aliased, parameters)

    /**
     * Create an aliased <code>PUBLIC.BOOK</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>PUBLIC.BOOK</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>PUBLIC.BOOK</code> table reference
     */
    constructor(): this(DSL.name("BOOK"), null)

    constructor(child: Table<out Record>, key: ForeignKey<out Record, BookRecord>): this(Internal.createPathAlias(child, key), child, key, BOOK, null)
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getIdentity(): Identity<BookRecord, Int?> = super.getIdentity() as Identity<BookRecord, Int?>
    override fun getPrimaryKey(): UniqueKey<BookRecord> = PK_T_BOOK
    override fun getReferences(): List<ForeignKey<BookRecord, *>> = listOf(FK_T_BOOK_AUTHOR_ID, FK_T_BOOK_CO_AUTHOR_ID)

    private lateinit var _fkTBookAuthorId: Author
    private lateinit var _fkTBookCoAuthorId: Author
    fun fkTBookAuthorId(): Author {
        if (!this::_fkTBookAuthorId.isInitialized)
            _fkTBookAuthorId = Author(this, FK_T_BOOK_AUTHOR_ID)

        return _fkTBookAuthorId;
    }
    fun fkTBookCoAuthorId(): Author {
        if (!this::_fkTBookCoAuthorId.isInitialized)
            _fkTBookCoAuthorId = Author(this, FK_T_BOOK_CO_AUTHOR_ID)

        return _fkTBookCoAuthorId;
    }
    override fun `as`(alias: String): Book = Book(DSL.name(alias), this)
    override fun `as`(alias: Name): Book = Book(alias, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): Book = Book(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): Book = Book(name, null)

    // -------------------------------------------------------------------------
    // Row11 type methods
    // -------------------------------------------------------------------------
    override fun fieldsRow(): Row11<Int?, Int?, Int?, Int?, String?, Int?, Int?, String?, ByteArray?, Int?, LocalDateTime?> = super.fieldsRow() as Row11<Int?, Int?, Int?, Int?, String?, Int?, Int?, String?, ByteArray?, Int?, LocalDateTime?>
}
