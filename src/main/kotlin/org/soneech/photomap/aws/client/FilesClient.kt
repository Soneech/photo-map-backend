package org.soneech.photomap.aws.client

import org.soneech.photomap.aws.configuration.AwsProperties
import org.soneech.photomap.marks.model.FileContainer
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest

@Service
class FilesClient(
    private val s3Client: S3Client,
    private val awsProperties: AwsProperties,
) {

    fun uploadFiles(files: List<FileContainer>) {
        for (fileContainer in files) {
            val putObjectRequest = PutObjectRequest.builder()
                .bucket(fileContainer.bucket)
                .key(fileContainer.key)
                .contentType(fileContainer.file.contentType)
                .build()

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileContainer.file.bytes))
        }
    }

    fun downloadFiles(filesIds: List<String>): ResponseEntity<MutableList<ByteArray>> {
        val files = mutableListOf<ByteArray>()
        var contentType: String = ""

        for (key in filesIds) {
            val getObjectRequest = GetObjectRequest.builder()
                .bucket(awsProperties.buckets.photo.name)
                .key(key)
                .build()

            s3Client.getObject(getObjectRequest).use { inputStream ->
                val data = inputStream.readAllBytes()
                files.add(data)
                contentType = inputStream.response().contentType()
            }
        }

        val headers = HttpHeaders().apply {
            add(HttpHeaders.CONTENT_DISPOSITION, "inline")
            add(HttpHeaders.CONTENT_TYPE, contentType)
        }
        return ResponseEntity.ok()
            .headers(headers)
            .body(files)
    }
}