plugins {
    kotlin("jvm") version "1.9.10"
    id("org.jlleitschuh.gradle.ktlint") version "12.0.2"
    application
}

group = "app.tasktrackersystems.tasktracker.entities"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.github.kotlin-inquirer:kotlin-inquirer:0.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")
    implementation("app.tasktrackersystems.tasktracker.usecases:Task-Tracker-Usecases:1.0-SNAPSHOT")
    implementation("app.tasktrackersystems.tasktracker.entities:Task-Tracker-Entities:1.0-SNAPSHOT")
    implementation("app.tasktrackersystems.tasktracker.usecases.sqlite:Task-Tracker-Usecases-SQLite-Impl:1.0-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}
