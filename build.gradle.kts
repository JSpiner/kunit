import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22"
    id("io.gitlab.arturbosch.detekt") version "1.23.4"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
    id("jacoco")
    id("org.jetbrains.dokka") version "1.9.10"
    id("me.champeau.jmh") version "0.7.2"
}

group = "com.kunit"
version = "1.0.0"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    // Testing
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.kotest:kotest-property:5.8.0")

    // Benchmarking
    jmh("org.openjdk.jmh:jmh-core:1.37")
    jmh("org.openjdk.jmh:jmh-generator-annprocess:1.37")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf("-Xinline-classes", "-opt-in=kotlin.RequiresOptIn")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.95".toBigDecimal()
            }
        }
    }
}

detekt {
    config.from(files("config/detekt.yml"))
    buildUponDefaultConfig = true
    allRules = false
}

ktlint {
    version.set("1.1.0")
}
