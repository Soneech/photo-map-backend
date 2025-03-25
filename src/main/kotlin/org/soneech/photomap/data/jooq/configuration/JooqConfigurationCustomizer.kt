package org.soneech.photomap.data.jooq.configuration

import org.jooq.conf.RenderQuotedNames
import org.jooq.impl.DefaultConfiguration
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JooqConfigurationCustomizer {
    @Bean
    fun postgresJooqCustomizer(): DefaultConfigurationCustomizer {
        return DefaultConfigurationCustomizer { configuration: DefaultConfiguration ->
            configuration.settings()
                    .withRenderSchema(false)
                    .withRenderFormatted(true)
                    .withRenderQuotedNames(RenderQuotedNames.NEVER)
        }
    }
}