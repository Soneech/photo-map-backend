package org.soneech.photomap.marks.model

import org.soneech.photomap.data.jooq.generated.tables.pojos.Mark

data class MarkFullData(
    val mark: Mark,
    val photos: List<FileContainer> = emptyList(),
    val videos: List<FileContainer> = emptyList(),
)