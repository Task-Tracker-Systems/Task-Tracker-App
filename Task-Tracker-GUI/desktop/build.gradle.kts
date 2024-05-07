import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "app.tasktrackersystems"
version = "1.0-SNAPSHOT"


kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(compose.desktop.currentOs)
                implementation("app.tasktrackersystems.tasktracker.usecases:Task-Tracker-Usecases:1.0-SNAPSHOT")
                implementation("app.tasktrackersystems.tasktracker.usecases.sqlite:Task-Tracker-Usecases-SQLite-Impl:1.0-SNAPSHOT")
                implementation("app.cash.sqldelight:sqlite-driver:2.0.0-rc01")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "TaskTrackerGUI"
            packageVersion = "1.0.0"
            modules("java.sql")
            buildTypes.release {
                proguard {
                    configurationFiles.from("compose.pro")
                }
            }
        }
    }
}
