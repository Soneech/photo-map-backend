package org.soneech.photomap.data.jooq.utils

import org.jooq.codegen.GenerationTool
import org.jooq.meta.jaxb.*
import org.jooq.meta.jaxb.Target

class JooqCodegen {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val database = Database()
                    .withName("org.jooq.meta.extensions.liquibase.LiquibaseDatabase")
                    .withProperties(
                            Property().withKey("rootPath").withValue("src/main/resources/db/changelog"),
                            Property().withKey("scripts").withValue("changelog-master.yml")
                    )

            val options = Generate()
                    .withValidationAnnotations(true)
                    .withGeneratedAnnotation(true)
                    .withGeneratedAnnotationDate(false)
                    .withNullableAnnotation(true)
                    .withNullableAnnotationType("jakarta.annotation.Nullable")
                    .withNonnullAnnotation(true)
                    .withNonnullAnnotationType("jakarta.validation.constraints.NotNull")
                    .withJpaAnnotations(false)
                    .withSpringAnnotations(true)
                    .withConstructorPropertiesAnnotation(true)
                    .withConstructorPropertiesAnnotationOnPojos(true)
                    .withConstructorPropertiesAnnotationOnRecords(true)
                    .withFluentSetters(false)
                    .withDaos(false)
                    .withPojos(true)

            val target = Target()
                    .withPackageName("org.soneech.photomap.data.jooq.generated")
                    .withDirectory("src/main/kotlin")

            val configuration = Configuration()
                    .withGenerator(
                            Generator()
                                    .withName("org.jooq.codegen.KotlinGenerator")
                                    .withDatabase(database)
                                    .withGenerate(options)
                                    .withTarget(target)
                    )

            GenerationTool.generate(configuration)
        }
    }
}
