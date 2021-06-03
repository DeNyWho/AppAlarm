plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-android")
}

group = "me.denis"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "me.denis.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}