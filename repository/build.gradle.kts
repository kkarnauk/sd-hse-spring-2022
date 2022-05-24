plugins {
    kotlin("jvm")
    application
}

application {
    mainClass.set("ru.hse.sd.repo.MainKt")
}

val ktorVersion: String by project
val logbackVersion: String by project
val exposedVersion: String by project

dependencies {
    implementation(project(":model"))
    implementation(project(":settings"))
    implementation(project(":facade"))

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-gson:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("org.postgresql:postgresql:42.2.2")

    implementation("com.github.ajalt.clikt:clikt:3.4.2")
}
