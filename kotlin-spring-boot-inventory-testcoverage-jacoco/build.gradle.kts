import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("jacoco")
	kotlin("jvm")
	// kotlin-spring is a wrapper on top of all-open - https://kotlinlang.org/docs/all-open-plugin.html#spring-support
	kotlin("plugin.spring")
	// kotlin-jpa is wrapped on top of no-arg - https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support
	kotlin("plugin.jpa")
}

group = "com.examples"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
	mavenCentral()
}

dependencies {
	implementation(Spring.boot.data.jpa)
	implementation(Spring.boot.web)
	implementation(Spring.boot.webflux)

	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf
	implementation(Spring.boot.thymeleaf)

	// Used for parsing csv with the jackson-dataformater
	// Version managed through spring boot
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:_")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")

	// Used for gradle build scripts for kotlin
	implementation(Spring.reactor.kotlin)
	implementation("org.jetbrains.kotlin:kotlin-reflect:_")
	implementation(Kotlin.stdlib.jdk8)
	implementation(KotlinX.coroutines.reactor)

	//Open API docs for Kotlin
	implementation("org.springdoc:springdoc-openapi-data-rest:_")
	implementation("org.springdoc:springdoc-openapi-ui:_")
	implementation("org.springdoc:springdoc-openapi-kotlin:_")
	developmentOnly(Spring.boot.devTools)

	//H2 database
	runtimeOnly("com.h2database:h2:_")
	testImplementation(Spring.boot.test)
	testImplementation(Spring.reactor.test)

	//Mockk library for testing
	testImplementation(Testing.mockK)
	implementation("com.ninja-squad:springmockk:_")
	testImplementation(Testing.mockito.kotlin)
}

jacoco {
	toolVersion = "0.8.12"
	reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}

tasks {

	test {
		finalizedBy(jacocoTestReport)
	}

	jacocoTestReport {
		dependsOn(test)
	}

	jacocoTestReport {
		reports {
			xml.required = true
			csv.required = false
			html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "21"
	}
}
