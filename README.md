# MapInclusion - Integrated Accessibility Mapping 📍♿

**MapInclusion** adalah solusi digital terintegrasi yang menggabungkan aplikasi Mobile (Android) dengan sistem Backend (Web Service) untuk memetakan fasilitas publik ramah disabilitas. Project ini dibangun dengan arsitektur yang modular, memisahkan logika bisnis, penyimpanan data, dan antarmuka pengguna.

## 📂 Arsitektur Project (WSE Implementation)
Berdasarkan struktur folder di `com.example.mapinclusion_project`, aplikasi ini menerapkan pola arsitektur modern:

* **Models**: Representasi data untuk `User`, `Facility`, dan `Report` yang sinkron antara Mobile dan Server.
* **Plugins**: Konfigurasi inti Web Service seperti `Databases`, `Security` (Autentikasi), dan `Serialization` (JSON parsing).
* **Repository**: Abstraksi sumber data yang menangani logika pengambilan data dari database/API.
* **Routing**: Endpoint Web Service (WSE) yang mengatur jalur komunikasi data.
* **Services**: Logika pemrosesan data, termasuk sistem `ScoringService` untuk fasilitas.
* **Application.kt**: *Entry Point* utama untuk sisi Server (Ktor).
* **MainActivity.kt**: *Entry Point* utama untuk sisi Mobile (Android/Compose).

## 🚀 Fitur Utama
* **Full-Stack Integration**: Menggunakan Ktor Server untuk manajemen data dan Jetpack Compose untuk UI Mobile.
* **Sistem Autentikasi**: Keamanan akun pengguna melalui plugin `Security`.
* **Peta Aksesibilitas**: Visualisasi data fasilitas secara real-time dari database.
* **Scoring System**: Penilaian otomatis kualitas fasilitas berdasarkan input pengguna.

## 🛠️ Stack Teknologi
* **Bahasa**: [Kotlin](https://kotlinlang.org/)
* **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
* **Map Engine**: [osmdroid](https://github.com/osmdroid/osmdroid) (OpenStreetMap)
* **Asynchronous Image**: [Coil](https://coil-kt.github.io/coil/compose/)

## ⚙️ Cara Menjalankan Project
1.  **Server Side**: Jalankan `Application.kt` untuk mengaktifkan Web Service di localhost (port 8080).
2.  **Mobile Side**:
    * Pastikan konfigurasi `BASE_URL` mengarah ke IP server yang aktif.
    * Lakukan **Build > Clean Project**.
    * Run `MainActivity` pada Emulator atau Perangkat Fisik.

## 👤 Identitas Pengembang
* **Nama**: Indina Nor Azizah
* **NIM**: 230104040218
* **Status**: Project Selesai 
