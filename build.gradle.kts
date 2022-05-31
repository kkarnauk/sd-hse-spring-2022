import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
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
    implementation("com.github.ajalt.clikt:clikt:3.4.0")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
    testImplementation("io.mockk:mockk:1.12.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events = setOf(
            TestLogEvent.FAILED,
            TestLogEvent.STANDARD_OUT
        )
        exceptionFormat = TestExceptionFormat.SHORT
        showExceptions = true
        showCauses = true
        showStackTraces = true
        showStandardStreams = true

        info {
            events = setOf(
                TestLogEvent.FAILED,
                TestLogEvent.PASSED,
                TestLogEvent.SKIPPED,
                TestLogEvent.STANDARD_OUT
            )

            exceptionFormat = TestExceptionFormat.FULL
        }

        addTestListener(object : TestListener {
            override fun beforeSuite(suite: TestDescriptor) = Unit
            override fun beforeTest(testDescriptor: TestDescriptor) = Unit
            override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) = Unit
            override fun afterSuite(desc: TestDescriptor, result: TestResult) {
                if (desc.parent != null) return

                val summary = """
                    ${result.resultType} 
                    (
                    ${result.testCount} tests, 
                    ${result.successfulTestCount} passed, 
                    ${result.failedTestCount} failed, 
                    ${result.skippedTestCount} skipped
                    ) 
                    in ${(result.endTime - result.startTime) / 1000.0} seconds
                    """.trimIndent().replace(System.lineSeparator(), "")

                println(
                    """
                    ┌${"─".repeat(summary.length + 2)}┐
                    | $summary |
                    └${"─".repeat(summary.length + 2)}┘
                    """.trimIndent()
                )
            }
        })
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.register("fatJar", type = Jar::class) {
    archiveBaseName.set("${project.name}-fat")
    manifest {
        attributes["Implementation-Title"] = "Bash-like CLI"
        attributes["Implementation-Version"] = archiveVersion
        attributes["Main-Class"] = "ru.hse.sd.cli.MainKt"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks["jar"] as CopySpec)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    dependsOn("build")
}
