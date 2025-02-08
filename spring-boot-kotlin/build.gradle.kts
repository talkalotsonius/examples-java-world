import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
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
	implementation(Spring.boot.security)
	implementation ("jakarta.validation:jakarta.validation-api:3.1.1")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")
	implementation("org.jetbrains.kotlin:kotlin-reflect:_")
	implementation(Kotlin.stdlib.jdk8)
	runtimeOnly("org.postgresql:postgresql:_")
	testImplementation(Spring.boot.test)
	implementation("org.flywaydb:flyway-core:_")
	// Spring Boot Actuator for Monitoring
	implementation(Spring.boot.actuator)
	// Prometheus endpoint extension for Actuator
	implementation("io.micrometer:micrometer-registry-prometheus:_")
	// Test frameworks
	testImplementation(Testing.mockito.kotlin)
	testImplementation("com.h2database:h2:_")
//	testImplementation("com.github.javafaker:javafaker:_")
}

jacoco {
	toolVersion = "0.8.12"
	reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}

tasks {

	val extraTestsMatcher = "package/path/to/test/*.class"
	val extraTest = register<Test>("extraTest") {
		include(extraTestsMatcher)
		useJUnitPlatform {}
	}

	build {
		dependsOn(extraTest)
	}

	test {
		exclude(extraTestsMatcher)
		finalizedBy(jacocoTestReport)
		useJUnitPlatform {}
	}

	jacocoTestCoverageVerification {
		violationRules {
			rule { limit { minimum = BigDecimal.valueOf(0.0) } }
			rule {
				enabled = false
				element = "CLASS"
				includes = listOf("org.gradle.*")

				limit {
					counter = "LINE"
					value = "TOTALCOUNT"
					maximum = "0.3".toBigDecimal()
				}
			}
		}
	}

	jacocoTestReport {
		dependsOn(test)
		reports {
			xml.required = true
			csv.required = false
			html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
		}
	}

	check {
		dependsOn(jacocoTestCoverageVerification)
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "21"
	}
}
