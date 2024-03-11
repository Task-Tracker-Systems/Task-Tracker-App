plugins {
    kotlin("jvm") version "1.9.23"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
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
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
    implementation("app.tasktrackersystems.tasktracker.usecases:Task-Tracker-Usecases:1.0-SNAPSHOT")
    implementation("app.tasktrackersystems.tasktracker.entities:Task-Tracker-Entities:1.0-SNAPSHOT")
    implementation("app.tasktrackersystems.tasktracker.usecases.sqlite:Task-Tracker-Usecases-SQLite-Impl:1.0-SNAPSHOT")
    implementation("app.cash.sqldelight:sqlite-driver:2.0.0-rc01")
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
