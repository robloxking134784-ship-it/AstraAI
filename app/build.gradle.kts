plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.astraai"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.astraai"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }

        debug {
            isDebuggable = true
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
}
