plugins {
    kotlin("multiplatform") version "1.8.22"
    id("maven-publish")
    id("com.google.devtools.ksp") version "1.8.22-1.0.11"
    id("org.jlleitschuh.gradle.ktlint") version "11.4.2"
}

group = "com.garbereder.tasktracker.usecases"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        nodejs()
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions.freeCompilerArgs.add("-Xir-minimized-member-names=false")
            }
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
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("com.garbereder.tasktracker.entities:Task-Tracker-Entities:1.0-SNAPSHOT")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation("io.mockative:mockative:1.4.1")
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
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

dependencies {
    configurations
        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
        .forEach {
            add(it.name, "io.mockative:mockative-processor:2.0.1")
        }
}
