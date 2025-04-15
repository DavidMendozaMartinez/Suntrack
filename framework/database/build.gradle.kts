plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.davidmendozamartinez.framework.database"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    api(project(":data"))

    implementation(libs.hilt.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)

    kapt(libs.hilt.android.compiler)

    ksp(libs.room.compiler)
}
