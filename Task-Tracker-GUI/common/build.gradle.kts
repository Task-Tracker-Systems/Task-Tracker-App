plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("app.cash.sqldelight") version "2.0.0-rc01"
}

group = "app.tasktrackersystems.tasktracker"
version = "1.0-SNAPSHOT"

kotlin {
    android()
    jvm("desktop") {
        jvmToolchain(17)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                implementation("app.tasktrackersystems.tasktracker.usecases:Task-Tracker-Usecases:1.0-SNAPSHOT")
                implementation("app.tasktrackersystems.tasktracker.entities:Task-Tracker-Entities:1.0-SNAPSHOT")
                implementation("app.tasktrackersystems.tasktracker.usecases.sqlite:Task-Tracker-Usecases-SQLite-Impl:1.0-SNAPSHOT")
                val voyagerVersion = "1.0.0"
                implementation("cafe.adriel.voyager:voyager-core:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.5.1")
                api("androidx.core:core-ktx:1.9.0")
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        val desktopTest by getting
    }
}
compose.desktop {
    application {
        mainClass = "app.tasktrackersystems.tasktracker.common.AppKt"
        nativeDistributions {
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb
            )
            packageName = "jvm"
            packageVersion = "1.0.0"
        }
        buildTypes.release {
            proguard {
                configurationFiles.from("../desktop/compose.pro")
            }
        }
    }
}

android {
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "app.tasktrackersystems.tasktracker.common"
}