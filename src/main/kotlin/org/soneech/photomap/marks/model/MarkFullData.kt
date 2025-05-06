package org.soneech.photomap.marks.model

import org.soneech.photomap.data.jooq.generated.tables.pojos.Mark
import org.soneech.photomap.data.jooq.generated.tables.pojos.Users

data class MarkFullData(
    val author: Users,
    val mark: Mark,
    val photos: List<FileContainer> = emptyList(),
    val videos: List<FileContainer> = emptyList(),
)