group = "no.nav"
version = "0.0.1"

val tokenSupport = "1.1.5"
val ktorVersion = "1.3.2"
val kluentVersion = "1.61"
val platformRunner = "1.5.1"
val wiremockVersion = "2.26.3"
val juniper = "5.6.2"
val juniperPlatform = "1.6.2"

val mainClassName = "no.nav.dingsvalidate.DingsvalidateKt"

plugins {
    kotlin("jvm") version "1.3.72"
    java
    id("org.jmailen.kotlinter") version "2.3.2"
    id("com.github.ben-manes.versions") version "0.28.0"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/ktor")
    maven(url = "https://kotlin.bintray.com/kotlinx")
    maven(url = "http://packages.confluent.io/maven/")
}

tasks {
    withType<Jar> {
        manifest.attributes["Main-Class"] = mainClassName
    }
    create("printVersion") {
        println(project.version)
    }
    withType<Test> {
        useJUnitPlatform()
        testLogging.events("passed", "skipped", "failed")
    }
}

dependencies {
    implementation (kotlin("stdlib"))
    implementation ("io.ktor:ktor-auth:$ktorVersion")
    implementation ("io.ktor:ktor-auth-jwt:$ktorVersion")
    implementation ("io.ktor:ktor-server-netty:$ktorVersion")
    implementation ("io.ktor:ktor-jackson:$ktorVersion")
    implementation ("io.ktor:ktor-client-core:$ktorVersion")
    implementation ("io.ktor:ktor-client-jackson:$ktorVersion")
    implementation ("no.nav.security:token-validation-ktor:$tokenSupport")
    implementation("no.nav.security:token-validation-test-support:$tokenSupport")

    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion") {
        exclude(group = "org.eclipse.jetty")
    }
    testImplementation("org.amshove.kluent:kluent:$kluentVersion")
    testImplementation("org.junit.platform:junit-platform-runner:$platformRunner")
    testImplementation ("com.github.tomakehurst:wiremock:$wiremockVersion")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:$juniper")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:$juniper")
    testImplementation ("org.junit.platform:junit-platform-launcher:$juniperPlatform")

}