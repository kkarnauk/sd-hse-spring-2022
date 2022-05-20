plugins {
    kotlin("jvm")
}

val ktorVersion: String by project
val logbackVersion: String by project
val bootstrapVersion: String by project

repositories {
    mavenCentral()
}

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
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-webjars:$ktorVersion")
    implementation("org.webjars:bootstrap:$bootstrapVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
}
