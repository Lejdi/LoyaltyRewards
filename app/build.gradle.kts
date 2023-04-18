plugins {
    kotlin("android") version "1.8.20"
    kotlin("kapt") version "1.8.20"
    id("com.android.application") version "8.0.0"
    id("dagger.hilt.android.plugin") version "2.45"
    id("de.mannodermaus.android-junit5") version "1.8.2.1"
}

val compose = "1.4.1"
val composeCompilerVersion = "1.4.5"

android {

    namespace = "com.futuremind.loyaltyrewards"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.futuremind.loyaltyrewards"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    }
    kotlin {
        jvmToolchain(11)
    }
    composeOptions { kotlinCompilerExtensionVersion = composeCompilerVersion }

    buildFeatures {
        compose = true
    }

}

dependencies {

    val coroutines = "1.6.4"
    val accompanist = "0.30.1"
    val junit5 = "5.9.2"

    implementation(files("libs/mockapi.jar"))

    // UI
    implementation("androidx.compose.ui", "ui", compose)
    implementation("androidx.compose.ui", "ui-tooling", compose)
    implementation("androidx.compose.foundation", "foundation", compose)
    implementation("androidx.compose.material", "material", compose)
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("com.google.accompanist:accompanist-insets:$accompanist")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanist")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanist")
    implementation("io.coil-kt:coil-compose:2.3.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")

    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-compiler:2.45")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit5")
    testImplementation("io.mockk:mockk:1.13.3")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines")
    testImplementation("io.kotest:kotest-assertions-core:5.5.5")
    testImplementation("app.cash.turbine:turbine:0.12.3")

}