plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group = "app.tasktrackersystems"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.5.0")
}

android {
    compileSdkVersion(34)
    defaultConfig {
        applicationId = "app.tasktrackersystems.tasktracker.android"
        minSdkVersion(34)
        targetSdkVersion(34)
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