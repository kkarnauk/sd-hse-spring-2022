import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "ru.hse.sd"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("ru.hse.sd.cli.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.h0tk3y.betterParse:better-parse:0.4.3")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
