plugins {
    kotlin("jvm") version "2.1.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.1.0")
    implementation("io.github.cdimascio:dotenv-java:3.2.0")
    implementation("dev.kord:kord-core:0.15.0")
}

application {
    mainClass.set("Main")
    applicationDefaultJvmArgs = listOf(
        "--add-exports", "java.base/kotlin=ALL-UNNAMED"
    )
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(23))
sourceSets.main {
    java.srcDirs(".")
    resources.srcDirs("resources")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    exclude("**/bin/**")
}

tasks.processResources.configure {
    dependsOn(tasks.compileJava)
}