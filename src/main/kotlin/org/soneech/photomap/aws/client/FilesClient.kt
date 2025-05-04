package org.soneech.photomap.aws.client

import org.soneech.photomap.aws.configuration.AwsProperties
import org.soneech.photomap.data.jooq.generated.tables.pojos.FileData
import org.soneech.photomap.marks.model.FileContainer
import org.springframework.stereotype.Service
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.lang.RuntimeException

@Service
class FilesClient(
    private val s3Client: S3Client,
    private val awsProperties: AwsProperties,
) {

    fun uploadFiles(files: List<FileContainer>) {
        for (fileContainer in files) {
            val file = requireNotNull(fileContainer.multipartFile)
            val putObjectRequest = PutObjectRequest.builder()
                .bucket(fileContainer.bucket)
                .key(fileContainer.key)
                .contentType(file.contentType)
                .build()

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.bytes))
        }
    }

    fun downloadFiles(filesData: List<FileData>): Pair<List<FileContainer>, List<FileContainer>> {
        val photos = mutableListOf<FileContainer>()
        val videos = mutableListOf<FileContainer>()

        for (fileData in filesData) {
            val bucket = requireNotNull(fileData.bucketName)
            val objectKey = requireNotNull(fileData.objectKey)

            val getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(objectKey)
                .build()

            s3Client.getObject(getObjectRequest).use { inputStream ->
                val data = inputStream.readAllBytes()
                val fileContainer = FileContainer(
                    key = objectKey,
                    bucket = bucket,
                    bytes = data
                )
                when(bucket) {
                    awsProperties.buckets.photo.name -> photos.add(fileContainer)
                    awsProperties.buckets.video.name -> videos.add(fileContainer)
                    else -> throw RuntimeException("Неизвестный бакет")
                }
            }
        }

        return Pair(photos, videos)
    }
}