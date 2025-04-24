plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "org.soneech"
version = "1.0.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// logging
	implementation("io.github.microutils:kotlin-logging:3.0.5")
	implementation("ch.qos.logback:logback-classic:1.5.18")

	// core
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// jooq
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.jooq:jooq-codegen:3.19.20")
	implementation("org.jooq:jooq-meta-extensions-liquibase:3.19.20")

	// security
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("com.auth0:java-jwt:4.5.0")

	// database
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.liquibase:liquibase-core")

	// aws
	implementation("software.amazon.awssdk:aws-sdk-java:2.29.33")
	implementation("software.amazon.awssdk:apache-client:2.29.33")

	// management
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	runtimeOnly("io.micrometer:micrometer-registry-prometheus")

	// tests
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
