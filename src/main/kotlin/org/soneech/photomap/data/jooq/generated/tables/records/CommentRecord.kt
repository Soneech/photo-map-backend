/*
 * This file is generated by jOOQ.
 */
package org.soneech.photomap.`data`.jooq.generated.tables.records


import jakarta.annotation.Nullable
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

import java.time.LocalDateTime

import javax.annotation.processing.Generated

import org.jooq.Record1
import org.jooq.impl.UpdatableRecordImpl


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
open class CommentRecord() : UpdatableRecordImpl<CommentRecord>(org.soneech.photomap.`data`.jooq.generated.tables.Comment.COMMENT) {

    open var id: Long?
        set(value): Unit = set(0, value)
    @Nullable
        get(): Long? = get(0) as Long?

    @get:NotNull
    open var userId: Long?
        set(value): Unit = set(1, value)
    @NotNull
        get(): Long? = get(1) as Long?

    @get:NotNull
    open var markId: Long?
        set(value): Unit = set(2, value)
    @NotNull
        get(): Long? = get(2) as Long?

    @get:NotNull
    @get:Size(max = 1000000000)
    open var text: String?
        set(value): Unit = set(3, value)
    @NotNull
        get(): String? = get(3) as String?

    open var createdAt: LocalDateTime?
        set(value): Unit = set(4, value)
    @Nullable
        get(): LocalDateTime? = get(4) as LocalDateTime?

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    override fun key(): Record1<Long?> = super.key() as Record1<Long?>

    /**
     * Create a detached, initialised CommentRecord
     */
    constructor(id: Long? = null, userId: Long? = null, markId: Long? = null, text: String? = null, createdAt: LocalDateTime? = null): this() {
        this.id = id
        this.userId = userId
        this.markId = markId
        this.text = text
        this.createdAt = createdAt
        resetChangedOnNotNull()
    }

    /**
     * Create a detached, initialised CommentRecord
     */
    constructor(value: org.soneech.photomap.`data`.jooq.generated.tables.pojos.Comment?): this() {
        if (value != null) {
            this.id = value.id
            this.userId = value.userId
            this.markId = value.markId
            this.text = value.text
            this.createdAt = value.createdAt
            resetChangedOnNotNull()
        }
    }
}
