plugins {
    kotlin("multiplatform") version "1.8.22"
    id("maven-publish")
    id("app.cash.sqldelight") version "2.0.0-rc01"
    id("org.jlleitschuh.gradle.ktlint") version "11.4.0"
    id("org.jetbrains.kotlinx.kover") version "0.7.3"
}

group = "app.tasktrackersystems.tasktracker.usecases.sqlite"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("app.tasktrackersystems.tasktracker.usecases.sqlite")
        }
    }
}

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("app.tasktrackersystems.tasktracker.usecases:Task-Tracker-Usecases:1.0-SNAPSHOT")
                implementation("app.tasktrackersystems.tasktracker.entities:Task-Tracker-Entities:1.0-SNAPSHOT")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:sqlite-driver:2.0.0-rc01")
            }
        }
        val jvmTest by getting
        val nativeMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:native-driver:2.0.0-rc01")
            }
        }
        val nativeTest by getting
    }
}

ktlint {
    filter {
        // https://github.com/JLLeitschuh/ktlint-gradle/issues/522#issuecomment-958756817
        exclude { entry ->
            entry.file.toString().contains("generated")
        }
    }
}

koverReport {
    filters {
        excludes {
            classes(
                "app.tasktrackersystems.tasktracker.usecases.sqlite.TaskTrackerUsecasesSQLiteImpl.DatabaseImpl",
                "app.tasktrackersystems.tasktracker.usecases.sqlite.TaskTrackerUsecasesSQLiteImpl.DatabaseImpl\$Schema",
                "app.tasktrackersystems.tasktracker.usecases.sqlite.TaskTrackerUsecasesSQLiteImpl.DatabaseImplKt"
            )
        }
    }
}
