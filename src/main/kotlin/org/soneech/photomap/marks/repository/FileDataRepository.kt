package org.soneech.photomap.marks.repository

import org.jooq.DSLContext
import org.soneech.photomap.data.jooq.generated.tables.pojos.FileData
import org.soneech.photomap.data.jooq.generated.tables.records.FileDataRecord
import org.soneech.photomap.data.jooq.generated.tables.references.FILE_DATA
import org.springframework.stereotype.Repository

@Repository
class FileDataRepository(
    private val dsl: DSLContext,
) {
    fun create(fileData: FileData): FileData? {
        val record = FileDataRecord(fileData)
        return dsl.insertInto(FILE_DATA)
            .set(record)
            .returning()
            .fetchOneInto(FileData::class.java)
    }

    fun insertBatch(fileDataBatch: List<FileData>): Int {
        val records = mutableListOf<FileDataRecord>()

        for (file in fileDataBatch) {
            records.add(FileDataRecord(file))
        }
        return dsl.batchInsert(records).execute().size
    }
}