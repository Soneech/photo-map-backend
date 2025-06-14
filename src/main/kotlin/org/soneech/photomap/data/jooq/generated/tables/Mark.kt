/*
 * This file is generated by jOOQ.
 */
package org.soneech.photomap.`data`.jooq.generated.tables


import java.math.BigDecimal
import java.time.LocalDateTime

import javax.annotation.processing.Generated

import kotlin.collections.Collection
import kotlin.collections.List

import org.jooq.Check
import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.InverseForeignKey
import org.jooq.Name
import org.jooq.Path
import org.jooq.PlainSQL
import org.jooq.QueryPart
import org.jooq.Record
import org.jooq.SQL
import org.jooq.Schema
import org.jooq.Select
import org.jooq.Stringly
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = [
        "https://www.jooq.org",
        "jOOQ version:3.19.20"
    ],
    comments = "This class is generated by jOOQ"
)
@Suppress("UNCHECKED_CAST")
open class Mark(
    alias: Name,
    path: Table<out Record>?,
    childPath: ForeignKey<out Record, org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>?,
    parentPath: InverseForeignKey<out Record, org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>?,
    aliased: Table<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>?,
    parameters: Array<Field<*>?>?,
    where: Condition?
): TableImpl<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>(
    alias,
    org.soneech.photomap.`data`.jooq.generated.DefaultSchema.DEFAULT_SCHEMA,
    path,
    childPath,
    parentPath,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table(),
    where,
) {
    companion object {

        /**
         * The reference instance of <code>MARK</code>
         */
        val MARK: Mark = Mark()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord> = org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord::class.java

    /**
     * The column <code>MARK.ID</code>.
     */
    val ID: TableField<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, Long?> = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).identity(true), this, "")

    /**
     * The column <code>MARK.USER_ID</code>.
     */
    val USER_ID: TableField<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, Long?> = createField(DSL.name("USER_ID"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>MARK.LATITUDE</code>.
     */
    val LATITUDE: TableField<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, BigDecimal?> = createField(DSL.name("LATITUDE"), SQLDataType.DECIMAL(9, 6).nullable(false), this, "")

    /**
     * The column <code>MARK.LONGITUDE</code>.
     */
    val LONGITUDE: TableField<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, BigDecimal?> = createField(DSL.name("LONGITUDE"), SQLDataType.DECIMAL(9, 6).nullable(false), this, "")

    /**
     * The column <code>MARK.NAME</code>.
     */
    val NAME: TableField<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, String?> = createField(DSL.name("NAME"), SQLDataType.VARCHAR(255).nullable(false), this, "")

    /**
     * The column <code>MARK.DESCRIPTION</code>.
     */
    val DESCRIPTION: TableField<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, String?> = createField(DSL.name("DESCRIPTION"), SQLDataType.VARCHAR(1000000000), this, "")

    /**
     * The column <code>MARK.CATEGORY_ID</code>.
     */
    val CATEGORY_ID: TableField<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, Long?> = createField(DSL.name("CATEGORY_ID"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field(DSL.raw("14"), SQLDataType.BIGINT)), this, "")

    /**
     * The column <code>MARK.CREATED_AT</code>.
     */
    val CREATED_AT: TableField<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, LocalDateTime?> = createField(DSL.name("CREATED_AT"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field(DSL.raw("LOCALTIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "")

    /**
     * The column <code>MARK.UPDATED_AT</code>.
     */
    val UPDATED_AT: TableField<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, LocalDateTime?> = createField(DSL.name("UPDATED_AT"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field(DSL.raw("LOCALTIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "")

    /**
     * The column <code>MARK.IS_PRIVATE</code>.
     */
    val IS_PRIVATE: TableField<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, Boolean?> = createField(DSL.name("IS_PRIVATE"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field(DSL.raw("FALSE"), SQLDataType.BOOLEAN)), this, "")

    private constructor(alias: Name, aliased: Table<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>?): this(alias, null, null, null, aliased, null, null)
    private constructor(alias: Name, aliased: Table<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, null, aliased, parameters, null)
    private constructor(alias: Name, aliased: Table<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>?, where: Condition?): this(alias, null, null, null, aliased, null, where)

    /**
     * Create an aliased <code>MARK</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>MARK</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>MARK</code> table reference
     */
    constructor(): this(DSL.name("MARK"), null)

    constructor(path: Table<out Record>, childPath: ForeignKey<out Record, org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>?, parentPath: InverseForeignKey<out Record, org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>?): this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, MARK, null, null)

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    @Generated(
        value = [
            "https://www.jooq.org",
            "jOOQ version:3.19.20"
        ],
        comments = "This class is generated by jOOQ"
    )
    open class MarkPath : Mark, Path<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord> {
        constructor(path: Table<out Record>, childPath: ForeignKey<out Record, org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>?, parentPath: InverseForeignKey<out Record, org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>?): super(path, childPath, parentPath)
        private constructor(alias: Name, aliased: Table<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>): super(alias, aliased)
        override fun `as`(alias: String): MarkPath = MarkPath(DSL.name(alias), this)
        override fun `as`(alias: Name): MarkPath = MarkPath(alias, this)
        override fun `as`(alias: Table<*>): MarkPath = MarkPath(alias.qualifiedName, this)
    }
    override fun getSchema(): Schema? = if (aliased()) null else org.soneech.photomap.`data`.jooq.generated.DefaultSchema.DEFAULT_SCHEMA
    override fun getIdentity(): Identity<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, Long?> = super.getIdentity() as Identity<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, Long?>
    override fun getPrimaryKey(): UniqueKey<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord> = org.soneech.photomap.`data`.jooq.generated.keys.CONSTRAINT_2
    override fun getReferences(): List<ForeignKey<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord, *>> = listOf(org.soneech.photomap.`data`.jooq.generated.keys.FK_CATEGORY, org.soneech.photomap.`data`.jooq.generated.keys.FK_MARK_USER)

    private lateinit var _category: org.soneech.photomap.`data`.jooq.generated.tables.Category.CategoryPath

    /**
     * Get the implicit join path to the <code>CATEGORY</code> table.
     */
    fun category(): org.soneech.photomap.`data`.jooq.generated.tables.Category.CategoryPath {
        if (!this::_category.isInitialized)
            _category = org.soneech.photomap.`data`.jooq.generated.tables.Category.CategoryPath(this, org.soneech.photomap.`data`.jooq.generated.keys.FK_CATEGORY, null)

        return _category;
    }

    val category: org.soneech.photomap.`data`.jooq.generated.tables.Category.CategoryPath
        get(): org.soneech.photomap.`data`.jooq.generated.tables.Category.CategoryPath = category()

    private lateinit var _users: org.soneech.photomap.`data`.jooq.generated.tables.Users.UsersPath

    /**
     * Get the implicit join path to the <code>USERS</code> table.
     */
    fun users(): org.soneech.photomap.`data`.jooq.generated.tables.Users.UsersPath {
        if (!this::_users.isInitialized)
            _users = org.soneech.photomap.`data`.jooq.generated.tables.Users.UsersPath(this, org.soneech.photomap.`data`.jooq.generated.keys.FK_MARK_USER, null)

        return _users;
    }

    val users: org.soneech.photomap.`data`.jooq.generated.tables.Users.UsersPath
        get(): org.soneech.photomap.`data`.jooq.generated.tables.Users.UsersPath = users()

    private lateinit var _comment: org.soneech.photomap.`data`.jooq.generated.tables.Comment.CommentPath

    /**
     * Get the implicit to-many join path to the <code>COMMENT</code> table
     */
    fun comment(): org.soneech.photomap.`data`.jooq.generated.tables.Comment.CommentPath {
        if (!this::_comment.isInitialized)
            _comment = org.soneech.photomap.`data`.jooq.generated.tables.Comment.CommentPath(this, null, org.soneech.photomap.`data`.jooq.generated.keys.FK_COMMENT_MARK.inverseKey)

        return _comment;
    }

    val comment: org.soneech.photomap.`data`.jooq.generated.tables.Comment.CommentPath
        get(): org.soneech.photomap.`data`.jooq.generated.tables.Comment.CommentPath = comment()

    private lateinit var _fileData: org.soneech.photomap.`data`.jooq.generated.tables.FileData.FileDataPath

    /**
     * Get the implicit to-many join path to the <code>FILE_DATA</code> table
     */
    fun fileData(): org.soneech.photomap.`data`.jooq.generated.tables.FileData.FileDataPath {
        if (!this::_fileData.isInitialized)
            _fileData = org.soneech.photomap.`data`.jooq.generated.tables.FileData.FileDataPath(this, null, org.soneech.photomap.`data`.jooq.generated.keys.FK_FILE_DATA_MARK.inverseKey)

        return _fileData;
    }

    val fileData: org.soneech.photomap.`data`.jooq.generated.tables.FileData.FileDataPath
        get(): org.soneech.photomap.`data`.jooq.generated.tables.FileData.FileDataPath = fileData()

    private lateinit var _likes: org.soneech.photomap.`data`.jooq.generated.tables.Likes.LikesPath

    /**
     * Get the implicit to-many join path to the <code>LIKES</code> table
     */
    fun likes(): org.soneech.photomap.`data`.jooq.generated.tables.Likes.LikesPath {
        if (!this::_likes.isInitialized)
            _likes = org.soneech.photomap.`data`.jooq.generated.tables.Likes.LikesPath(this, null, org.soneech.photomap.`data`.jooq.generated.keys.FK_LIKES_MARK.inverseKey)

        return _likes;
    }

    val likes: org.soneech.photomap.`data`.jooq.generated.tables.Likes.LikesPath
        get(): org.soneech.photomap.`data`.jooq.generated.tables.Likes.LikesPath = likes()
    override fun getChecks(): List<Check<org.soneech.photomap.`data`.jooq.generated.tables.records.MarkRecord>> = listOf(
        Internal.createCheck(this, DSL.name("CONSTRAINT_23"), "\"LATITUDE\" BETWEEN -90 AND 90", true),
        Internal.createCheck(this, DSL.name("CONSTRAINT_23F"), "\"LONGITUDE\" BETWEEN -180 AND 180", true)
    )
    override fun `as`(alias: String): Mark = Mark(DSL.name(alias), this)
    override fun `as`(alias: Name): Mark = Mark(alias, this)
    override fun `as`(alias: Table<*>): Mark = Mark(alias.qualifiedName, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): Mark = Mark(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): Mark = Mark(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): Mark = Mark(name.qualifiedName, null)

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Condition?): Mark = Mark(qualifiedName, if (aliased()) this else null, condition)

    /**
     * Create an inline derived table from this table
     */
    override fun where(conditions: Collection<Condition>): Mark = where(DSL.and(conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(vararg conditions: Condition?): Mark = where(DSL.and(*conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Field<Boolean?>?): Mark = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(condition: SQL): Mark = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String): Mark = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg binds: Any?): Mark = where(DSL.condition(condition, *binds))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg parts: QueryPart): Mark = where(DSL.condition(condition, *parts))

    /**
     * Create an inline derived table from this table
     */
    override fun whereExists(select: Select<*>): Mark = where(DSL.exists(select))

    /**
     * Create an inline derived table from this table
     */
    override fun whereNotExists(select: Select<*>): Mark = where(DSL.notExists(select))
}
