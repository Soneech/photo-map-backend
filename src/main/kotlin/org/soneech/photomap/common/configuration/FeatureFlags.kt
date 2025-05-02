package org.soneech.photomap.common.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("feature-flags")
class FeatureFlags {
    var useStorage: Boolean = true
}