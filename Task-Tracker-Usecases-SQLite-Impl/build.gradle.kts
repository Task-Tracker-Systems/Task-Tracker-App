plugins {
    kotlin("multiplatform") version "1.8.22"
    id("maven-publish")
    id("app.cash.sqldelight") version "2.0.0-rc01"
}

group = "com.garbereder.tasktracker.usecases.sqlite"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.garbereder.tasktracker.usecases.sqlite")
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
                implementation("com.garbereder.tasktracker.usecases:Task-Tracker-Usecases:1.0-SNAPSHOT")
                implementation("com.garbereder.tasktracker.entities:Task-Tracker-Entities:1.0-SNAPSHOT")
                implementation("com.garbereder.tasktracker.entities-impl:Task-Tracker-Entities-Impl:1.0-SNAPSHOT")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting{
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
