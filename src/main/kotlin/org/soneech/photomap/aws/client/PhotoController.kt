package org.soneech.photomap.aws.client

import org.soneech.photomap.aws.configuration.AwsProperties
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import java.io.IOException

// test
@RestController
@RequestMapping("/photos")
class PhotoController(
    private val s3Client: S3Client,
    private val awsProperties: AwsProperties,
) {

    @PostMapping(consumes = [MULTIPART_FORM_DATA_VALUE])
    @Throws(IOException::class)
    fun uploadFile(@RequestParam photo: MultipartFile): String {
        val key = photo.originalFilename.toString()

        val putObjectRequest = PutObjectRequest.builder()
            .bucket(awsProperties.buckets.photo.name)
            .key(key)
            .contentType(photo.contentType)
            .build()

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(photo.bytes))
        return key
    }

    @GetMapping
    @Throws(IOException::class)
    fun downloadFile(@RequestParam key: String): ResponseEntity<ByteArray> {
        val getObjectRequest = GetObjectRequest.builder()
            .bucket(awsProperties.buckets.photo.name)
            .key(key)
            .build()

        s3Client.getObject(getObjectRequest).use { inputStream ->
            val data = inputStream.readAllBytes()

            val headers = HttpHeaders().apply {
                add(HttpHeaders.CONTENT_DISPOSITION, "inline")
                add(HttpHeaders.CONTENT_TYPE, inputStream.response().contentType())
            }

            return ResponseEntity.ok()
                .headers(headers)
                .body(data)
        }
    }
}