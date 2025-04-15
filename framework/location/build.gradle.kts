plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.davidmendozamartinez.framework.location"
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
    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.play.services.location)

    kapt(libs.hilt.android.compiler)
}
