package org.soneech.photomap.marks.service

import mu.KLogging
import org.soneech.photomap.aws.client.FilesClient
import org.soneech.photomap.aws.configuration.AwsProperties
import org.soneech.photomap.data.jooq.exception.CreationException
import org.soneech.photomap.data.jooq.exception.NotFoundException
import org.soneech.photomap.data.jooq.generated.tables.pojos.FileData
import org.soneech.photomap.data.jooq.generated.tables.pojos.Mark
import org.soneech.photomap.marks.model.FileContainer
import org.soneech.photomap.marks.model.MarkFullData
import org.soneech.photomap.marks.repository.FileDataRepository
import org.soneech.photomap.marks.repository.MarkRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.util.UUID

@Service
class MarkDataService (
    private val markRepository: MarkRepository,
    private val fileDataRepository: FileDataRepository,
    private val filesClient: FilesClient,
    private val aws: AwsProperties,
){

    fun getFullDataById(id: Long): MarkFullData {
        logger.info("Getting full mark data with id = $id")
        val mark = getById(id)
        val filesData = fileDataRepository.getAllByMarkId(id)

        val markFullData: MarkFullData = if (filesData.isNotEmpty()) {
            val files = filesClient.downloadFiles(filesData)
            MarkFullData(
                mark = mark,
                photos = files.first,
                videos = files.second,
            )
        } else {
            MarkFullData(mark)
        }

        return markFullData
    }
    fun getById(id: Long): Mark {
        return markRepository.getById(id)
            ?: throw NotFoundException("Метка с id = $id не найдена")
    }

    fun getAll(): List<Mark> {
        return markRepository.getAll()
    }

    fun create(mark: Mark, photos: List<MultipartFile>?, videos: List<MultipartFile>?): Mark {
        logger.info("Start creating mark")

        val createdMark = create(mark)
        val markId = requireNotNull(createdMark.id)

        val filesData = joinPhotosAndVideos(photos, videos)
        if (filesData.isNotEmpty()) {
            filesClient.uploadFiles(filesData)

            val size = saveRelations(filesData, markId)
            logger.info("Save relations for mark and files with size: $size")
        }

        logger.info("Created mark with data: $createdMark")
        return createdMark
    }

    fun create(mark: Mark): Mark {
        val now = LocalDateTime.now()
        mark.createdAt = now
        mark.updatedAt = now
        mark.likes = 0

        return markRepository.create(mark)
            ?: throw CreationException("Ошибка при создании метки с данными: $mark")
    }

    private fun saveRelations(files: List<FileContainer>, markId: Long): Int {
        val fileDataBatch = files.toFileData(markId)
        return fileDataRepository.insertBatch(fileDataBatch)
    }

    private fun joinPhotosAndVideos(
        photos: List<MultipartFile>?,
        videos: List<MultipartFile>?
    ): List<FileContainer> {
        return (photos?.toFileData(aws.buckets.photo.name).orEmpty() +
                videos?.toFileData(aws.buckets.video.name).orEmpty())
    }

    private fun List<MultipartFile>.toFileData(bucket: String): List<FileContainer> {
        return this.map { file ->
            FileContainer(
                key = UUID.randomUUID().toString(),
                bucket = bucket,
                multipartFile = file,
            )
        }
    }

    private fun List<FileContainer>.toFileData(markId: Long): List<FileData> {
        return this.map { fileContainer ->
            FileData(
                objectKey = fileContainer.key,
                bucketName = fileContainer.bucket,
                markId = markId
            )
        }
    }

    companion object: KLogging()
}