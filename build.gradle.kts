val kotlinVersion = "1.4.32"
val kotlinCoroutinesVersion = "1.4.3"
val jacksonVersion = "2.12.5"

plugins {
    kotlin("jvm") version "1.4.32"
    kotlin("kapt") version "1.4.32"
}

buildscript {
    repositories {
        mavenCentral()
        maven { url = uri("https://maven.taktik.be/content/groups/public") }
    }
    dependencies {
        classpath("com.taktik.gradle:gradle-plugin-git-version:2.0.2")
        classpath("com.taktik.gradle:gradle-plugin-maven-repository:1.0.2")
    }
}

apply(plugin = "git-version")
apply(plugin = "maven-repository")
val gitVersion: String? by project

group = "io.icure"
version = gitVersion ?: "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.taktik.be/content/groups/public")
    }
}

dependencies {
    api(group = "com.github.pozo", name = "mapstruct-kotlin", version = "1.3.1.2")
    kapt(group = "com.github.pozo", name = "mapstruct-kotlin-processor", version = "1.3.1.2")

    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib", version = kotlinVersion)

    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version = kotlinCoroutinesVersion)
    implementation(
        group = "org.jetbrains.kotlinx",
        name = "kotlinx-coroutines-reactive",
        version = kotlinCoroutinesVersion
    )
    implementation(
        group = "org.jetbrains.kotlinx",
        name = "kotlinx-coroutines-reactor",
        version = kotlinCoroutinesVersion
    )

    implementation(group = "com.fasterxml.jackson.core", name = "jackson-core", version = jacksonVersion)
    implementation(group = "com.fasterxml.jackson.core", name = "jackson-databind", version = jacksonVersion)
    implementation(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin", version = jacksonVersion)
    implementation(group = "com.fasterxml.jackson.datatype", name = "jackson-datatype-jsr310", version = jacksonVersion)

    implementation(group = "io.icure", name = "async-jackson-http-client", version = "0.1.15-9cf193799d")
    implementation(group = "io.icure", name = "icure-reactive-kotlin-client", version = "0.1.275-680bc74192")

    implementation(group = "javax.inject", name = "javax.inject", version = "1")
    implementation(group = "org.mapstruct", name = "mapstruct", version = "1.3.1.Final")
    implementation(group = "com.github.ben-manes.caffeine", name = "caffeine", version = "3.0.3")

    implementation(group = "ch.qos.logback", name = "logback-classic", version = "1.2.3")
    implementation(group = "ch.qos.logback", name = "logback-access", version = "1.2.3")

    implementation(group = "org.slf4j", name = "slf4j-api", version = "1.7.12")
    implementation(group = "org.slf4j", name = "jul-to-slf4j", version = "1.7.12")
    implementation(group = "org.slf4j", name = "jcl-over-slf4j", version = "1.7.12")
    implementation(group = "org.slf4j", name = "log4j-over-slf4j", version = "1.7.12")

    implementation(group = "io.projectreactor", name = "reactor-core", version = "3.4.10")
    implementation(group = "io.projectreactor.netty", name = "reactor-netty", version = "1.0.11")

    // Bouncy Castle
    implementation(group = "org.bouncycastle", name = "bcprov-jdk15on", version = "1.69")
    implementation(group = "org.bouncycastle", name = "bcmail-jdk15on", version = "1.69")

    testImplementation(group = "io.kotlintest", name = "kotlintest", version = "2.0.7")
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter", version = "5.7.0")
    testImplementation(group = "com.willowtreeapps.assertk", name = "assertk-jvm", version = "0.24")
}


java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.getByName("publish") {
    dependsOn("apiGenerate", "build")
}

tasks.register("apiGenerate", Jar::class) {
    inputs.files(fileTree("openApiTemplates"))
        .withPropertyName("sourceFiles")
        .withPathSensitivity(PathSensitivity.RELATIVE)
    doLast {
        javaexec {
            main = "-jar"
            args = listOf(
                "${rootDir}/openapi-generator-cli.jar", "generate",
                "-i", "${rootDir}/icure-medical-device-spec.json",
                "-g", "kotlin",
                "-o", "$rootDir",

                "--model-package", "io.icure.md.client.models",
                "--api-package", "io.icure.md.client.apis",
                "--package-name", "io.icure.md.client",
                "--group-id", "io.icure",
                "--artifact-id", project.name,
                "--artifact-version", "0.0.1-SNAPSHOT",
                "--template-dir", "$rootDir/openApiTemplates",
                "--additional-properties", "useCoroutines=true,serializationLibrary=jackson"
            )
        }
    }
    dependsOn.add("download-openapi-spec") // required due to https://github.com/OpenAPITools/openapi-generator/issues/8255
}

tasks.register("download-openapi-spec") {
    doLast {
        val destFile = File("${rootDir}/icure-medical-device-spec.json")
        val url = "${System.getProperty("API_URL")}/v3/api-docs/v2"
        ant.invokeMethod("get", mapOf("src" to url, "dest" to destFile))
    }
}
