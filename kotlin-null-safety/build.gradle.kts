import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
}

group = "com.examples"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
	mavenCentral()
}

dependencies {
	implementation(Spring.boot.actuator)
	implementation(Spring.boot.data.jpa)
	implementation(Spring.boot.web)
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")
	implementation("org.jetbrains.kotlin:kotlin-reflect:_")
	implementation(Kotlin.stdlib.jdk8)
	developmentOnly(Spring.boot.devTools)
	runtimeOnly("com.h2database:h2:_")
	runtimeOnly("org.postgresql:postgresql:_")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
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
