import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	// kotlin-spring is a wrapper on top of all-open - https://kotlinlang.org/docs/all-open-plugin.html#spring-support
	kotlin("plugin.spring")
	// kotlin-jpa is wrapped on top of no-arg - https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support
	kotlin("plugin.jpa")
}

group = "com.example.api"
version = "0.0.1"
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
	//implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	/*implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")*/
	testImplementation("org.springframework.boot:spring-boot-starter-test")
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
