package org.soneech.photomap.aws.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated

@Component
@Validated
@ConfigurationProperties("cloud.aws.s3")
class AwsProperties {
    lateinit var baseUrl: String

    lateinit var secretKeyId: String

    lateinit var secretKey: String

    lateinit var region: String

    @NestedConfigurationProperty
    lateinit var buckets: BucketsProperties
}

@Component
class BucketsProperties {
    @NestedConfigurationProperty
    lateinit var photo: PhotoBucketProperties

    @NestedConfigurationProperty
    lateinit var video: VideoBucketProperties
}

@Component
class PhotoBucketProperties {
    lateinit var name: String
}

@Component
class VideoBucketProperties {
    lateinit var name: String
}