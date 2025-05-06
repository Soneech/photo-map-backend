package org.soneech.photomap.marks.service

import org.apache.coyote.BadRequestException
import org.soneech.photomap.data.jooq.generated.tables.pojos.Likes
import org.soneech.photomap.marks.repository.LikesRepository
import org.springframework.stereotype.Service

@Service
class LikesService(
    private val likesRepository: LikesRepository,
) {

    fun getLikesForMark(markId: Long): List<Likes> {
        return likesRepository.getLikesForMark(markId)
    }

    fun handleLike(markId: Long, userId: Long): Likes {
        val like = Likes(markId = markId, userId = userId)

        val isExists = likesRepository.isExists(markId, userId)
        when(isExists) {
            true -> likesRepository.deleteLike(markId, userId)
            false -> likesRepository.addLike(like)
                ?: throw BadRequestException(
                    "Не удалось добавить лайк для метки с id = $markId; userId = $userId"
                )
        }

        return like
    }
}