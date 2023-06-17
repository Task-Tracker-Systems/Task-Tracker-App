plugins {
    kotlin("jvm") version "1.8.22"
    application
}

group = "com.garbereder.tasktracker.entities"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven ("https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.github.kotlin-inquirer:kotlin-inquirer:0.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("com.garbereder.tasktracker.usecases:Task-Tracker-Usecases:1.0-SNAPSHOT")
    implementation("com.garbereder.tasktracker.entities:Task-Tracker-Entities:1.0-SNAPSHOT")
    implementation("com.garbereder.tasktracker.entities-impl:Task-Tracker-Entities-Impl:1.0-SNAPSHOT")
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