plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    // Pastikan package name sudah benar dan konsisten.
    // Namespace di sini menggunakan id.antasari_p6minda_230104040218
    namespace = "id.antasari_p6minda_230104040218"
    compileSdk = 34
    defaultConfig {
        // applicationId di sini menggunakan id.antasari.p6minda_230104040218 (perhatikan perbedaan underscore/dot)
        applicationId = "id.antasari.p6minda_230104040218"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    // menyamakan versi java dan kotlin, yaitu 17
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        // untuk kotlin compiler + kapt
        jvmTarget = "17"
    }
}

dependencies {
    // ===== COMPOSE =====
    // Baris ini dikomentari (dengan //) karena syntax ===== COMPOSE ===== adalah invalid di blok dependencies.

    // BOM sinkronasi versi compose
    val composeBOM = platform("androidx.compose:compose-bom:2024.06.00")
    implementation(composeBOM)
    androidTestImplementation(composeBOM)

    // core UI Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    // Koreksi: Anda menulis "android.compose.ui:ui-tooling-preview". Harusnya "androidx.compose.ui:ui-tooling-preview"
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // activity compose
    implementation("androidx.activity:activity-compose:1.9.2")

    // lifecycle / viewModel Compose
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // ===== ROOM (local DB offline / CRUD) =====
    // Baris ini dikomentari (dengan //)
    val roomVersion = "2.6.1"
    // Perbaikan: Dalam string, variabel Kotlin harus diawali dengan $, bukan hanya nama variabel.
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // ===== Coroutines =====
    // Baris ini dikomentari (dengan //)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
}
// Tambahan rekomendasi kotlin untuk membuat Gradle, Kotlin, kapt
kotlin{
    jvmToolchain(17)
}