plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group = "app.tasktrackersystems"
version = "1.0-SNAPSHOT"

repositories {
}

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.5.0")
    implementation("app.tasktrackersystems.tasktracker.usecases:Task-Tracker-Usecases:1.0-SNAPSHOT")
    implementation("app.tasktrackersystems.tasktracker.usecases.sqlite:Task-Tracker-Usecases-SQLite-Impl:1.0-SNAPSHOT")
    implementation("app.cash.sqldelight:android-driver:2.0.0-rc01")
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "app.tasktrackersystems.tasktracker.android"
        targetSdk = 33
        minSdkVersion(33)
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

        kotlinOptions {
            jvmTarget = "17"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    namespace = "app.tasktrackersystems.tasktracker.android"
}