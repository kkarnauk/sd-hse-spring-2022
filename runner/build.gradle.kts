plugins {
    kotlin("jvm")
    application
}

application {
    mainClass.set("ru.hse.sd.hwproj.runner.MainKt")
}

val ktorVersion: String by project
val logbackVersion: String by project
val rabbitmqVersion: String by project

val rabbitmqMockVersion: String by project
val mockkVersion: String by project

val junitVersion: String by project

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":model"))
    implementation(project(":settings"))
    implementation(project(":repository"))
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
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("com.rabbitmq:amqp-client:$rabbitmqVersion")

    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("com.github.fridujo:rabbitmq-mock:$rabbitmqMockVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}
