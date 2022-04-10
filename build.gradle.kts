import com.soywiz.korge.gradle.*

plugins {
    kotlin("multiplatform") version "1.6.10"
}

buildscript {
    val korgePluginVersion: String by project

    repositories {
        mavenLocal()
        mavenCentral()
        google()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:$korgePluginVersion")
    }
}

apply<KorgeGradlePlugin>()

tasks {
    val javaVersion = "11"

    withType<JavaCompile>().configureEach {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = javaVersion
        }
    }
}

korge {
    id = "ru.hse.sd.rogue"

    jvmMainClassName = "ru.hse.sd.rogue.MainKt"

    targetJvm()
}
