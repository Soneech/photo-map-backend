package org.soneech.photomap.aws.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.http.apache.ApacheHttpClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import java.net.URI

@Configuration
class S3ClientConfiguration {

    @Bean
    fun s3Client(awsProperties: AwsProperties): S3Client {
        val credentials = AwsBasicCredentials.create(
            awsProperties.secretKeyId,
            awsProperties.secretKey,
        )
        return S3Client.builder()
            .httpClient(ApacheHttpClient.create())
            .region(Region.of(awsProperties.region))
            .endpointOverride(URI.create(awsProperties.baseUrl))
            .credentialsProvider(
                StaticCredentialsProvider.create(credentials)
            )
            .build()
    }
}