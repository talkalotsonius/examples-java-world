import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    // kotlin-spring is a wrapper on top of all-open - https://kotlinlang.org/docs/all-open-plugin.html#spring-support
    kotlin("plugin.spring")
    //id("org.jlleitschuh.gradle.ktlint")
}

group = "com.examples"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    implementation(Spring.boot.actuator)
    implementation(Spring.boot.web)
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")
    implementation("org.jetbrains.kotlin:kotlin-reflect:_")
    implementation(Kotlin.stdlib.jdk8)
    developmentOnly(Spring.boot.devTools)
    testImplementation(Spring.boot.test)
    testImplementation("io.rest-assured:rest-assured:_")
    testImplementation(Testing.mockK)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

//ktlint {
//    version.set("0.43.1")
//}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "21"
    }
}
